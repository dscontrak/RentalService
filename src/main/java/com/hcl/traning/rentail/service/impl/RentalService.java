package com.hcl.traning.rentail.service.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.dao.FilmRepository;
import com.hcl.traning.rentail.dao.PaymentRepository;
import com.hcl.traning.rentail.dao.RentalFilmRepository;
import com.hcl.traning.rentail.dao.RentalRepository;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.model.Payment;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilmsSerialize;
import com.hcl.traning.rentail.service.IRentalService;
import com.hcl.traning.rentail.util.CalcuatePayment;
import com.hcl.traning.rentail.util.CalculateInventory;
import com.hcl.traning.rentail.util.CodeGenerator;

@Service
public class RentalService implements IRentalService {
	
	@Autowired
	RentalRepository daoRental;
	
	@Autowired
	CustomerRepository daoCustomer;
	
	@Autowired
	FilmRepository daoFilm;
	
	@Autowired
	RentalFilmRepository daoRentalFilm;
	
	@Autowired
	PaymentRepository daoPayment;
	
	@Autowired
	CodeGenerator codeGenerator;
	
	@Autowired
	CalcuatePayment calcuatePayment;
	
	@Autowired
	CalculateInventory calculateInventory;
	
	
	
	@Override
	public Rental add(Rental rental) {
		LocalDateTime timestamp = LocalDateTime.now();	
		rental.setCreated(timestamp);				
		
		Customer customer = daoCustomer.findOne(rental.getCustomer().getId()) ;
		rental.setCustomer(customer);
		rental.setCode(codeGenerator.generate());
		
		// Set initial status Queue
		rental.setStatus("Q");		
		daoRental.save(rental);
		
		// read all rental and film 
		rental.getRentalFilms().forEach(currRentalFilm -> {
			Film foundFilm = daoFilm.findOne(currRentalFilm.getFilm().getId());
			
			if(foundFilm != null) {
				currRentalFilm.setFilm(foundFilm);
				currRentalFilm.setRental(rental);
				currRentalFilm.setReturnWithoutDue( calcuatePayment.calculateDayReturnWithoutDue(foundFilm) );
				// Save rentalFilm register
				daoRentalFilm.save(currRentalFilm);
			}
			
		});
		
		addPossiblePayments(rental);
		return rental;
	}	
	
	@Override
	public void addAll(Collection<Rental> rentals) {
		LocalDateTime timestamp = LocalDateTime.now();
		
		rentals.forEach(c -> {			
			c.setCreated(timestamp);
			daoRental.save(c);
		});
		
		/*for (Rental c : customers) {
			
		}*/
	}
	
	@Override
	public List<Rental> listAll() {
		return daoRental.findAll();
	}
	
	@Override
	public Rental getById(Long id) {
		Rental rentalFound = daoRental.findOne(id);
		/*try {
			calcuatePayment.setCustomer(rentalFound.getCustomer());
			Set<Payment> payments = calcuatePayment.calculateByFilmsToRent(rentalFound.getRentalFilms());
			rentalFound.setPossiblePayments(payments);
		} catch (CloneNotSupportedException e) {			
			e.printStackTrace();
		}*/
		
		addPossiblePayments(rentalFound);
				
		return rentalFound;
	}

	@Override
	public void addPossiblePayments(Rental rental) {
		Set<Payment> payments = null;
		
		if(rental.getStatus().equals("D")) {
			//throw new IllegalArgumentException("The rental has been done");
			return;
		}
		
		try {
			calcuatePayment.setCustomer(rental.getCustomer());			
			
			if(rental.getStatus().equalsIgnoreCase("Q")) {
				payments = calcuatePayment.calculateByFilmsToRent(rental.getRentalFilms());
			}else if(rental.getStatus().equalsIgnoreCase("A")) {
				payments = calcuatePayment.calculateChargeByLateReturn(rental.getRentalFilms());
			}
			
			rental.setPossiblePayments(payments);	
			
		} catch (CloneNotSupportedException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Override
	public Rental addPaymentsToRental(Rental rentalRequest) {
		Rental rentalFound = daoRental.findOne(rentalRequest.getId());
		 
		
		if(!rentalFound.getStatus().equals("Q")) {
			throw new IllegalArgumentException("The rental has a differnt status to Queue");
		}
		
		rentalFound.setPayments(rentalRequest.getPayments());		
		rentalFound.getPayments().forEach(p -> {
			p.setCustomer(rentalFound.getCustomer());
			p.setRental(rentalFound);
		});
		
		// Calculate inventory and available 
		rentalFound.getRentalFilms().forEach(rf -> {
			Film film = calculateInventory.getFilmToUpdateAgaistInventory(rf.getFilm(), rf.getAmount());
			daoFilm.save(film);
		});
		
		
		// Update custumer's bonus
		Customer customer = rentalFound.getCustomer();
		customer.setBonus(calcuatePayment.getCustomerWithBonus(rentalFound.getRentalFilms()));				
		daoCustomer.save(customer);
		
		// Update rental status 
		rentalFound.setStatus("A");
		daoRental.save(rentalFound);
		
		// Save payments
		//daoPayment.saveAll(rentalFound.getPayments());		
		daoPayment.save(rentalFound.getPayments());
		
		
		return rentalFound;
	}
	
	@Override
	public Rental returnRental(Rental rentalRequest) {
		 Rental rentalFound = daoRental.findOne(rentalRequest.getId());
		   
		// Validate rental
		if(!rentalFound.getStatus().equals("A")) {
			throw new IllegalArgumentException("The rental has a differnt status to Active");
		}
		
		// Add possible payments to do when return.
		// addPossiblePayments(rentalRequest);
		
		// Update inventory
		rentalFound.getRentalFilms().forEach(rf -> {
			int amountReturn = calculateInventory.getAmountToUpdateFilmReturn(rf, rentalRequest.getRentalFilms());
			Film film = rf.getFilm();
			film.setInventoryStore(film.getInventoryStore() + amountReturn);
			film.setInventoryRent(film.getInventoryRent() - amountReturn);
			
			daoFilm.save(film);
		});
		
		// Update rental 
		rentalFound.setStatus("D");
		daoRental.save(rentalFound);
		
		
		return rentalFound;
	}
	
	
	public Set<RentalFilmsSerialize> getDataRentalFilms(Long id){
		Set<RentalFilmsSerialize> filmsSerializes = new HashSet<RentalFilmsSerialize>();
		Rental rental = daoRentalFilm.findRentalByRental(id).getRental(); // rentalFilmService.findAllByRental(id);
		
		rental.getRentalFilms().forEach(rf -> {
			RentalFilmsSerialize rfSerialize = new RentalFilmsSerialize();
			rfSerialize.copyFromRentalFilm(rf);
			
			filmsSerializes.add(rfSerialize);
		});
		
		return filmsSerializes;
	}
	

	/*private Set<Payment> setPaymentFromRequest(Rental rentalRequest, Rental rentalFound) {
		Customer currentCustomer = rentalFound.getCustomer();
		Set<Payment> payments = new HashSet<Payment>();
		rentalRequest.getPayments().forEach(payment -> {
			Payment p;
			try {
				p = (Payment) payment.clone();
				payment.setCustomer(currentCustomer);
				payment.setRental(rentalFound);
				payments.add(p);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			
		});
		
		return payments;
	}*/
	
}

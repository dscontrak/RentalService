package com.hcl.traning.rentail.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.traning.rentail.dao.CustomerRepository;
import com.hcl.traning.rentail.dao.FilmRepository;
import com.hcl.traning.rentail.dao.PaymentRepository;
import com.hcl.traning.rentail.dao.RentalFilmRepository;
import com.hcl.traning.rentail.dao.RentalRepository;
import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalDto;
import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.model.Payment;
import com.hcl.traning.rentail.model.Rental;
import com.hcl.traning.rentail.model.RentalFilms;
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
	
	@Autowired
	@Qualifier("org.dozer.Mapper")
	Mapper mapper;
	
	
	
	@Override
	@Transactional(readOnly = false)
	public RentalDto add(RentalDto rental) {
					
		
		Customer customer = daoCustomer.findOne(rental.getCustomer().getId()) ;
		rental.setCustomer(mapper.map(customer, CustomerDto.class));
		rental.setCode(codeGenerator.generate());
		
		// Set initial status Queue
		rental.setStatus("Q");
		
		Rental rentalDb = mapper.map(rental, Rental.class);
		LocalDateTime timestamp = LocalDateTime.now();	
		rentalDb.setCreated(timestamp);	
		
		daoRental.save(rentalDb);
				
		
		// read all rental and film 
		rental.getRentalFilms().forEach(currRentalFilm -> {
			Film foundFilm = daoFilm.findOne(currRentalFilm.getFilm().getId());
			
			if(foundFilm != null) {
				
				currRentalFilm.setFilm(mapper.map(foundFilm, FilmDto.class));
				currRentalFilm.setRental(mapper.map(rentalDb, RentalDto.class));
				currRentalFilm.setReturnWithoutDue( calcuatePayment.calculateDayReturnWithoutDue(foundFilm.getTypeFilm()) );
				// Save rentalFilm register				
				daoRentalFilm.save( mapper.map(currRentalFilm, RentalFilms.class) );
			}
			
		});
		
		addPossiblePayments(rental);
		rental.setId(rentalDb.getId());
		return rental;
	}	
	
	@Override
	public void addAll(Collection<RentalDto> rentals) {
		LocalDateTime timestamp = LocalDateTime.now();
		
		rentals.forEach(r -> {			
			r.setCreated(timestamp);
			daoRental.save(mapper.map(r, Rental.class));
		});
		
		/*for (Rental c : customers) {
			
		}*/
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RentalDto> listAll() {
		
		List<RentalDto> list = new ArrayList<RentalDto>();
		
		daoRental.findAll().forEach(r -> {
			list.add(mapper.map(r, RentalDto.class));
		});
		
		return list;
	}
	
	@Override
	@Transactional(readOnly = true)
	public RentalDto getById(Long id) {
		Rental rentalFound = daoRental.findOne(id);
		/*try {
			calcuatePayment.setCustomer(rentalFound.getCustomer());
			Set<Payment> payments = calcuatePayment.calculateByFilmsToRent(rentalFound.getRentalFilms());
			rentalFound.setPossiblePayments(payments);
		} catch (CloneNotSupportedException e) {			
			e.printStackTrace();
		}*/
		
		RentalDto rentalDto = mapper.map(rentalFound, RentalDto.class);
		
		addPossiblePayments(rentalDto);
				
		return rentalDto;
	}

	@Override
	public void addPossiblePayments(RentalDto rental) {
		Set<PaymentDto> payments = null;
		
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
	public RentalDto addPaymentsToRental(RentalDto rentalRequest) {
		Rental rentalFound = daoRental.findOne(rentalRequest.getId());
		
		if(rentalFound == null) {
			throw new IllegalArgumentException("The rental not found in data base");
		}
		
		RentalDto rentalDto = mapper.map(rentalFound, RentalDto.class);
		
		
		
		if(!rentalDto.getStatus().equals("Q")) {
			throw new IllegalArgumentException("The rental has a differnt status to Queue");
		}
		
		rentalDto.setPayments(rentalRequest.getPayments());		
		rentalDto.getPayments().forEach(p -> {
			p.setCustomer(rentalDto.getCustomer());
			p.setRental(rentalDto);			
			
			
			
		});
		
		// Calculate inventory and available 
		rentalDto.getRentalFilms().forEach(rf -> {
			FilmDto film = calculateInventory.getFilmToUpdateAgaistInventory(rf.getFilm(), rf.getAmount());
			daoFilm.save(mapper.map(film, Film.class));
		});
		
		
		// Update custumer's bonus
		CustomerDto customer = rentalDto.getCustomer();
		customer.setBonus(calcuatePayment.getCustomerWithBonus(rentalDto.getRentalFilms()));				
		daoCustomer.save(mapper.map(customer, Customer.class));
		
		// Update rental status 
		rentalDto.setStatus("A");
		daoRental.save(mapper.map(rentalDto, Rental.class));
		
		// Save payments
		//daoPayment.saveAll(rentalFound.getPayments());		
		rentalDto.getPayments().forEach(p -> {
			daoPayment.save(mapper.map(p, Payment.class));
		});
		
		
		return mapper.map(rentalDto, RentalDto.class);
	}
	
	@Override
	public RentalDto returnRental(RentalDto rentalRequest) {
		Rental rentalDb = daoRental.findOne(rentalRequest.getId());
		
		if(rentalDb == null) {
			throw new IllegalArgumentException("The rental not found in data base");
		}
		
		RentalDto rentalFound = mapper.map(rentalDb, RentalDto.class);   
		// Validate rental
		
		
		
		if(!rentalFound.getStatus().equals("A")) {
			throw new IllegalArgumentException("The rental has a differnt status to Active");
		}
		
		// Add possible payments to do when return.
		// addPossiblePayments(rentalRequest);
		
		// Update inventory
		rentalFound.getRentalFilms().forEach(rf -> {
			int amountReturn = calculateInventory.getAmountToUpdateFilmReturn(
					rf,
					rentalFound.getRentalFilms()
					);
			
			FilmDto film = rf.getFilm();
			film.setInventoryStore(film.getInventoryStore() + amountReturn);
			film.setInventoryRent(film.getInventoryRent() - amountReturn);
			
			daoFilm.save(mapper.map(film, Film.class));
		});
		
		// Update rental 
		rentalFound.setStatus("D");
		daoRental.save(mapper.map(rentalFound, Rental.class));
		
		
		return mapper.map(rentalFound, RentalDto.class) ;
	}
	
	@Transactional(readOnly = true)
	public Set<RentalFilmsSerialize> getDataRentalFilms(Long id){
		Set<RentalFilmsSerialize> filmsSerializes = new HashSet<RentalFilmsSerialize>();
		//Rental rental = daoRentalFilm.findRentalByRental(id).getRental(); // rentalFilmService.findAllByRental(id);
		Rental rental = daoRental.findOne(id);
		
		if(rental == null) {
			throw new IllegalArgumentException("The rental not found in data base");
		}
		
		
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

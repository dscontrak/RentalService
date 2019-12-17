package com.hcl.traning.rentail.util;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.hcl.traning.rentail.model.Customer;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.model.Payment;

import com.hcl.traning.rentail.model.RentalFilms;

@Component
public class CalcuatePayment {

	private Customer customer;

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Set<Payment> calculateChargeByLateReturn(Set<RentalFilms> rentalFilms){
		
		Payment paymentCharges = new Payment();
		LocalDate today = LocalDate.now();
		Double amount = 0.0;
		Set<Payment> payments = new HashSet<>();
		for(RentalFilms rf: rentalFilms) {
			
			if (rf.getFilm() == null) {
				continue;
			}
			Film film = rf.getFilm();
			Period period = Period.between(rf.getReturnWithoutDue().toLocalDate(), today);
			int daysLate = period.getDays();
			if(daysLate > 0) {
				if (film.getType().equals("N")) {
					amount += 40.0 * daysLate;
				} else if (film.getType().equals("R")) {
					if(daysLate > 3) {
						amount += 30.0 * daysLate;
					}					
				} else {
					if(daysLate > 5) {
						amount += 30.0 * daysLate;
					}
					
				}
			}
			
			
		}
		
		paymentCharges.setAmount(new BigDecimal(amount));
		paymentCharges.setTypePayment("M");
		paymentCharges.setReasonPayment("D");

		payments.add(paymentCharges);
		
		return payments;
	}

	public Set<Payment> calculateByFilmsToRent(Set<RentalFilms> rentalFilms) throws CloneNotSupportedException {
		Double amount = 0.0;
		Set<Payment> payments = new HashSet<>();

		Payment paymentMoney = new Payment();
		Payment paymentBonus = null;
		
		if(customer == null) {
			throw new IllegalArgumentException("The Customer is requerid");
		}

		// Calculate with only cash
		// paymentMoney.setCustomer(customer);
		paymentMoney.setReasonPayment("N");

		for (RentalFilms rf : rentalFilms) {

			if (rf.getFilm() == null) {
				continue;
			}
			Film film = rf.getFilm();

			if (film.getType().equals("N")) {
				amount += 40.0;
			} else if (film.getType().equals("R")) {
				amount += 30.0;
			} else {
				amount += 30.0;
			}
		}

		if (customer.getBonus() >= 25) {
			paymentBonus = (Payment) paymentMoney.clone();
			int numberByBonus = customer.getBonus() % 2;			

			paymentBonus.setAmount(new BigDecimal(numberByBonus));
			paymentBonus.setTypePayment("B");

		}

		paymentMoney.setAmount(new BigDecimal(amount));
		paymentMoney.setTypePayment("M");

		payments.add(paymentMoney);
		if (paymentBonus != null) {
			payments.add(paymentBonus);
		}

		return payments;

	}

	public Date calculateDayReturnWithoutDue(Film film) {
		Date dateToReturn = null;
		

		// current date
		LocalDate today = LocalDate.now();

		// adding one day to the localdate
		LocalDate datePromiseToRetun;

		if (film.getType().equalsIgnoreCase("N")) {
			datePromiseToRetun = today.plusDays(1);
		}else if (film.getType().equalsIgnoreCase("R")) {
			datePromiseToRetun = today.plusDays(3);
		}else if (film.getType().equalsIgnoreCase("O")) {
			datePromiseToRetun = today.plusDays(5);
		}else {
			datePromiseToRetun = today.plusDays(1);
		}
		
		dateToReturn = Date.valueOf(datePromiseToRetun);

		return dateToReturn;
	}
	
	public int getCustomerWithBonus(Set<RentalFilms> rentalFilms) {
		int amountBonus = 0;
				
		amountBonus += 2*(rentalFilms.stream().filter(rf -> rf.getFilm().getType().equalsIgnoreCase("N")).mapToInt(rf -> rf.getAmount()).sum());
		amountBonus += (rentalFilms.stream().filter(rf -> !rf.getFilm().getType().equalsIgnoreCase("N")).mapToInt(rf -> rf.getAmount()).sum());				
		
		return amountBonus;
	} 

}

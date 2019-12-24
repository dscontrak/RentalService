package com.hcl.traning.rentail.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.hcl.traning.rentail.mapper.CustomerDto;
import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.mapper.PaymentDto;
import com.hcl.traning.rentail.mapper.RentalFilmsDto;
import com.hcl.traning.rentail.mapper.TypeFilmDto;
import com.hcl.traning.rentail.model.Film;
import com.hcl.traning.rentail.model.TypeFilm;


@Component
public class CalcuatePayment {

	private CustomerDto customer;

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	
	public Set<PaymentDto> calculateChargeByLateReturn(Set<RentalFilmsDto> rentalFilms){
		
		PaymentDto paymentCharges = new PaymentDto();
		LocalDate today = LocalDate.now();
		Double amount = 0.0;
		Set<PaymentDto> payments = new HashSet<>();
		for(RentalFilmsDto rf: rentalFilms) {
			
			if (rf.getFilm() == null) {
				continue;
			}
			FilmDto film = rf.getFilm();
			TypeFilmDto typeFilmDto = film.getTypeFilm();
			
			Period period = Period.between(rf.getReturnWithoutDue(), today);
			
			int daysLate = period.getDays();
			
			if(daysLate > 0) {
				if (typeFilmDto != null && typeFilmDto.getDaysToReturn() > daysLate) {
					amount += typeFilmDto.getPrice() * daysLate;							
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

	public Set<PaymentDto> calculateByFilmsToRent(Set<RentalFilmsDto> rentalFilms) throws CloneNotSupportedException {
		Double amount = 0.0;
		Set<PaymentDto> payments = new HashSet<>();

		PaymentDto paymentMoney = new PaymentDto();
		PaymentDto paymentBonus = null;
		
		if(customer == null) {
			throw new IllegalArgumentException("The Customer is requerid");
		}

		// Calculate with only cash
		// paymentMoney.setCustomer(customer);
		paymentMoney.setReasonPayment("N");

		for (RentalFilmsDto rf : rentalFilms) {

			if (rf.getFilm() == null) {
				continue;
			}
			FilmDto film = rf.getFilm();
			TypeFilmDto typeFilmDto = film.getTypeFilm();
			
			if(typeFilmDto != null && typeFilmDto.getPrice() > 0) {
				amount += typeFilmDto.getPrice();
			}else {
				amount += 40.0;
			}
			
			
		}

		if (customer.getBonus() >= 25) {
			paymentBonus = (PaymentDto) paymentMoney.clone();
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

	public LocalDate calculateDayReturnWithoutDue(TypeFilm film) {
		//Date dateToReturn = null;
		
		// current date
		LocalDate today = LocalDate.now();

		// adding one day to the localdate
		LocalDate datePromiseToRetun;

		if (film != null && film.getDaysToReturn() > 0) {
			datePromiseToRetun = today.plusDays(film.getDaysToReturn());
		}else {
			datePromiseToRetun = today.plusDays(1);
		}
		
		//dateToReturn = Date.valueOf(datePromiseToRetun);

		return datePromiseToRetun;
	}
	
	public int getCustomerWithBonus(Set<RentalFilmsDto> rentalFilms) {
		int amountBonus = 0;
				
		amountBonus += 2*(rentalFilms.stream().filter(rf -> rf.getFilm().getTypeFilm().getPrice() >= 40).mapToInt(rf -> rf.getAmount()).sum());
		amountBonus += (rentalFilms.stream().filter(rf -> rf.getFilm().getTypeFilm().getPrice() < 40).mapToInt(rf -> rf.getAmount()).sum());				
		
		return amountBonus;
	} 

}

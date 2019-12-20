package com.hcl.traning.rentail.util;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.hcl.traning.rentail.mapper.FilmDto;
import com.hcl.traning.rentail.mapper.RentalFilmsDto;

@Component
public class CalculateInventory {
	
	
	public FilmDto getFilmToUpdateAgaistInventory(FilmDto film, int amountRent){
				
		if(amountRent > film.getInventoryStore()) {
			throw new IllegalArgumentException("There are not inventory to film: " + film.getTitle());
		}
		
		film.setInventoryRent( film.getInventoryRent() + amountRent );
		film.setInventoryStore(film.getInventoryStore() - amountRent );
				
		return film;
	}

	public int getAmountToUpdateFilmReturn(RentalFilmsDto requestRf, Set<RentalFilmsDto> dbRf) {
		Long idFilm =  requestRf.getFilm().getId();		
		RentalFilmsDto rentalFilmFound = dbRf.stream().filter(rf -> rf.getFilm().getId() == idFilm).findAny().orElse(null);
		
		
		if(rentalFilmFound == null) {
			throw new IllegalArgumentException("The film doesnt exist on rent");
		}
		
		if(rentalFilmFound.getAmount() != requestRf.getAmount()) {
			throw new IllegalArgumentException("The amount to return is differnte then the amount currently on rent");
		}
		
		return requestRf.getAmount();
	}

	

	/* public Film getFilmToUpdateAgaistReturn(Rental rentalFound, Rental rentalRequest) {
		if(amountReturn != film.getInventoryStore()) {
			throw new IllegalArgumentException("There are not inventory to film: " + film.getTitle());
		}
		
		return null;
	}*/

	
	
}

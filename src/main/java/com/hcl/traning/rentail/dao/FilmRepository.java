package com.hcl.traning.rentail.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.traning.rentail.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

	//void save(Film film);

	//void update(Film film);

	//void updateAll(Set<Film> films);
	
	//void delete(Film film);

	//List<Film> findAll();

	//Film getById(Long id);

}
package br.com.next.desafio.spring.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.next.desafio.spring.entities.House;
import jakarta.websocket.server.PathParam;

public interface HouseRepository extends JpaRepository<House, String>{
	List<House> findByZipcode(@PathParam("zipcode") String zipcode);
}
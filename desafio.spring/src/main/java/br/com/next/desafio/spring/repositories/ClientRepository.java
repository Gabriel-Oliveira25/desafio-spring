package br.com.next.desafio.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.next.desafio.spring.entities.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
	Client findBySocialSecurity(@Param("social_security")String socialSecurity); // select * from client where social_security = socialSecurity;
}

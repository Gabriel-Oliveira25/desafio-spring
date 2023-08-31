package br.com.next.desafio.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.next.desafio.spring.entities.Client;
import jakarta.websocket.server.PathParam;

public interface ClientRepository extends JpaRepository<Client, String>{
	Client findBySocialSecurity(@PathParam("social_security")String socialSecurity); // select * from client where social_security = socialSecurity;
}

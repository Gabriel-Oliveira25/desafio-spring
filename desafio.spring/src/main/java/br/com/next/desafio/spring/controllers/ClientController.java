package br.com.next.desafio.spring.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.desafio.spring.dto.request.ClientRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.entities.Client;
import br.com.next.desafio.spring.services.ClientServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/client")
public class ClientController {
	
	private final ClientServices service;
	
	@PostMapping
	public ResponseEntity<GenericResponseDTO> createClient(@RequestBody ClientRequestDTO request){
		return new ResponseEntity<GenericResponseDTO>(service.createClient(request) , HttpStatus.CREATED);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Client> findClientById(@PathVariable(name="id") String id){
		return new ResponseEntity<Client>(service.findClientById(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> getAllClients(){
		return new ResponseEntity<List<Client>>(service.getAllClients(), HttpStatus.OK);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<GenericResponseDTO> updateClient(@PathVariable(name="id") String id, @RequestBody ClientRequestDTO request){
		return new ResponseEntity<GenericResponseDTO>(service.updateClient(id, request), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<GenericResponseDTO> deleteClient(@PathVariable(name="id") String id){
		return new ResponseEntity<GenericResponseDTO>(service.deleteClient(id), HttpStatus.OK);
	}
}

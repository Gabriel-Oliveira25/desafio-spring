package br.com.next.desafio.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.desafio.spring.dto.request.ClientRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
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
	
}

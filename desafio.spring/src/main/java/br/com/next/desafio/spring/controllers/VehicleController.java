package br.com.next.desafio.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.desafio.spring.dto.request.VehicleRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.entities.Client;
import br.com.next.desafio.spring.services.VehicleServices;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/vehicle")
public class VehicleController {
	
	private final VehicleServices service;
	
	@PostMapping
	public ResponseEntity<GenericResponseDTO> registerVehicle(@RequestBody VehicleRequestDTO request){
		return new ResponseEntity<GenericResponseDTO>(service.registerVehicle(request) , HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<GenericResponseDTO> deleteClient(@PathVariable(name="id") String id){
		return new ResponseEntity<GenericResponseDTO>(service.deleteVehicle(id), HttpStatus.OK);
	}
	
	@GetMapping(value="/{id}/client")
	public ResponseEntity<Client> getClientsByVehicleId(@PathVariable(name="id") String id){
		return new ResponseEntity<Client>(service.getClientsByVehicleId(id), HttpStatus.OK);
	}
}

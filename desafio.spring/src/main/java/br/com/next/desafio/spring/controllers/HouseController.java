package br.com.next.desafio.spring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.next.desafio.spring.dto.request.HouseRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.entities.House;
import br.com.next.desafio.spring.services.HouseServices;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api/v1/house")
public class HouseController {
	
	private final HouseServices service;
	
    @GetMapping
    public ResponseEntity<List<House>> listAll(@RequestParam Optional<String> zipcode) {
        return new ResponseEntity<List<House>>(this.service.listAll(zipcode), HttpStatus.OK);
    }
	
	@PostMapping
	public ResponseEntity<GenericResponseDTO> createHouse(@RequestBody HouseRequestDTO request){
		return new ResponseEntity<GenericResponseDTO>(this.service.createHouse(request), HttpStatus.OK);
	}
	
	@PutMapping(value="{id}")
	public ResponseEntity<GenericResponseDTO> updateOwnershipStatus(@PathParam(value="id") String id, @RequestBody HouseRequestDTO request) {
		return new ResponseEntity<GenericResponseDTO>(this.service.updateOwnershipStatus(id, request), HttpStatus.OK);
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<GenericResponseDTO> deleteHouse(@PathParam(value="id") String id){
		return new ResponseEntity<GenericResponseDTO>(this.service.deleteHouse(id), HttpStatus.OK);
	}
}

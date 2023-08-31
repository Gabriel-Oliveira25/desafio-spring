package br.com.next.desafio.spring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.next.desafio.spring.dto.request.HouseRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.entities.House;
import br.com.next.desafio.spring.exceptions.GeneralException;
import br.com.next.desafio.spring.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HouseServices {

	private final HouseRepository repository;

	
	//BUGGED -> GET TO IT TOMORROW
	public List<House> listByZipcode(String zipcode){
		return repository.findByZipcode(zipcode);
	}
	
	public GenericResponseDTO createHouse(HouseRequestDTO request) {
		House entity = new House();
		BeanUtils.copyProperties(request, entity);
		repository.save(entity);
		return GenericResponseDTO.builder().message("House created").status(201).build();
	}

	public GenericResponseDTO updateOwnershipStatus(String id, HouseRequestDTO request) {
		Optional.ofNullable(repository.findById(id).orElse(null)).ifPresentOrElse(house -> {
			House entity = repository.findById(id).orElse(null);
			BeanUtils.copyProperties(request, entity);
			repository.save(entity);
		}, () -> {
			throw new GeneralException("House not found in our database", HttpStatus.NOT_FOUND);
		});

		return GenericResponseDTO.builder().message("House information updated").status(200).build();
	}

	public GenericResponseDTO deleteHouse(String id) {
		Optional.ofNullable(repository.findById(id).orElse(null)).ifPresentOrElse(house -> {
			repository.deleteById(id);
		}, () -> {
			throw new GeneralException("Client not found in our database", HttpStatus.NOT_FOUND);
		});
		return GenericResponseDTO.builder().message("House deleted").status(200).build();
	}
}

package br.com.next.desafio.spring.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.next.desafio.spring.dto.request.VehicleRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.entities.Client;
import br.com.next.desafio.spring.entities.Vehicle;
import br.com.next.desafio.spring.exceptions.GeneralException;
import br.com.next.desafio.spring.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class VehicleServices {

	private final VehicleRepository repository;

	public GenericResponseDTO registerVehicle(VehicleRequestDTO request) {
		Optional.ofNullable(repository.findByPlateNumber(request.getPlateNumber()))
		.ifPresentOrElse(vehicle -> {
			throw new GeneralException("Vehicle already registered in our database", HttpStatus.CONFLICT);
		}, () -> {
			Vehicle entity = new Vehicle();
			BeanUtils.copyProperties(request, entity);
			repository.save(entity);
		});
		return GenericResponseDTO.builder().message("Vehicle created").status(201).build();
	}

	public GenericResponseDTO deleteVehicle(String id) {
		Optional.ofNullable(repository.findById(id).orElse(null)).ifPresentOrElse(vehicle -> {
			repository.deleteById(id);
		}, () -> {
			throw new GeneralException("Vehicle not found in our database", HttpStatus.NOT_FOUND);
		});
		return GenericResponseDTO.builder().message("Vehicle deleted").status(200).build();
	}

	public Client getClientsByVehicleId(String id) {
		return repository.findById(id).orElseThrow(() -> new GeneralException("Client not found in our database", HttpStatus.NOT_FOUND)).getClient();

	}
}

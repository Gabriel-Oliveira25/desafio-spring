package br.com.next.desafio.spring.dto.request;

import br.com.next.desafio.spring.entities.Client;
import lombok.Data;

@Data
public class VehicleRequestDTO {

	private String brand;
	private String model;
	private int year;
	private String plateNumber;
	private Client client;
}

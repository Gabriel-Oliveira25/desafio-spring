package br.com.next.desafio.spring.dto.request;

import br.com.next.desafio.spring.entities.Client;
import br.com.next.desafio.spring.enums.OwnershipStatus;
import lombok.Data;

@Data
public class HouseRequestDTO {

	private OwnershipStatus ownershipStatus;
	private String location;
	private String zipcode;
	private Client client;
}

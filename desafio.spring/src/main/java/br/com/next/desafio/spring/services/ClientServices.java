package br.com.next.desafio.spring.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.next.desafio.spring.dto.request.ClientRequestDTO;
import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.entities.Client;
import br.com.next.desafio.spring.exceptions.GeneralException;
import br.com.next.desafio.spring.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientServices {
	
	private final ClientRepository repository; //ser imutável, caso hacker queira fazer injeção de dependências no repositório, ser impossível. (PROGRAMAÇÃO DEFENSIVA).
	//BEANS NÃO SE MUDAM
	
	public GenericResponseDTO createClient(ClientRequestDTO request){
		Optional.ofNullable(repository.findBySocialSecurity(request.getSocialSecurity()))
        .ifPresentOrElse(client -> {
           throw new GeneralException("client is already registered", HttpStatus.CONFLICT);
        }, () -> {
    		Client entity = new Client();
    		BeanUtils.copyProperties(request, entity);
    		repository.save(entity);
        }); 
		
		return GenericResponseDTO.builder().message("Client created").status(201).build();
	}
	
	
	
}

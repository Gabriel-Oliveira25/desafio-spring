package br.com.next.desafio.spring.services;

import java.time.LocalDateTime;
import java.util.List;
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
           throw new GeneralException("client is already registered in our database", HttpStatus.CONFLICT);
        }, () -> {
    		Client entity = new Client();
    		BeanUtils.copyProperties(request, entity); 
    		repository.save(entity);
        }); 
		
		return GenericResponseDTO.builder().message("Client created").status(201).build();
	}
	
	public Client findClientById(String id) {
		return repository.findById(id).orElseThrow(() -> new GeneralException("Client not found in our database", HttpStatus.NOT_FOUND));
	}
	
	public List<Client> getAllClients(){
		return repository.findAll();
	}
	
	public GenericResponseDTO updateClient(String id, ClientRequestDTO request) {
		Optional.ofNullable(repository.findById(id).orElse(null))
		.ifPresentOrElse(client -> {
			Client entity = repository.findById(id).orElse(null);			
			BeanUtils.copyProperties(request, entity);		
			entity.setUpdatedAt(LocalDateTime.now());
			repository.save(entity);
			
		}, () -> {
			throw new GeneralException("Client not found in our database", HttpStatus.NOT_FOUND);
		});
		
		return GenericResponseDTO.builder().message("Client information updated").status(200).build();
	}
	
	public GenericResponseDTO deleteClient(String id) {
		Optional.ofNullable(repository.findById(id).orElse(null))
		.ifPresentOrElse(client -> {
			repository.deleteById(id);
		}, () -> {
			throw new GeneralException("Client not found in our database", HttpStatus.NOT_FOUND);
		});
		return GenericResponseDTO.builder().message("Client deleted").status(200).build();
	}
}


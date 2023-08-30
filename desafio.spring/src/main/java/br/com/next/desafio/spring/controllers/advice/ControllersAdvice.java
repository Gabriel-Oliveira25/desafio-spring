package br.com.next.desafio.spring.controllers.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.next.desafio.spring.dto.response.GenericResponseDTO;
import br.com.next.desafio.spring.exceptions.GeneralException;

@ControllerAdvice
public class ControllersAdvice {
	@ExceptionHandler(GeneralException.class)
    public ResponseEntity<GenericResponseDTO> generalException(GeneralException ex) {
        return new ResponseEntity<GenericResponseDTO>(GenericResponseDTO.builder().message(ex.getMessage()).status(ex.getStatusCode()).build(), ex.getStatus());
    }
}

package br.com.next.desafio.spring.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponseDTO {

	private String message;
	private Integer status;
}

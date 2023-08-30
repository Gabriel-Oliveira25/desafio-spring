package br.com.next.desafio.spring.dto.request;

import br.com.next.desafio.spring.enums.MaritalStatus;
import lombok.Data;

@Data
public class ClientRequestDTO {
	

    private String name;
    private String socialSecurity;
    private Integer age;
    private Integer dependents;
    private Double income;
    private MaritalStatus maritalStatus;
}

package br.com.next.desafio.spring.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.next.desafio.spring.enums.InsuranceAnalysis;
import br.com.next.desafio.spring.enums.InsuranceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@Entity
@Table(name = "insurance")
public class Insurance {

	@Id
	@UuidGenerator
	@Column(nullable = false, updatable = false, unique = true)
	private String id;

	@Column(name="type", nullable = false, updatable = false)
	private InsuranceType type;
	
	@Column(name="risk", nullable = false, updatable = false)
	private Integer risk;
	
	@Column(name="insurance_analysis", nullable = false, updatable = false)
	private InsuranceAnalysis insuranceAnalysis;
	
	@Column(name="observation", nullable = true, updatable = false)
	private String observation;
	
	@Column(name="created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name="validated_at", nullable = false, updatable = false)
	private LocalDateTime validatedAt;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="client_id") 
	//USADO PRA ESTABELECER RELAÇÕES ENTRE DUAS ENTIDADES DO BANCO DE DADOS
	private Client client;
}

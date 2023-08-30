package br.com.next.desafio.spring.entities;

import org.hibernate.annotations.UuidGenerator;

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
@Table(name = "vehicle")
public class Vehicle {

	@Id
	@UuidGenerator
	@Column(nullable = false, updatable = false, unique = true)
	private String id;
	
	@Column(name="brand", nullable=false, updatable=false)
	private String brand;
	
	@Column(name="model", nullable=false, updatable=false)
	private String model;
	
	@Column(name="year", nullable=false, updatable=false)
	private Integer year;
	
	@ManyToOne
	@JoinColumn(name="client_id") 
	private Client client;
}

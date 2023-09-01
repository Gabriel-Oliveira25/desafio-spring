package br.com.next.desafio.spring.entities;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.next.desafio.spring.enums.OwnershipStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "house")
public class House {

	@Id
	@UuidGenerator
	@Column(nullable = false, updatable = false, unique = true)
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(name = "ownership_status", nullable = false)
	private OwnershipStatus ownershipStatus;

	@Column(name = "location", nullable = false, updatable = false)
	private String location;

	@Column(name = "zipcode", nullable = false, updatable = false)
	private String zipcode;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "client_id")
	// USADO PRA ESTABELECER RELAÇÕES ENTRE DUAS ENTIDADES DO BANCO DE DADOS
	private Client client;

}

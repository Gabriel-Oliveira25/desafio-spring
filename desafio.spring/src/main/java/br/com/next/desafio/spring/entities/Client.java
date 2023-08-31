package br.com.next.desafio.spring.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import br.com.next.desafio.spring.enums.MaritalStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "client")
public class Client {

	@Id
	@UuidGenerator
	@Column(nullable = false, updatable = false, unique = true)
	private String id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name="social_security", nullable=false, updatable=false, unique=true)
	private String socialSecurity;

	@Column(name = "age", nullable = false)
	private Integer age;

	@Column(name="dependents", nullable = false)
	private Integer dependents;

	@Column(name="income", nullable = false)
	private Double income;

	@Enumerated(EnumType.STRING) // TRATAR O VALOR DA CLASSE ENUM COMO STRING E SÓ PODE SER ACEITO OS DOIS VALORES
	@Column(name="marital_status", nullable = false)
	private MaritalStatus maritalStatus;

	@Column(name="created_at",nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true) //mappedBy= INDICA O OBJETO QUE ESTÁ FAZENDO A RELAÇÃO ENTRE AS DUAS CLASSES
	//fetch = define por padrão que o relacionamento dos objetos seja feito de uma maneira "preguiçosa", sendo assim, os dados das casas do cliente só são "vistos" se você der um get clientHouses. 
	//orphanRemovel = caso seja removido da coleção, remove automaticamente do banco de dados
	private List<House> clientHouses;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private Set<Vehicle> clientVehicle;
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	private Set<Insurance> clientInsurance;
	
}

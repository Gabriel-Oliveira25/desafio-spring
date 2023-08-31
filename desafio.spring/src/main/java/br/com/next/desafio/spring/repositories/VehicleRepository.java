package br.com.next.desafio.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.next.desafio.spring.entities.Vehicle;
import jakarta.websocket.server.PathParam;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
	Vehicle findByPlateNumber(@PathParam("plate_number") String plateNumber);
}

package com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;

import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String>{
	

	List<Cliente> findByCedula(String cedula);
	List<Cliente> deleteByCedula(String cedula);
	List<Cliente> findByNombreCompleto(String nombreCompleto);
	

}

package com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.model.Cliente;


import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.repository.ClienteRepository;


@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/cliente")
	public ResponseEntity<List<Cliente>> getAllClientes(@RequestParam(required = false) String cedula) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();

			if (cedula == null) {
				clienteRepository.findAll().forEach(clientes::add);
			} else {
				clienteRepository.findByCedula(cedula).forEach(clientes::add);
			}

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/cliente/{cedula}")
	public ResponseEntity<Cliente> getClienteByCedula(@PathVariable("cedula") String cedula) {
		List<Cliente> clienteData = clienteRepository.findByCedula(cedula);

		if (clienteData!=null) {
			return new ResponseEntity<>(clienteData.get(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*
	
	@GetMapping("/cliente/{cedula}")
	public ResponseEntity<List<Cliente>> findByCedula(@PathVariable("cedula") String cedula) {
		try {
			List<Cliente> clientes = clienteRepository.findByCedula(cedula);

			if (clientes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	*/
	
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente user) {
		try {
			Cliente _cliente = clienteRepository.save(
					new Cliente(user.getCedula(), user.getNombreCompleto(), user.getDireccion(), user.getTelefono(), user.getCorreo()));
			return new ResponseEntity<>(_cliente, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/cliente/{cedula}")
	public ResponseEntity<HttpStatus> deleteClientes(@PathVariable("cedula") String cedula) {
		try {
			clienteRepository.deleteByCedula(cedula);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/** 
	@PutMapping("/cliente/{id}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") String id, @RequestBody Cliente user) {
		Optional<Cliente> clienteData = clienteRepository.findById(id);

		if (clienteData.isPresent()) {
			Cliente _cliente = clienteData.get();
			_cliente.setCedula(user.getCedula());
			_cliente.setNombreCompleto(user.getNombreCompleto());
			_cliente.setDireccion(user.getDireccion());
			_cliente.setTelefono(user.getTelefono());
			_cliente.setCorreo(user.getCorreo());
			
			
			return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}
	**/
	@PutMapping("/cliente/{cedula}")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("cedula") String cedula, @RequestBody Cliente user) {
		List<Cliente> clienteData = clienteRepository.findByCedula(cedula);

		if (clienteData!=null) {
			Cliente _cliente = clienteData.get(0);
			_cliente.setCedula(user.getCedula());
			_cliente.setNombreCompleto(user.getNombreCompleto());
			_cliente.setDireccion(user.getDireccion());
			_cliente.setTelefono(user.getTelefono());
			_cliente.setCorreo(user.getCorreo());
			
			
			return new ResponseEntity<>(clienteRepository.save(_cliente), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	}

	

	

}

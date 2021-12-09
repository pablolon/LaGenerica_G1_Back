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

import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.model.Usuario;
import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.repository.UsuarioRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;

	@GetMapping("/usuario")
	public ResponseEntity<List<Usuario>> getAllUsuarios(@RequestParam(required = false) String username) {
		try {
			List<Usuario> usuarios = new ArrayList<Usuario>();

			if (username == null) {
				usuarioRepository.findAll().forEach(usuarios::add);
			} else {
				usuarioRepository.findByUsername(username).forEach(usuarios::add);
			}

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	

	@GetMapping("/usuario/{username}")
	public ResponseEntity<Usuario> getUsuarioByUsername(@PathVariable("username") String username) {
		List<Usuario> usuarioData = usuarioRepository.findByUsername(username);

		if (usuarioData!=null) {
			return new ResponseEntity<>(usuarioData.get(0), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/usuario")
	public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario user) {
		try {
			Usuario _usuario = usuarioRepository.save(
					new Usuario(user.getUsername(), user.getPassword(), user.getNombrecompleto(), user.getEmail()));
			return new ResponseEntity<>(_usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/usuario/{username}")
	public ResponseEntity<Usuario> updateUsuario(@PathVariable("username") String username, @RequestBody Usuario user) {
		List<Usuario> usuarioData = usuarioRepository.findByUsername(username);

		if (usuarioData!=null) {
			Usuario _usuario = usuarioData.get(0);
			_usuario.setUsername(user.getUsername());
			_usuario.setPassword(user.getPassword());
			_usuario.setNombrecompleto(user.getNombrecompleto());
			_usuario.setEmail(user.getEmail());
			return new ResponseEntity<>(usuarioRepository.save(_usuario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/usuario/{username}")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable("username") String username) {
		try {
			usuarioRepository.deleteByUsername(username);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/usuarios")
	public ResponseEntity<HttpStatus> deleteAllUsuarioss() {
		try {
			usuarioRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/usuarios/{username}")
	public ResponseEntity<List<Usuario>> findByUsername(@PathVariable("username") String username) {
		try {
			List<Usuario> usuarios = usuarioRepository.findByUsername(username);

			if (usuarios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

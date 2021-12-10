package com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
import org.springframework.web.bind.annotation.RestController;

import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.model.Venta;
import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.repository.VentaRepository;

@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class VentaController {

	@Autowired
	VentaRepository ventaRepository;

	@GetMapping("/venta/consecutivo")
	public ResponseEntity<Long> getVentaConsecutivo() {
		try {
		ArrayList<Venta> aux = (ArrayList<Venta>) ventaRepository.findAll();
		long mayor = 0;
		for (Venta v : aux) {
			if (v.getCodigoventa() > mayor) {
				mayor = v.getCodigoventa();
			}
		}
		if (aux.isEmpty()) {
			mayor=1;
		}
		
			return new ResponseEntity<>(mayor, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/venta")
	public ResponseEntity<List<Venta>> getAllVenta() {
		try {
			List<Venta> venta = new ArrayList<Venta>();

			ventaRepository.findAll().forEach(venta::add);

			if (venta.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(venta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/venta/id/{id}")
	public ResponseEntity<Venta> getVentaById(@PathVariable("id") String id) {
		Optional<Venta> ventaData = ventaRepository.findById(id);

		if (ventaData.isPresent()) {
			return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/venta/codigo/{codigo}")
	public ResponseEntity<Venta> getVentaByCodigoventa(@PathVariable("codigo") Long codigo) {
		Venta aux = ventaRepository.findByCodigoventa(codigo).get(0);
		Optional<Venta> ventaData = Optional.of(aux);

		if (ventaData.isPresent()) {
			return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/venta/cedula/{cedula}")
	public ResponseEntity<ArrayList<Venta>> getVentaByCedulacliente(@PathVariable("cedula") Long cedula) {
		ArrayList<Venta> aux = (ArrayList<Venta>) ventaRepository.findByCedulacliente(cedula);
		Optional<ArrayList<Venta>> ventaData = Optional.of(aux);

		if (ventaData.isPresent()) {
			return new ResponseEntity<>(ventaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/venta")
	public ResponseEntity<Venta> createVenta(@RequestBody Venta sale) {
		try {
			Venta _venta = ventaRepository.save(new Venta(sale.getCedulacliente(), sale.getCodigoventa(),
					sale.getDetalleventa(), sale.getIvaventa(), sale.getTotalventa(), sale.getValorventa()));
			return new ResponseEntity<>(_venta, HttpStatus.CREATED);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<>(null, HttpStatus.IM_USED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// RECOMENDACIÓN, ENVIAR JSON SIN ID PERO EL SI ES OBLIGATORIO EN LA URL
	@PutMapping("/venta/id/{id}")
	public ResponseEntity<Venta> updateVentaById(@PathVariable("id") String id, @RequestBody Venta sale) {
		Optional<Venta> ventaData = ventaRepository.findById(id);

		if (ventaData.isPresent()) {
			Venta _venta = ventaData.get();
			_venta.setCedulacliente(sale.getCedulacliente());
			_venta.setCodigoventa(sale.getCodigoventa());
			_venta.setDetalleventa(sale.getDetalleventa());
			_venta.setIvaventa(sale.getIvaventa());
			_venta.setTotalventa(sale.getTotalventa());
			_venta.setValorventa(sale.getValorventa());

			return new ResponseEntity<>(ventaRepository.save(_venta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/venta/codigo/{codigo}")
	public ResponseEntity<Venta> updateVentaByCodigoventa(@PathVariable("codigo") long codigo,
			@RequestBody Venta sale) {
		Venta aux = ventaRepository.findByCodigoventa(codigo).get(0);
		Optional<Venta> ventaData = Optional.of(aux);

		if (ventaData.isPresent()) {
			Venta _venta = ventaData.get();
			_venta.setCedulacliente(sale.getCedulacliente());
			_venta.setCodigoventa(sale.getCodigoventa());
			_venta.setDetalleventa(sale.getDetalleventa());
			_venta.setIvaventa(sale.getIvaventa());
			_venta.setTotalventa(sale.getTotalventa());
			_venta.setValorventa(sale.getValorventa());

			return new ResponseEntity<>(ventaRepository.save(_venta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("/venta/id/{id}")
	public ResponseEntity<HttpStatus> deleteVentaById(@PathVariable("id") String id) {
		try {
			ventaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/venta/cedula/{cedula}")
	public ResponseEntity<HttpStatus> deleteVentaByCedulacliente(@PathVariable("cedula") long cedula) {
		try {
			ventaRepository.deleteByCodigoventa(cedula);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/venta/codigo/{codigo}")
	public ResponseEntity<HttpStatus> deleteVentaByNombre(@PathVariable("codigo") Long codigo) {
		try {
			ventaRepository.deleteByCodigoventa(codigo);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/venta")
	public ResponseEntity<HttpStatus> deleteAllVenta() {
		try {
			ventaRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/venta/{cedula}")
	public ResponseEntity<List<Venta>> findByCedulacliente(@PathVariable("cedula") Long cedula) {
		try {
			List<Venta> venta = ventaRepository.findByCedulacliente(cedula);

			if (venta.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(venta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
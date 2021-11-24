package com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_clientes")
public class Cliente {
	
	@Id
	private String id;
	
	private String cedula;
	private String nombreCompleto;
	private String direccion;
	private String telefono;
	private String correo;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cedula
	 * @param nombreCompleto
	 * @param direccion
	 * @param telefono
	 * @param correo
	 */
	public Cliente(String cedula, String nombreCompleto, String direccion, String telefono, String correo) {
		super();
		this.cedula = cedula;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	

	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	

}

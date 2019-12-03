package com.springboot.data.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//Usar una interfaz itemExpensa para englobar mantenimiento y servicio. Y a traves de esa interfaz itemExpensa listar en el view de expensa los
// servicio y mantenimientos registrados 
@Entity
@Table(name="mantenimientos")
public class Mantenimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;
	
	@Column(name="proveedor")
	private String proveedor;
	
	@Column(name="tipo")
	private String mantenimientoTipo;
	
	@Column(name="precio")
	private Double precio;
	
	@Column(name="nombre")
	private String nombre;

	public Mantenimiento() {
		
	}
	
	public Mantenimiento(Long id, Edificio edificio, String proveedor, String mantenimientoTipo,
			Double precio, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.edificio = edificio;
		this.proveedor = proveedor;
		this.mantenimientoTipo = mantenimientoTipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getMantenimientoTipo() {
		return mantenimientoTipo;
	}

	public void setMantenimientoTipo(String mantenimientoTipo) {
		this.mantenimientoTipo = mantenimientoTipo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}

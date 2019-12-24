package com.springboot.data.app.models.data.entity;

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
@Table(name="mantenimiento_prueba")
public class MantenimientoPrueba implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="precio")
	private Double precio;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name = "edificio_id")
	private Edificio edificio;

	public MantenimientoPrueba() {
		
	}
	
	public MantenimientoPrueba(Long id, Double precio, String nombre) {
		super();
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
	}
	
	

	public MantenimientoPrueba(Long id, Double precio, String nombre, Edificio edificio) {
		super();
		this.id = id;
		this.precio = precio;
		this.nombre = nombre;
		this.edificio = edificio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}
	
	
	
}
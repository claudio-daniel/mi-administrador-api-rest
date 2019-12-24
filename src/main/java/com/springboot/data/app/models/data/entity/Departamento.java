package com.springboot.data.app.models.data.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="departamentos")
public class Departamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@JoinColumn(name="nombre")
	private String nombre; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Edificio edificio;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="propietario_id")
	private Propietario propietario;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inquilino_id")
	private Inquilino inquilino;
	
	@OneToMany(mappedBy="departamento", fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private List<Servicio> servicios;
	
	@OneToMany(mappedBy="departamento", fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private List<Expensa> expensas;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="departamento_estados_id")
	private DepartamentoEstado estado;
	
	@Column(name="cantidad_habitacion")
	private Integer cantidadHabitaciones;

	public Departamento() {
		
	}
	
	public Departamento(Long id, Edificio edificio, Inquilino inquilino, List<Expensa> expensas, DepartamentoEstado estado) {
		super();
		this.id = id;
		this.edificio = edificio;
		this.inquilino = inquilino;
		this.expensas = expensas;
		this.estado = estado;
	}

	public Departamento(Long id, String nombre, Direccion direccion, Propietario propietario, Inquilino inquilino,
			List<Mantenimiento> mantenimientos, List<Servicio> servicios, List<Expensa> expensas,
			DepartamentoEstado estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.propietario = propietario;
		this.inquilino = inquilino;
		this.servicios = servicios;
		this.expensas = expensas;
		this.estado = estado;
	}
	
	public Departamento(Long id, Direccion direccion, Propietario propietario, Inquilino inquilino,
			List<Mantenimiento> mantenimientos, List<Servicio> servicios, List<Expensa> expensas,
			DepartamentoEstado estado, Integer cantidadHabitaciones) {
		super();
		this.id = id;
		this.propietario = propietario;
		this.inquilino = inquilino;
		this.servicios = servicios;
		this.expensas = expensas;
		this.estado = estado;
		this.cantidadHabitaciones = cantidadHabitaciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Inquilino getInquilino() {
		return inquilino;
	}

	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}

	public List<Expensa> getExpensas() {
		return expensas;
	}

	public void setExpensas(List<Expensa> expensas) {
		this.expensas = expensas;
	}

	public DepartamentoEstado getEstado() {
		return estado;
	}

	public void setEstado(DepartamentoEstado estado) {
		this.estado = estado;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Integer getCantidadHabitaciones() {
		return cantidadHabitaciones;
	}

	public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
		this.cantidadHabitaciones = cantidadHabitaciones;
	}

	//crear add y remove servicio mantenimiento y  expensa
}

package com.springboot.data.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="edificios")
public class Edificio implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@JoinColumn(name="nombre")
	private String nombre;
	
	@OneToMany(mappedBy="edificio", fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private List<Departamento> departamentos;
	
	@OneToMany(mappedBy="edificio", fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	private List<Mantenimiento> mantenimientos;

	@OneToOne
	@JoinColumn(name="direccion_id")
	private Direccion direccion;

	public Edificio() {
		
	}
	
	public Edificio(Long id, @NotEmpty String nombre, List<Departamento> departamentos,
			List<Mantenimiento> mantenimiento, Direccion direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamentos = departamentos;
		this.mantenimientos = mantenimiento;
		this.direccion = direccion;
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

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Mantenimiento> getMantenimientos() {
		return mantenimientos;
	}

	public void setMantenimientos(List<Mantenimiento> mantenimiento) {
		this.mantenimientos = mantenimiento;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	} 
	
	
}

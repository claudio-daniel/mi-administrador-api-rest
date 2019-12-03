package com.springboot.data.app.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="expensas_item")
public class ExpensaItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mantenimiento_id")
	private Mantenimiento mantenimiento;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="servicio_id")
	private Servicio servicio;

	public ExpensaItem() {	
	}

	public ExpensaItem(Long id, Integer cantidad, Mantenimiento mantenimiento, Servicio servicio) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.mantenimiento = mantenimiento;
		this.servicio = servicio;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public Mantenimiento getMantenimiento() {
		return mantenimiento;
	}


	public void setMantenimiento(Mantenimiento mantenimiento) {
		this.mantenimiento = mantenimiento;
	}


	public Servicio getServicio() {
		return servicio;
	}


	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	public Double calcularImporte() {
		return (cantidad.doubleValue() * mantenimiento.getPrecio()) + (cantidad.doubleValue() * servicio.getPrecio()); 
	}
}

package com.springboot.data.app.models.data.entity;

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

	private Double precio;
	
	private Boolean enFactura;
	
	private Boolean cobrado;
	
	public ExpensaItem() {	
	}

	public ExpensaItem(Long id, Integer cantidad, Mantenimiento mantenimiento, Servicio servicio) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.mantenimiento = mantenimiento;
		this.servicio = servicio;
	}

	public ExpensaItem(Integer cantidad, Double precio) {
		super();
		this.cantidad = cantidad;
		this.precio = precio;
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

	public Boolean getEnFactura() {
		return enFactura;
	}

	public void setEnFactura(Boolean enFactura) {
		this.enFactura = enFactura;
	}

	public Boolean getCobrado() {
		return cobrado;
	}

	public void setCobrado(Boolean cobrado) {
		this.cobrado = cobrado;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Double calcularImporte() {
		return (cantidad.doubleValue() * mantenimiento.getPrecio()) + (cantidad.doubleValue() * servicio.getPrecio()); 
	}
}

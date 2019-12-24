package com.springboot.data.app.models.data.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="expensas")
public class Expensa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Departamento departamento;
	
	@NotEmpty
	private String descripcion;
	
	private String observacion;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy") 
	private Date createAt;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="expensa_id")
	private List<ExpensaItem> itemsExpensas;
	
	public Expensa() {
		this.itemsExpensas = new ArrayList<ExpensaItem>(); 
	}
	
	public Expensa(Long id, Departamento departamento) {
		super();
		this.id = id;
		this.departamento = departamento;
	}
	
	public Expensa(Long id, Departamento departamento, @NotEmpty String descripcion, String observacion,
			Date createAt) {
		super();
		this.id = id;
		this.departamento = departamento;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.createAt = createAt;
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<ExpensaItem> getItemsExpensas() {
		return itemsExpensas;
	}

	public void setItemsExpensas(List<ExpensaItem> itemsExpensas) {
		this.itemsExpensas = itemsExpensas;
	}
	
	public void addItemExpensa(ExpensaItem expensaItem) {
		this.itemsExpensas.add(expensaItem);
	}
	
	public Double getTotal() {
		Double total = 0.0;
		
		for (ExpensaItem e : this.itemsExpensas) {
			
			total += e.calcularImporte() != null ? e.calcularImporte() : 0.0;
		}
		
		return total;
	}
}

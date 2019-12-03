package com.springboot.data.app.models.entity;

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

/**
 * @author claudioDaniel
 *
 */
@Entity
@Table(name= "facturas")
public class Factura implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String descripcion;
	
	private String observacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Inquilino inquilino;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	@DateTimeFormat(pattern = "dd-MM-yyyy") 
	private Date createAt;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="factura_id")
	private List<ItemFactura> itemsFacturas;
	
	public Factura(){
		this.itemsFacturas = new ArrayList<ItemFactura>();
	}
	
	public Factura(Long id, String descripcion, String observacion, Inquilino inquilino, Date createAt) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.observacion = observacion;
		this.inquilino = inquilino;
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
	public Inquilino getInquilino() {
		return inquilino;
	}
	public void setInquilino(Inquilino inquilino) {
		this.inquilino = inquilino;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<ItemFactura> getItemsFacturas() {
		return itemsFacturas;
	}

	public void setItemsFacturas(List<ItemFactura> itemsFacturas) {
		this.itemsFacturas = itemsFacturas;
	} 
	
	public void addItemFactura(ItemFactura item) {
		itemsFacturas.add(item);
	}

	public Double getTotal() {
		Double total = 0.0;
		
		for (ItemFactura i : itemsFacturas) {
			
			total += i.calcularImporte() != null ? i.calcularImporte() : 0.0;
		}
		
		return total;
	}
}

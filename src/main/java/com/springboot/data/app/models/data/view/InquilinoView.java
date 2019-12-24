package com.springboot.data.app.models.data.view;

import java.io.Serializable;
import java.util.Date;

public class InquilinoView implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nombre;

	private String apellido;

	private String email;

	private Date createAt;

	public InquilinoView() {
	}

	public InquilinoView(Long id, String nombre, String apellido, String email, Date createAt) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.createAt = createAt;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return this.nombre + " " + this.apellido;
	}
}

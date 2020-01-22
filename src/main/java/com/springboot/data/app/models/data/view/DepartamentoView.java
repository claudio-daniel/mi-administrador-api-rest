package com.springboot.data.app.models.data.view;

import com.springboot.data.app.models.data.entity.*;

import java.util.List;
import java.util.Map;

public class DepartamentoView {

     private Long id;

     private String nombre;

     private Map<String, Object> edificio;

     private Propietario propietario;

     private Inquilino inquilino;

     private List<Servicio> servicios;

     private List<Expensa> expensas;

     private DepartamentoEstado estado;

     private Integer cantidadHabitaciones;

     public Long getId() { return id; }

     public void setId(Long id) { this.id = id; }

     public String getNombre() {return nombre; }

     public void setNombre(String nombre) { this.nombre = nombre; }

     public Map<String, Object> getEdificio() { return edificio; }

     public void setEdificio(Map<String, Object> edificio) { this.edificio = edificio; }

     public Inquilino getInquilino() { return inquilino; }

     public void setInquilino(Inquilino inquilino) { this.inquilino = inquilino; }

     public List<Expensa> getExpensas() { return expensas; }

     public void setExpensas(List<Expensa> expensas) { this.expensas = expensas; }

     public DepartamentoEstado getEstado() { return estado; }

     public void setEstado(DepartamentoEstado estado) { this.estado = estado; }

     public Propietario getPropietario() { return propietario; }

     public void setPropietario(Propietario propietario) { this.propietario = propietario; }

     public List<Servicio> getServicios() { return servicios; }

     public void setServicios(List<Servicio> servicios) { this.servicios = servicios; }

     public Integer getCantidadHabitaciones() { return cantidadHabitaciones; }

     public void setCantidadHabitaciones(Integer cantidadHabitaciones) { this.cantidadHabitaciones = cantidadHabitaciones; }
}

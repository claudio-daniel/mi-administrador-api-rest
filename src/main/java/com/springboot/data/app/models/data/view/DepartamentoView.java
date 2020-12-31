package com.springboot.data.app.models.data.view;

import com.springboot.data.app.models.data.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DepartamentoView {

    private Long id;

    private String nombre;

    private Map<String, Object> edificio;

    private Map<String, Object> propietario;

    private Map<String, Object> inquilino;

    private List<Servicio> servicios;

    private List<Expensa> expensas;

    private String estado;

    private Integer cantidadHabitaciones;

    private String patente;

    private String caballosFuerza;

    private Integer kilometros;

    private String modelo;

    private String traccion;

    private String tipo;

    private String marca;

    private LocalDate fecha;

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

    public Map<String, Object> getEdificio() {
        return edificio;
    }

    public void setEdificio(Map<String, Object> edificio) {
        this.edificio = edificio;
    }

    public Map<String, Object> getInquilino() {
        return inquilino;
    }

    public void setInquilino(Map<String, Object> inquilino) {
        this.inquilino = inquilino;
    }

    public List<Expensa> getExpensas() {
        return expensas;
    }

    public void setExpensas(List<Expensa> expensas) {
        this.expensas = expensas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Map<String, Object> getPropietario() {
        return propietario;
    }

    public void setPropietario(Map<String, Object> propietario) {
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

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getCaballosFuerza() {
        return caballosFuerza;
    }

    public void setCaballosFuerza(String caballosFuerza) {
        this.caballosFuerza = caballosFuerza;
    }

    public Integer getKilometros() {
        return kilometros;
    }

    public void setKilometros(Integer kilometros) {
        this.kilometros = kilometros;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

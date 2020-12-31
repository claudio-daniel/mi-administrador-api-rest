package com.springboot.data.app.models.data.transformer;

import com.springboot.data.app.models.data.entity.Departamento;
import com.springboot.data.app.models.data.entity.Edificio;

import com.springboot.data.app.models.data.view.DepartamentoView;
import com.springboot.data.app.models.service.IEdificioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DepartamentoTransformer {

    @Autowired
    @Qualifier("edificioService")
    private IEdificioService departamentoService;

    public Departamento convertToDepartamento(DepartamentoView departamentoView){
        Departamento departamento = new Departamento();

        if (departamentoView != null){
            departamento.setId(departamentoView.getId());
            departamento.setNombre(departamentoView.getNombre());
            departamento.setCantidadHabitaciones(departamentoView.getCantidadHabitaciones());

            Edificio edificio = departamentoService.findOne((Long) departamentoView.getEdificio().get("id"));
            departamento.setEdificio(edificio);

            departamento.setExpensas(departamentoView.getExpensas());
            //departamento.setEstado(departamentoView.getEstado());
            //departamento.setInquilino(departamentoView.getInquilino());
            //departamento.setPropietario(departamentoView.getPropietario());
            departamento.setServicios(departamentoView.getServicios());
        }

        return departamento;
    }

    public DepartamentoView convertToDepartamentoView(Departamento departamento){
        DepartamentoView departamentoView = new DepartamentoView();

        if (departamento != null){
            departamentoView.setId(departamento.getId());
            departamentoView.setNombre(departamento.getNombre());
            departamentoView.setCantidadHabitaciones(departamento.getCantidadHabitaciones());
            departamentoView.setKilometros(departamento.getKilometros());
            departamentoView.setCaballosFuerza(departamento.getCaballosFuerza());
            departamentoView.setFecha(departamento.getFecha());
            departamentoView.setEstado(departamento.getEstado().getDescripcion());
            departamentoView.setMarca(departamento.getMarca());
            departamentoView.setModelo(departamento.getModelo());
            departamentoView.setPatente(departamento.getPatente());
            departamentoView.setTipo(departamento.getTipo());
            departamentoView.setTraccion(departamento.getTraccion());

            Map<String, Object> edificio = new HashMap<>();
            edificio.put("id", departamento.getEdificio().getId());
            edificio.put("nombre", departamento.getEdificio().getNombre());
            departamentoView.setEdificio(edificio);

    //        departamentoView.setExpensas(departamento.getExpensas());
    //        departamentoView.setEstado(departamento.getEstado());

            Map<String, Object> propietario = new HashMap<>();
            propietario.put("id", departamento.getPropietario().getId());
            propietario.put("nombre", departamento.getPropietario().getNombre());
            departamentoView.setPropietario(propietario);

            Map<String, Object> inquilino = new HashMap<>();
            inquilino.put("id", departamento.getInquilino().getId());
            inquilino.put("nombre", departamento.getInquilino().getNombre());
            inquilino.put("fecha", departamento.getInquilino().getCreateAt());
            departamentoView.setInquilino(inquilino);

    //        departamentoView.setServicios(departamento.getServicios());
        }

        return departamentoView;
    }
}
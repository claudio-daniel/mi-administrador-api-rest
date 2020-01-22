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
            departamento.setEstado(departamentoView.getEstado());
            departamento.setInquilino(departamentoView.getInquilino());
            departamento.setPropietario(departamentoView.getPropietario());
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

            Map<String, Object> edificio = new HashMap<>();
            edificio.put("id", departamento.getEdificio().getId());
            edificio.put("nombre", departamento.getEdificio().getNombre());
            departamentoView.setEdificio(edificio);

            departamentoView.setExpensas(departamento.getExpensas());
            departamentoView.setEstado(departamento.getEstado());
            departamentoView.setInquilino(departamento.getInquilino());
            departamentoView.setPropietario(departamento.getPropietario());
            departamentoView.setServicios(departamento.getServicios());
        }

        return departamentoView;
    }
}
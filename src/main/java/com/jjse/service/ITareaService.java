package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.TareaDto;
import com.jjse.model.entity.Tarea;

public interface ITareaService {

    List<Tarea> listAll();

    Tarea save(TareaDto tarea);

    Tarea findById(Integer id);

    void delete(Tarea tarea);

    boolean existsById(Integer id);
    
    List<Tarea> findByEstadoId(Integer estadoId);
}

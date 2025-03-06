package com.jjse.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.jjse.model.entity.Tarea;

public interface TareaDao extends CrudRepository<Tarea, Integer> {

    List<Tarea> findByEstadoId(Integer estadoId);
}

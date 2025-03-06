package com.jjse.model.dao;

import org.springframework.data.repository.CrudRepository;


import com.jjse.model.entity.Tarea;

public interface TareaDao extends CrudRepository<Tarea, Integer> {

}

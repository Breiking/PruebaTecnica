package com.jjse.service;

import java.util.List;

import com.jjse.model.dto.EstadoDto;
import com.jjse.model.entity.Estado;



public interface IEstadoService {
    
    List<Estado> listAll();

    Estado save(EstadoDto estado);

    Estado findById(Integer id);

    void delete(Estado estado);

    boolean existsById(Integer id);

}

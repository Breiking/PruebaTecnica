package com.jjse.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jjse.model.dao.EstadoDao;
import com.jjse.model.dto.EstadoDto;
import com.jjse.model.entity.Estado;
import com.jjse.service.IEstadoService;


@Service
public class EstadoImplService implements IEstadoService {

    @Autowired
    private EstadoDao estadoDao;

    @Override
    public List<Estado> listAll(){
        return (List) estadoDao.findAll();
    }

    @Transactional
    @Override
    public Estado save(EstadoDto estadoDto){
         
        Estado estado = Estado.builder()
                .nombre(estadoDto.getNombre())
                .descripcion(estadoDto.getDescripcion())
                .build();

        return estadoDao.save(estado);
    }

    @Transactional(readOnly = true)
    @Override
    public Estado findById(Integer id){
        return estadoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Estado estado){
        estadoDao.delete(estado);
    }

    @Override
    public boolean existsById(Integer id){
        return estadoDao.existsById(id);
    }

}

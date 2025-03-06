package com.jjse.service.impl;

import java.beans.Transient;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Attr;
import com.jjse.model.dao.EstadoDao;
import com.jjse.model.dao.TareaDao;
import com.jjse.model.dao.UserDao;
import com.jjse.model.dto.EstadoDto;
import com.jjse.model.dto.TareaDto;
import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.Estado;
import com.jjse.model.entity.Tarea;
import com.jjse.model.entity.User;
import com.jjse.service.ITareaService;

@Service
public class TareaImplService implements ITareaService {

    @Autowired
    private TareaDao tareaDao;
    private UserDao userDao;
    private EstadoDao estadoDao;

    @Override
    public List<Tarea> listAll(){
        return (List) tareaDao.findAll();
    }

    @Transactional
    @Override
    public Tarea save(TareaDto tareaDto){

        User user = userDao.findById(tareaDto.getFk_user())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Estado estado = estadoDao.findById(tareaDto.getFk_estado())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado"));

        Tarea tarea = Tarea.builder()
                    .id(tareaDto.getId())
                    .titulo(tareaDto.getTitulo())
                    .descripcion(tareaDto.getDescripcion())
                    .user(user)
                    .estado(estado)
                    .build();
        return tareaDao.save(tarea);
    }

    @Transactional(readOnly = true)
    @Override
    public Tarea findById(Integer id){
        return tareaDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Tarea tarea){
        tareaDao.delete(tarea);
    }

    @Override
    public boolean existsById(Integer id){
        return tareaDao.existsById(id);
    }

}

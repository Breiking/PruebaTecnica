package com.jjse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dto.EstadoDto;
import com.jjse.model.entity.Estado;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.IEstadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/estados")
@Tag(name = "Estados", description = "Endpoints para gestionar los Estados")
public class EstadoController {
    @Autowired
    private IEstadoService estadoService;

    @GetMapping()
    @Operation(summary = "Listar todos los Estados", description = "")
    public ResponseEntity<?> showAll() {
        List<Estado> getList = estadoService.listAll();
        if (getList.size() <= 0) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay Estados registrados")
                                        .object(null)
                                        .build(),
                                        HttpStatus.OK);  
        }
        return new ResponseEntity<>(
            MessageResponse.builder()
                            .message("Estados encontrados")
                            .object(getList)
                            .build(),
                            HttpStatus.OK);
    }
    
    @PostMapping("")
    @Operation(summary = "Crear un estado nuevo", description = "Siguiendo el json de ejemplo puede crear un nuevo estado para las tareas")
    public ResponseEntity<?> registrarEstado(@RequestBody EstadoDto estadoDto) {
        Estado estadoSave = null;
        try {
            estadoSave = estadoService.save(estadoDto);
            return new ResponseEntity<>(
                            MessageResponse.builder()
                            .message("Estado creado satisfactoriamente")
                            .object(EstadoDto.builder()
                                .id(estadoSave.getId())
                                .nombre(estadoSave.getNombre())
                                .descripcion(estadoSave.getDescripcion())
                                .build())
                            .build(),
                            HttpStatus.CREATED);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }  
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Modificar un estado", description = "")
    public ResponseEntity<?> updatePermiso(@PathVariable Integer id, @RequestBody EstadoDto estadoDto) {
        Estado estadoUpdate = null;

        try {
            if (estadoService.existsById(id)) {
                estadoDto.setId(id);
                estadoUpdate = estadoService.save(estadoDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("Estado actualizado")
                                    .object(EstadoDto.builder()
                                        .nombre(estadoUpdate.getNombre())
                                        .descripcion(estadoUpdate.getDescripcion())
                                        .build())
                                    .build(),
                                HttpStatus.CREATED);   
            }
            else{
                return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("El Esatado no fue encontrando")
                                        .object(null)
                                        .build()
                            ,HttpStatus.NOT_FOUND);    
            }

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un estado", description = "Si hay tareas con este estado, este no podra ser eliminado")
    public ResponseEntity<?> deleteEestado(@PathVariable Integer id){
        try {
            Estado estadoDelete = estadoService.findById(id);
            estadoService.delete(estadoDelete);
            return new ResponseEntity<>(estadoDelete,HttpStatus.NO_CONTENT);
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}

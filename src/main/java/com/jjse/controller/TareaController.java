package com.jjse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dto.TareaDto;
import com.jjse.model.entity.Tarea;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.ITareaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/taks")
@Tag(name = "Tareas", description = "Endpoints para gestionar tareas")
public class TareaController {

    @Autowired
    private ITareaService tareaService;

    @GetMapping("")
    @Operation(summary = "Listar todas las tareas", description = "")
    public ResponseEntity<?> showAll(){
        
        List<Tarea> getList = tareaService.listAll();

        if (getList.size() <= 0) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay tareas registradas")
                                        .object(null)
                                        .build(),
                            HttpStatus.OK);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                            .message("Registros encontrados")
                            .object(getList)
                            .build(),
                            HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Registrar una nueva tarea", description = "Guarda una tarea en la base de datos, el titulo no debe ser menor a 10 caracteres")
    public ResponseEntity<?> registerTarea(@RequestBody TareaDto tareaDto) {
        Tarea tareaSave = null;

        try {
            tareaSave = tareaService.save(tareaDto);
            return new ResponseEntity<>(
                        MessageResponse.builder()
                        .message("Tarea Guardada")
                        .object(TareaDto.builder()
                            .id(tareaSave.getId())
                            .titulo(tareaSave.getTitulo())
                            .descripcion(tareaSave.getDescripcion())
                            .fk_estado(tareaSave.getEstado())
                            .fk_user(tareaSave.getUser())
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
    @Operation(summary = "Modificar una tarea", description = "Guarda los cambios, el titulo no debe ser menor a 10 caracteres")
    public ResponseEntity<?> updateTarea(@PathVariable Integer id, @RequestBody TareaDto tareaDto){

        Tarea tareaUpdate = null;

        try {
            
            if (tareaService.existsById(id)) {
                tareaDto.setId(id);
                tareaUpdate = tareaService.save(tareaDto);
                return new ResponseEntity<>(
                                MessageResponse.builder()
                                    .message("Tarea Actualizada")
                                    .object(TareaDto.builder()
                                        .id(tareaUpdate.getId())
                                        .titulo(tareaUpdate.getTitulo())
                                        .descripcion(tareaUpdate.getDescripcion())
                                        .fk_user(tareaUpdate.getUser())
                                        .fk_estado(tareaUpdate.getEstado())
                                        .build())
                                    .build(),
                                HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("La tarea no fue encontrando")
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
    @Operation(summary = "Eliminar una tarea", description = "")
    public ResponseEntity<?> deleteTarea(@PathVariable Integer id){

        try {
            Tarea tareaDelete = tareaService.findById(id);
            tareaService.delete(tareaDelete);

            return new ResponseEntity<>(tareaDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message(exDt.getMessage())
                                        .object(null)
                                        .build()
                            ,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una tarea en especifico", description = "el dato a ingresar debe ser el id de la tarea")
    public ResponseEntity<?> showByid(@PathVariable Integer id) {
        
        Tarea tarea = tareaService.findById(id);

        if (tarea == null) {
            return new ResponseEntity<>(
                MessageResponse.builder()
                            .message("El registro que intenta buscar no existe")
                            .object(null)
                            .build()
                ,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message("Tarea encontrada")
                        .object(TareaDto.builder()
                                    .id(tarea.getId())
                                    .titulo(tarea.getTitulo())
                                    .descripcion(tarea.getDescripcion())
                                    .fk_user(tarea.getUser())
                                    .fk_estado(tarea.getEstado())
                                    .build())
                                .build(),
            HttpStatus.OK);

    }
    
    @GetMapping("/estado/{id}")
    @Operation(summary = "Buscar las tareas con estado en especifico", description = "se debe enviar el id del estado que desea filtrar")
    public ResponseEntity<?> tareasByEstado(@PathVariable Integer id) {
        List<Tarea> tareas = tareaService.findByEstadoId(id);

        if (tareas.isEmpty()) {
            return new ResponseEntity<>(
                MessageResponse.builder()
                            .message("No hay tareas con ese estado")
                            .object(null)
                            .build(),
                HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message("Tareas encontradas")
                        .object(tareas)
                        .build(),
            HttpStatus.OK);
    }
    

}

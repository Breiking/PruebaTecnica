package com.jjse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jjse.model.dto.UserDto;
import com.jjse.model.entity.User;
import com.jjse.model.pyload.MessageResponse;
import com.jjse.service.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints para gestionar los Usuarios")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("")
    @Operation(summary = "Listar todos los Usuarios", description = "")
    public ResponseEntity<?> showAll() {
        
        List<User> getList = userService.listAll();

        if (getList.size() <= 0) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("No hay Usuario Registrados")
                                        .object(null)
                                        .build()
                                    ,HttpStatus.OK);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message(null)
                        .object(getList)
                        .build()
                    ,HttpStatus.OK);
    }

    @PostMapping("")
    @Operation(summary = "Registrar un nuevo usuario", description = "el nombre no debe ser menor a 5 caracteres")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto){
        User userSave = null;
        try {
            userSave = userService.save(userDto);

            return new ResponseEntity<>(
                    MessageResponse.builder()
                                .message("Usuario Agregado correctamente")
                                .object(UserDto.builder()
                                        .id(userSave.getId())
                                        .nombre(userSave.getNombre())
                                        .email(userSave.getEmail())
                                        .build())
                                    .build(),
                    HttpStatus.CREATED );
            
        } catch (DataAccessException exDt) {
            return new ResponseEntity<>(
                    MessageResponse.builder()
                                .message("Error al guardar el usuario" + exDt.getMessage())
                                .object(null)
                                .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } catch(Exception ex){
            return new ResponseEntity<>(
                    MessageResponse.builder()
                                .message("Error desconocido" + ex.getMessage())
                                .object(null)
                                .build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    @Operation(summary = "Modificar un usuario", description = "el nombre no debe ser menor a 5 caracteres")
        public ResponseEntity<?> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id){
            User userUpdate = null;
            try {
                if (userService.existsById(id)) {
                    userDto.setId(id);
                    userUpdate = userService.save(userDto);

                    return new ResponseEntity<>(
                                    MessageResponse.builder()
                                                .message("Usuario Modificado correctamente")
                                                .object(UserDto.builder()
                                                    .id(userUpdate.getId())
                                                    .nombre(userUpdate.getNombre())
                                                    .email(userUpdate.getEmail())
                                                    .build())
                                                .build(),
                                    HttpStatus.CREATED);
                }
                else{
                    return new ResponseEntity<>(
                                MessageResponse.builder()
                                            .message("El usuario no fue encontrando")
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
        @Operation(summary = "Eliminar un usuario", description = "El usuario se eliminar unicamente si no tiene ninguna tarea asignada, en proceso o terminada")
        public ResponseEntity<?> deleteeUser(@PathVariable Integer id){
        try {

            User userDelete = userService.findById(id);
            userService.delete(userDelete);

            return new ResponseEntity<>(userDelete, HttpStatus.NO_CONTENT);
            
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
    @Operation(summary = "Buscar un usuario", description = "")
    public ResponseEntity<?> showByid(@PathVariable Integer id){
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(
                            MessageResponse.builder()
                                        .message("El usuario que intenta buscar no existe")
                                        .object(null)
                                        .build()
                            ,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
            MessageResponse.builder()
                        .message(null)
                        .object(UserDto.builder()
                            .id(user.getId())
                            .nombre(user.getNombre())
                            .email(user.getEmail())
                            .build())
                        .build()
            ,HttpStatus.OK);
    }


    
}

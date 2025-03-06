package com.jjse.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jjse.model.entity.Estado;
import com.jjse.model.entity.User;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TareaDto implements Serializable {
    
    @JsonIgnore
    private Integer id;

    private String titulo;

    private String descripcion;

    @JsonIgnoreProperties({"nombre", "email", "tareas"})
    private User fk_user;

    @JsonIgnoreProperties({"nombre", "descripcion", "tareas"})
    private Estado fk_estado;
}

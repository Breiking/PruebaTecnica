package com.jjse.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jjse.model.entity.Tarea;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class EstadoDto implements Serializable {

    @JsonIgnore
    private int id;
    
    private String nombre;

    private String descripcion;

    @JsonIgnore
    private List<Tarea> tareas;
}

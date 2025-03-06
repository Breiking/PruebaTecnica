package com.jjse.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class EstadoDto implements Serializable {

    private int id;
    
    private String nombre;

    private String descripcion;
}

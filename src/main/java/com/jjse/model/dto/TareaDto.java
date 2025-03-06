package com.jjse.model.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class TareaDto implements Serializable {

    private Integer id;

    private String titulo;

    private String descripcion;

    private Integer fk_user;

    private Integer fk_estado;
}

package com.jjse.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class UserDto implements Serializable {

    private Integer id;

    private String nombre;

    private String email;

    @JsonIgnore
    private List<TareaDto> tareas;
}

package com.jjse.model.entity;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "tareas")
public class Tarea implements Serializable {
    
    @Id
    @Schema(description = "ID único de la tarea", example = "1")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, min = 10, message = "El título no puede tener más de 100 caracteres y menos de 10")
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonManagedReference
    @JsonIgnoreProperties({"nombre", "email", "tareas"})
    private User user;

    @ManyToOne
    @JoinColumn(name = "fk_estado")
    @JsonManagedReference
    @JsonIgnoreProperties({"nombre", "descripcion", "tareas"})
    private Estado estado;
}

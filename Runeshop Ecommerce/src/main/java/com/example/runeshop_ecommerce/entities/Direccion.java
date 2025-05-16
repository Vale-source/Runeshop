package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Direccion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Direccion extends Base{

    @JsonProperty("localidad")
    @NotNull(message = "La localidad no puede ser nulo")
    @Column(name = "localidad", nullable = false)
    private String localidad;

    @JsonProperty("departamento")
    @NotNull(message = "El departamento no puede ser nulo")
    @Column(name = "departamento", nullable = false)
    private String departamento;

    @JsonProperty("provincia")
    @NotNull(message = "La provincia no puede ser nulo")
    @Column(name = "provincia",nullable = false)
    private String provincia;

    @JsonProperty("pais")
    @NotNull(message = "El pais no puede ser nulo")
    @Column(name = "pais", nullable = false)
    private String pais;

    @OneToMany(mappedBy = "direccion")
    @JsonManagedReference
    private List<UsuarioDireccion> usuariosDirecciones;
}

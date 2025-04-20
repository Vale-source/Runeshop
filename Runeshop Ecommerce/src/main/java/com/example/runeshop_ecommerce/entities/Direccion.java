package com.example.runeshop_ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Direccion")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Direccion extends Base{

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "provincia")
    private String provinicia;

    @Column(name = "pais")
    private String pais;

    @OneToMany(mappedBy = "direccion")
    private List<UsuarioDireccion> usuariosDirecciones;
}

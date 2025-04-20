package com.example.runeshop_ecommerce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario_direccion")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDireccion extends Base {

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

}

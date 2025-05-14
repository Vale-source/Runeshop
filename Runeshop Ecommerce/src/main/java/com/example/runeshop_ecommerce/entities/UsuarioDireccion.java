package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuario_direccion")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UsuarioDireccion extends Base {

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "direccion_id")
    private Direccion direccion;

    @OneToMany
    @JsonManagedReference
    @JoinColumn(name = "oredenes_de_compra")
    private List<OrdenCompra> ordenCompras;
}

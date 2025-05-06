package com.example.runeshop_ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Precio")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Precio extends Base {
    @Column(name = "precio_compra")
    private Double precioCompra;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @OneToMany(mappedBy = "precio")
    private List<Detalle> detalles;

    @ManyToMany
    @JoinTable(
            name = "precio_descuento",
            joinColumns = @JoinColumn(name = "precio_id"),
            inverseJoinColumns = @JoinColumn(name = "descuento_id")
    )
    private List<Descuento> descuentos;
}

package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @JsonProperty("precioCompra")
    @NotNull(message = "El precio de compra no puede ser nulo")
    @Column(name = "precio_compra", nullable = false)
    private Double precioCompra;

    @JsonProperty("precioVenta")
    @NotNull(message = "El precio de venta no puede ser nulo")
    @Column(name = "precio_venta", nullable = false)
    private Double precioVenta;

    @OneToMany(mappedBy = "precio")
    @JsonManagedReference
    private List<Detalle> detalles;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "precio_descuento",
            joinColumns = @JoinColumn(name = "precio_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "descuento_id", nullable = false)
    )
    private List<Descuento> descuentos;
}

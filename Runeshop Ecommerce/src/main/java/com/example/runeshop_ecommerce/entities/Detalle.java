package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Detalle")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Detalle extends Base{

    @JsonProperty("color")
    @NotNull(message = "El color del producto no puede ser nulo")
    @Column(name = "color", nullable = false)
    private String color;

    @JsonProperty("estado")
    @NotNull(message = "El estado del producto no puede ser nulo")
    @Column(name = "estado", nullable = false)
    private boolean estado;

    @JsonProperty("marca")
    @NotNull(message = "La marca del producto no puede ser nulo")
    @Column(name = "marca", nullable = false)
    private Marca marca;

    @JsonProperty("stock")
    @NotNull(message = "El stock del producto no puede ser nulo")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "detalle_talle",
            joinColumns = @JoinColumn(name = "detalle_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "talle_id", nullable = false)
    )
    private List<Talle> talles;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "precio_id", nullable = false)
    private Precio precio;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "detalle_imagen",
            joinColumns = @JoinColumn(name = "detalle_id"),
            inverseJoinColumns = @JoinColumn(name = "imagen_id")
    )
    private List<Imagen> imagenes;

    @ManyToMany
    @JsonBackReference
    private List<OrdenCompra> ordenCompras;
}

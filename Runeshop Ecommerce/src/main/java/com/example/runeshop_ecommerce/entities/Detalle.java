package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Detalle")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Detalle extends Base{

    @Column(name = "color")
    private String color;

    @Column(name = "estado")
    private boolean estado;

    @Column(name = "marca")
    private Enum<Marca> marca;

    @Column(name = "stock")
    private Number stock;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "talle_id")
    private Talle talle;

    @ManyToOne
    @JoinColumn(name = "precio_id")
    private Precio precio;

    @ManyToMany
    @JoinTable(
            name = "detalle_imagen",
            joinColumns = @JoinColumn(name = "detalle_id"),
            inverseJoinColumns = @JoinColumn(name = "imagen_id")
    )
    private List<Imagen> imagenes;
}

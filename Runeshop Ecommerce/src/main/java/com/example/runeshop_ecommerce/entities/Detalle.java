package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.fasterxml.jackson.annotation.*;
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
@JsonPropertyOrder({ "id", "color", "estado", "marca", "stock"})
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
    @Enumerated(EnumType.STRING)
    @Column(name = "marca", nullable = false)
    private Marca marca;

    @JsonProperty("stock")
    @NotNull(message = "El stock del producto no puede ser nulo")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @ManyToOne
    @JsonIgnoreProperties("detalles")
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JsonIgnoreProperties("detalles")
    @JoinColumn(name = "talle_id", nullable = false)
    private Talle talle;

    @ManyToOne
    @JsonIgnoreProperties("detalles")
    @JoinColumn(name = "precio_id", nullable = false)
    private Precio precio;

    @ManyToMany
    @JsonIgnoreProperties("detalles")
    @JoinTable(
            name = "detalle_imagen",
            joinColumns = @JoinColumn(name = "detalle_id"),
            inverseJoinColumns = @JoinColumn(name = "imagen_id")
    )
    private List<Imagen> imagenes;

    @ManyToMany
    @JsonIgnoreProperties("detalles")
    private List<OrdenCompra> ordenCompras;
}

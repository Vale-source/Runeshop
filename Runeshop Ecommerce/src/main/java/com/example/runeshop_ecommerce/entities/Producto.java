package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Producto extends Base{

    @JsonProperty("nombre")
    @NotNull(message = "El nombre del producto no puede ser nulo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @JsonProperty("sexo")
    @NotNull(message = "El sexo del producto no puede ser nulo")
    @Column(name = "sexo", nullable = false)
    private String sexo;

    @JsonProperty("tipoProducto")
    @NotNull(message = "El tipo de producto no puede ser nulo")
    @Column(name = "tipo_producto", nullable = false)
    private TipoProducto tipoProducto;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    @JsonManagedReference
    private List<Detalle> detalles;

}

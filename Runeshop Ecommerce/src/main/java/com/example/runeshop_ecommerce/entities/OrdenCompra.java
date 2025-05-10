package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orden_compra")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdenCompra extends Base{

    @JsonProperty("total")
    @NotNull(message = "el total no puede ser nulo")
    @Column(name = "total", nullable = false)
    private Float total; //Sumatoria de todos los productos

    @JsonProperty("fechaCompra")
    @NotNull(message = "la fecha de compra no puede ser nulo")
    @Column(name = "fecha_compra", nullable = false)
    private Date fechaCompra;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "id_usuario_direccion")
    private UsuarioDireccion id;

    @ManyToMany
    @JsonManagedReference
    @JoinTable(
            name = "ordenCompra_detalle",
            joinColumns = @JoinColumn(name = "ordeCompra_id"),
            inverseJoinColumns = @JoinColumn(name = "detalle_id")
    )
    private List<Detalle> detalles;
}

package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
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

    @Column(name = "total")
    private Float total; //Sumatoria de todos los productos

    @Column(name = "fecha_compra")
    private Date fechaCompra;

    @ManyToOne
    @JoinColumn(name = "id_usuario_direccion")
    private UsuarioDireccion usuarioDireccion;

    @ManyToMany
    @JoinTable(
            name = "ordenCompra_detalle",
            joinColumns = @JoinColumn(name = "ordeCompra_id"),
            inverseJoinColumns = @JoinColumn(name = "detalle_id")
    )
    private List<Detalle> detalles;
}

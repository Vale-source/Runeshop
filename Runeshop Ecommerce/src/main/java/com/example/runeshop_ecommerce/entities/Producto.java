package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import jakarta.persistence.*;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "tipo_producto")
    private TipoProducto tipoProducto;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto")
    private List<Detalle> detalles;

}

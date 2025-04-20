package com.example.runeshop_ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Descuento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Descuento extends Base{

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_final")
    private Date fechaFinal;

    @Column(name = "porcentaje")
    private Number porcentaje;

    @ManyToMany(mappedBy = "descuentos")
    private List<Precio> precios;
}

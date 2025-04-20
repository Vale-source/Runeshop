package com.example.runeshop_ecommerce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Talle")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Talle extends Base{

    @Column(name = "talle")
    private Number talle;

    @OneToMany(mappedBy = "talle")
    private List<Detalle> detalles;
}

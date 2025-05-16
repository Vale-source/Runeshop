package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Talle")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Talle extends Base{

    @JsonProperty("numero")
    @NotNull(message = "el numero del talle es nulo")
    @Column(name = "numero", nullable = false)
    private Integer numero;

    @ManyToMany(mappedBy = "talles")
    @JsonBackReference
    private List<Detalle> detalles;
}

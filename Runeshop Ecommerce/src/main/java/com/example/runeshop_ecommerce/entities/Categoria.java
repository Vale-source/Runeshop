package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "Categoria")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Categoria extends Base {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre de la categoría no puede ser nulo.")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @JsonManagedReference
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}

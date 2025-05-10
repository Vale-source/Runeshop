package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Imagen")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Imagen extends Base {

    @JsonProperty("url")
    @NotNull(message = "La URL no puede ser nulo")
    @JoinColumn(name = "URL", nullable = false)
    private String url;

    @ManyToMany(mappedBy = "imagenes")
    @JsonBackReference
    private List<Detalle> detalles;
}

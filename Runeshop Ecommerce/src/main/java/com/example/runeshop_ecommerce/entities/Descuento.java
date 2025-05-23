package com.example.runeshop_ecommerce.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Descuento")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder({ "id", "fechaInicio", "fechaFinal", "porcentaje" })
public class Descuento extends Base{

    @JsonProperty("fechaInicio")
    @NotNull(message = "La fecha de inicio del descuento no puede ser nulo.")
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @JsonProperty("fechaFinal")
    @NotNull(message = "La fecha de finalizacion del descuento no puede ser nulo.")
    @Column(name = "fecha_final", nullable = false)
    private Date fechaFinal;

    @JsonProperty("porcentaje")
    @NotNull(message = "El porcentaje del descuento no puede ser nulo.")
    @Column(name = "porcentaje", nullable = false)
    private Number porcentaje;

    @JsonIgnoreProperties("descuentos")
    @ManyToMany(mappedBy = "descuentos")
    private List<Precio> precios;
}

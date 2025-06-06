package com.example.runeshop_ecommerce.DTOs;

import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductoFilterDTO {

    @JsonProperty("sexo")
    private String sexo;

    @JsonProperty("marca")
    private List<Marca> marca;

    @JsonProperty("talle")
    private List<Integer> talleNumero;

    @JsonProperty("tipoProducto")
    private List<TipoProducto> tipoProducto;

    @JsonProperty("modelo")
    private String modelo;

    @JsonProperty("categoria")
    private List<String> categoria;

    @JsonProperty("min")
    private Double min;

    @JsonProperty("max")
    private Double max;

}

package com.example.runeshop_ecommerce.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String nombreUsuario;
    String contrasenia;
    String nombre;
    String apellido;
    String country;
}

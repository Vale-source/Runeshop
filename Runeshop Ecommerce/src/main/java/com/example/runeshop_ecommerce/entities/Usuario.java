package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends Base{

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contraseña")
    private String contrasenia;

    @Column(name = "email")
    private String email;

    @Column(name = "rol")
    private Enum<TipoUsuario> tipoUsuario;

    @Column(name = "DNI")
    private Number dni;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioDireccion> usuariosDirecciones;
}

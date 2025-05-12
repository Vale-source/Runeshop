package com.example.runeshop_ecommerce.entities;

import com.example.runeshop_ecommerce.entities.enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends Base implements UserDetails {

    @JsonProperty("nombre")
    @NotNull(message = "El nombre no puede ser nulo")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @JsonProperty("nombreUsuario")
    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @JsonProperty("apellido")
    @NotNull(message = "El apellido no puede ser nulo")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @JsonProperty("contraseña")
    @NotNull(message = "La contraseña no puede ser nulo")
    @Column(name = "contraseña", nullable = false)
    private String contrasenia;

    @JsonProperty("email")
    @NotNull(message = "El email no puede ser nulo")
    @Column(name = "email", nullable = false)
    private String email;

    @JsonProperty("tipoUsuario")
    @NotNull(message = "El rol de usuario no puede ser nulo")
    @Column(name = "rol", nullable = false)
    private Enum<Role> tipoUsuario;

    @JsonProperty("dni")
    @Column(name = "DNI")
    private Number dni;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<UsuarioDireccion> usuariosDirecciones;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(tipoUsuario.name()));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isCredentialNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

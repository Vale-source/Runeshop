package com.example.runeshop_ecommerce.auth;

import com.example.runeshop_ecommerce.config.JwtService;
import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.entities.enums.Role;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getNombreUsuario(), request.getContrasenia()));

        Usuario usuario = usuarioRepository
                .findUsuarioByNombreUsuario(request.getNombreUsuario())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        if (usuarioRepository.findUsuarioByNombreUsuario(request.getNombreUsuario()).isPresent()) {
            throw new IllegalArgumentException("Nombre de usuario ya registrado");
        }
        if (usuarioRepository.findUsuarioByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (usuarioRepository.findUsuarioByDni(request.getDni()).isPresent()) {
            throw new IllegalArgumentException("DNI ya registrado");
        }

        Usuario usuario = Usuario.builder()
                .nombreUsuario(request.getNombreUsuario())
                .contrasenia(passwordEncoder.encode(request.getContrasenia()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .dni(request.getDni())
                .tipoUsuario(Role.USER)
                .email(request.getEmail())
                .build();

        usuarioRepository.save(usuario);
        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .id(usuario.getId())
                .build();
    }
}

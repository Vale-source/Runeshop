package com.example.runeshop_ecommerce.auth;

import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthResponse login(LoginRequest request) {
        return null;
    }

    public AuthResponse register(RegisterRequest request) {
//        Usuario usuario
        return null;
    }
}

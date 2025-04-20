package com.example.runeshop_ecommerce.repositories;

import com.example.runeshop_ecommerce.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario, Long> {
}

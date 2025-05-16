package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.repositories.DireccionRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioDireccionRepository;
import com.example.runeshop_ecommerce.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioDireccionService extends BaseService<UsuarioDireccion, Long> {

    private UsuarioRepository usuarioRepository;
    private DireccionRepository direccionRepository;
    private UsuarioDireccionRepository usuarioDireccionRepository;

    public UsuarioDireccionService(UsuarioDireccionRepository usuarioDireccionRepository) {
        super(usuarioDireccionRepository);
    }

    @Transactional
    public List<Direccion> direcionesPorUsuario(Long usuarioId) throws Exception {
        try {
            return usuarioDireccionRepository.getDireccionesPorUsuario(usuarioId);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void agregarDireccion(Long id, Direccion direccion) throws Exception {
        try	{
            Usuario usuario = usuarioRepository.findById(id).orElse(null);
            direccionRepository.save(direccion);
            UsuarioDireccion relacion = UsuarioDireccion.builder()
                    .usuario(usuario)
                    .direccion(direccion)
                    .build();
            usuarioDireccionRepository.save(relacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.example.runeshop_ecommerce;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.entities.Usuario;
import com.example.runeshop_ecommerce.entities.UsuarioDireccion;
import com.example.runeshop_ecommerce.entities.enums.Role;
import com.example.runeshop_ecommerce.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RuneshopEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RuneshopEcommerceApplication.class, args);
        System.out.println("Servidor Iniciado");
    }
    @Bean
    @Transactional
    CommandLineRunner init(
            TalleRepository talleRepository,
            ImagenRepository imagenRepository,
            PrecioRepository precioRepository,
            DetalleRepository detalleRepository,
            UsuarioRepository usuarioRepository,
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            DescuentoRepository descuentoRepository,
            DireccionRepository direccionRepository,
            OrdenCompraRepository ordenCompraRepository,
            UsuarioDireccionRepository usuarioDireccionRepository

    ) {
        return args -> {
            try {
                //usuarios
                Usuario usuario = Usuario.builder()
                        .nombre("Juan")
                        .apellido("Perez")
                        .nombreUsuario("juan.perez")
                        .email("juanperez@example.com")
                        .contrasenia("password123")
                        .tipoUsuario(Role.USER)
                        .dni(12345678)
                        .build();
                usuarioRepository.save(usuario);

                // direcciones
                Direccion direccion = Direccion.builder()
                        .localidad("Ciudad Autónoma de Buenos Aires")
                        .departamento("Palermo")
                        .provincia("Buenos Aires")
                        .pais("Argentina")
                        .build();
                direccionRepository.save(direccion);

                // usuarioDireccion
                UsuarioDireccion usuarioDireccion = UsuarioDireccion.builder()
                        .usuario(usuario)
                        .direccion(direccion)
                        .build();
                usuarioDireccionRepository.save(usuarioDireccion);


            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        };
    }

}

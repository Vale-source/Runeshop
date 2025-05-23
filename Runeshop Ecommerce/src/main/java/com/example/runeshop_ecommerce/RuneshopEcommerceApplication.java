package com.example.runeshop_ecommerce;

import com.example.runeshop_ecommerce.entities.*;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.Role;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
                // Usuario
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

                // Direccion
                Direccion direccion = Direccion.builder()
                        .localidad("Ciudad Autónoma de Buenos Aires")
                        .departamento("Palermo")
                        .provincia("Buenos Aires")
                        .pais("Argentina")
                        .build();
                direccionRepository.save(direccion);

                // UsuarioDireccion
                UsuarioDireccion usuarioDireccion = UsuarioDireccion.builder()
                        .usuario(usuario)
                        .direccion(direccion)
                        .build();
                usuarioDireccionRepository.save(usuarioDireccion);

                // Talle
                Talle talle = Talle.builder()
                        .numero(43)
                        .build();
                talleRepository.save(talle);

                // Categoria
                Categoria categoria = Categoria.builder()
                        .nombre("Urbano")
                        .build();
                categoriaRepository.save(categoria);

                // Imagen
                Imagen imagen = Imagen.builder()
                        .nombre("Jordan No Fake img")
                        .imagenUrl(null)
                        .build();
                imagenRepository.save(imagen);

                // Precio
                Precio precio = Precio.builder()
                        .precioCompra(23000.56)
                        .precioVenta(25000.00)
                        .build();
                precioRepository.save(precio);

                // Producto
                Producto producto = Producto.builder()
                        .modelo("Jordan No Fake")
                        .sexo("Hombre")
                        .tipoProducto(TipoProducto.ZAPATILLA)
                        .categoria(categoria)
                        .build();
                productoRepository.save(producto);

                // Detalle
                Detalle detalle = Detalle.builder()
                        .marca(Marca.ADIDAS)
                        .stock(100)
                        .color("Rojo")
                        .estado(true)
                        .producto(producto)
                        .precio(precio)
                        .talle(talle)
                        .imagenes(List.of(imagen))
                        .build();
                detalleRepository.save(detalle);
                List<Detalle> detalles = new ArrayList<>();
                detalles.add(detalle);
                talle.setDetalles(detalles);

                // OrdenCompra
                OrdenCompra ordenCompra = OrdenCompra.builder()
                        .total(25000.00f)
                        .fechaCompra(new java.util.Date())
                        .usuarioDireccion(usuarioDireccion)
                        .detalles(List.of(detalle))
                        .build();
                ordenCompraRepository.save(ordenCompra);

                // Descuento
                Descuento descuento = Descuento.builder()
                        .fechaInicio(new java.util.Date())
                        .fechaFinal(new java.util.Date(System.currentTimeMillis() + 86400000L)) // +1 día
                        .porcentaje(10)
                        .precios(List.of(precio))
                        .build();
                descuentoRepository.save(descuento);

                // Actualizar relaciones entre Precio y Descuento
                precio.setDescuentos(List.of(descuento));
                precioRepository.save(precio);

            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        };
    }

}

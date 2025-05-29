package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.DTOs.CrearDetalleDTO;
import com.example.runeshop_ecommerce.DTOs.CrearProductoDTO;
import com.example.runeshop_ecommerce.entities.*;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.repositories.CategoriaRepository;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import com.example.runeshop_ecommerce.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class ProductoService extends BaseService<Producto, Long> {

    private final DetalleRepository detalleRepository;
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, DetalleRepository detalleRepository, ProductoRepository productoRepository1, CategoriaRepository categoriaRepository){
        super(productoRepository);
        this.detalleRepository = detalleRepository;
        this.productoRepository = productoRepository1;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Producto crearProducto (
            CrearProductoDTO dto
    ) throws Exception {
        if (dto != null) {
            System.out.println("Modelo: " + dto.getModelo());
        }
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new Exception("Categoria no encontrada"));

        Producto producto = Producto.builder()
                .modelo(dto.getModelo().toUpperCase(Locale.ROOT))
                .sexo(dto.getSexo())
                .tipoProducto(dto.getTipoProducto())
                .categoria(categoria)
                .detalles(new ArrayList<>())
                .build();
        categoria.getProductos().add(producto);
        productoRepository.save(producto);
        return producto;
    }

    @Transactional
    public List<Producto> filtroProd (
            String sexo,
            Marca marca,
            Integer talleNumero,
            TipoProducto tipoProducto,
            String nombre,
            String cateoria,
            Double min,
            Double max
    ) throws Exception {
        if (min != null && max != null && min > max) {
            throw new Exception("El valor de 'min' no puede ser mayor que 'max'.");
        }

        return productoRepository.filtro(sexo, marca, talleNumero, tipoProducto, nombre, cateoria, min, max);
    }

    public List<Producto> orderAsc (
            List<Producto> productos
    ) throws Exception {
        if (productos.isEmpty()) {
            throw new Exception("La lista esta vacia");
        }
        return productoRepository.ordenarPrecioAsc(productos);
    }

    public List<Producto> orderDesc (
            List<Producto> productos
    ) throws Exception {
        if (productos.isEmpty()) {
            throw new Exception("La lista esta vacia");
        }
        return productoRepository.ordenarPrecioDesc(productos);
    }
}

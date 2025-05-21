package com.example.runeshop_ecommerce.services;

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

@Service
public class ProductoService extends BaseService<Producto, Long> {

    private DetalleRepository detalleRepository;
    private ProductoRepository productoRepository;
    private DetalleService detalleService;
    private CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository){
        super(productoRepository);
    }

    @Transactional
    public Producto crearProducto (
            CrearProductoDTO dto,
            MultipartFile file
    ) throws Exception {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new Exception("Categoria no encontrada"));

        Producto producto = Producto.builder()
                .modelo(dto.getModelo())
                .sexo(dto.getSexo())
                .tipoProducto(dto.getTipoProducto())
                .categoria(categoria)
                .build();
        productoRepository.save(producto);

        Detalle detalle = Detalle.builder()
                .color(dto.getColor())
                .estado(dto.isEstado())
                .marca(dto.getMarca())
                .stock(dto.getStock())
                .talles(dto.getTalles())
                .precio(dto.getPrecio())
                .producto(producto)
                .build();
        detalleRepository.save(detalle);

        if ( producto.getDetalles() == null || producto.getDetalles().isEmpty()) {
            producto.setDetalles(new ArrayList<>());
        }

        producto.getDetalles().add(detalle);

        detalleService.saveImagenInDetalle(detalle.getId(), file);

        return producto;
    }

    @Transactional
    public List<Producto> filtroProd (
            String sexo,
            Marca marca,
            Integer talleNumero,
            TipoProducto tipoProducto,
            String nombre,
            String cateoria
            ) throws Exception {
        return productoRepository.filtro(sexo, marca, talleNumero, tipoProducto, nombre, cateoria);
    }

    @Transactional
    public List<Producto> filtrarPorPrecio(Double min, Double max) throws Exception {
        if (min.isNaN() || max.isNaN()) {
            throw new Exception("Ingrese valores validos para el filtro");
        }
        return detalleRepository.filtroPrecio(min, max);
    }

    @Transactional
    public List<Producto> ordenarPrecioAscendente() {
        return detalleRepository.ordenarPrecioAsc();
    }

    @Transactional
    public List<Producto> ordenarPrecioDescendente() {
            return detalleRepository.ordenarPrecioDesc();
    }
}

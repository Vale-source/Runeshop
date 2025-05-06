package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Producto;
import com.example.runeshop_ecommerce.entities.Talle;
import com.example.runeshop_ecommerce.entities.enums.Marca;
import com.example.runeshop_ecommerce.entities.enums.TipoProducto;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import com.example.runeshop_ecommerce.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService extends BaseService<Producto, Long> {

    private DetalleRepository detalleRepository;
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        super(productoRepository);
    }

    public List<Producto> filtrarPorMarca(Marca marca) throws Exception{
        try {
            return detalleRepository.filtroMarca(marca);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> filtrarPorTalle(Talle talle) throws Exception{
        try {
            return detalleRepository.filtroTalle(talle);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> filtrarPorPrecio(Double min, Double max) throws Exception {
        try {
            return detalleRepository.filtroPrecio(min, max);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> filtrarPorTipoProducto(TipoProducto tipoProducto) throws Exception {
        try {
            return productoRepository.filtroTipoProducto(tipoProducto);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> filtrarPorNombre(Producto prod) throws Exception {
        try {
            return productoRepository.filtroNombre(prod);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> filtrarPorCategoria(Producto prod) throws Exception {
        try {
            return productoRepository.filtroCategoria(prod);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Producto> filtrarPorSexo(Producto prod) throws Exception {
        try {
            return productoRepository.filtroSexo(prod);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.repositories.DetalleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;


@Service
public class DetalleService extends BaseService<Detalle, Long> {

    private ImagenService imagenService;
    private DetalleRepository detalleRepository;

    public DetalleService(DetalleRepository detalleRepository) {
        super(detalleRepository);
    }

    @Transactional
    public Detalle saveImagenInDetalle(Long detalleId, MultipartFile file) throws Exception {
        Detalle detalle = detalleRepository.findById(detalleId).orElseThrow(() -> new Exception("No se encontro el detalle"));
        if (file != null && !file.isEmpty()) {
            Imagen imagen = imagenService.subirImagen(file);
            agregarImagenAlDetalle(detalle, imagen);
        } else {
            throw new Exception("Archivo a subir inexistente");
        }
        return detalleRepository.save(detalle);
    }

    public Detalle updateDetalle(Long detalleId, Long imagenId, MultipartFile file) throws Exception {
        Detalle detalle = detalleRepository.findById(detalleId)
                .orElseThrow(() -> new Exception("Detalle no encontrado"));

        Imagen imagenAReemplazar = detalle.getImagenes()
                .stream()
                .filter(imagen -> imagen.getId().equals(imagenId))
                .findFirst()
                .orElseThrow(() -> new Exception("Imagen no encontrada"));

        Imagen subirImagen = imagenService.subirImagen(file);

        detalle.getImagenes().remove(imagenAReemplazar);
        detalle.getImagenes().add(subirImagen);

        return detalleRepository.save(detalle);
    }


    public void agregarImagenAlDetalle(Detalle detalle, Imagen imagen){
        if (detalle.getImagenes() == null || detalle.getImagenes().isEmpty()) {
            detalle.setImagenes(new ArrayList<>());
        }
        detalle.getImagenes().add(imagen);
    }
}

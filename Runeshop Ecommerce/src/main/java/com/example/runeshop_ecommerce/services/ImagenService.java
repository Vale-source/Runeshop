package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.repositories.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Map;

@Service
public class ImagenService extends BaseService<Imagen, Long> {

    private final CloudinaryService cloudinaryService;
    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository, CloudinaryService cloudinaryService) {
        super(imagenRepository);
        this.imagenRepository = imagenRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public Imagen subirImagen(MultipartFile file) throws IOException {
        Map<String, Object> resultado = cloudinaryService.upload(file);
        String url = resultado.get("url").toString();

        Imagen imagen = Imagen.builder()
                .nombre(file.getOriginalFilename())
                .imagenUrl(url)
                .build();

        return imagenRepository.save(imagen);
    }

    public void borrarImagen(Long imagenId) throws Exception {
        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new Exception("Imagen no encontrada"));

        cloudinaryService.delete(imagen.getImagenUrl());
        imagenRepository.deleteById(imagenId);
    }

}

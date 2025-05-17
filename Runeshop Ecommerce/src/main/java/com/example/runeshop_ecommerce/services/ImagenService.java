package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Imagen;
import com.example.runeshop_ecommerce.repositories.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImagenService extends BaseService<Imagen, Long> {

    private CloudinaryService cloudinaryService;
    private ImagenRepository imagenRepository;


    public ImagenService(ImagenRepository imagenRepository) {
        super(imagenRepository);
    }

    Imagen subirImagen(MultipartFile file) throws IOException {
        Map subirResultado = cloudinaryService.upload(file);
        String imagenUrl = subirResultado.get("url").toString();
        String imagenId = subirResultado.get("public_id").toString();
        Imagen imagen = Imagen.builder()
                .nombre(file.getOriginalFilename())
                .imagenUrl(imagenUrl)
                .imagenId(imagenId)
                .build();
        return imagenRepository.save(imagen);
    }

    void borrarImagen(Imagen imagen) throws IOException {
        
    }
}

package com.example.runeshop_ecommerce.services;

import com.example.runeshop_ecommerce.entities.Direccion;
import com.example.runeshop_ecommerce.repositories.DireccionRepository;
import org.springframework.stereotype.Service;

@Service
public class DireccionService extends BaseService<Direccion, Long> {

	public DireccionService(DireccionRepository direccionRepository) {
		super(direccionRepository);
	}
}

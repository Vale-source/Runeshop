package com.example.runeshop_ecommerce.utils;

import com.example.runeshop_ecommerce.auth.AuthService;
import com.example.runeshop_ecommerce.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class AdminSetup {

	private final AuthService authService;

	@PostConstruct
	public void	initializeAdmin() throws Exception {
		try	{
			RegisterRequest adminRequest = RegisterRequest.builder()
					.nombreUsuario("Admin")
					.contrasenia("adminroot123")
					.nombre("Administrador")
					.apellido("Principal")
					.dni(42159633)
					.email("admin@example.com")
					.build();

			authService.registerAdmin(adminRequest);
			System.out.println("Administrador creado con exito");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}

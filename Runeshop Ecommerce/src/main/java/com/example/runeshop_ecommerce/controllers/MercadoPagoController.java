package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Detalle;
import com.example.runeshop_ecommerce.entities.OrdenCompra;
import com.example.runeshop_ecommerce.exception.NotStockException;
import com.example.runeshop_ecommerce.repositories.OrdenCompraRepository;
import com.example.runeshop_ecommerce.services.OrdenCompraService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.resources.preference.PreferenceBackUrls;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mercado")
public class MercadoPagoController {

    Dotenv dotenv = Dotenv.load();
    protected String mercadoPagoKey = dotenv.get("MERCADOPAGO_API_KEY_SANDBOX");

    private final OrdenCompraService ordenCompraService;

    public MercadoPagoController(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;
    }

    @GetMapping("/pago")
    public String mercado(
            @RequestParam("detallesId") List<Long> detallesId,
            @RequestParam("usuarioDireccionId") Long usuarioDireccionId
    ) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoKey);

            OrdenCompra ordenCompra = ordenCompraService.generarOrdenCompra(detallesId, usuarioDireccionId);
            List<PreferenceItemRequest> items = new ArrayList<>();

            for (Detalle detalle : ordenCompra.getDetalles()) {

                ordenCompraService.reducirStock(detalle.getId());

                Double precioFinal = (detalle.getDescuentos() != null)
                        ? detalle.getPrecioDescuento()
                        : detalle.getPrecio().getPrecioVenta();

                System.out.println("==== PREFERENCIA DE ITEM ====");
                System.out.println("ID: " + detalle.getId());
                System.out.println("Título: " + detalle.getProducto().getModelo());
                System.out.println("Imagen URL: " + detalle.getImagenes().get(0).getImagenUrl());
                System.out.println("Precio Final: " + precioFinal);
                System.out.println("=================================");

                PreferenceItemRequest item = PreferenceItemRequest.builder()
                        .id(detalle.getId().toString())
                        .title(detalle.getProducto().getModelo())
                        .pictureUrl(detalle.getImagenes().get(0).getImagenUrl())
                        .categoryId(detalle.getProducto().getCategoria().getNombre())
                        .quantity(1)
                        .currencyId("ARS")
                        .unitPrice(BigDecimal.valueOf(precioFinal))
                        .build();

                items.add(item);
            }

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                            .success("https://99e2-2803-9800-9842-7276-90db-2977-c9c5-cd22.ngrok-free.app/exito")
                            .pending("https://99e2-2803-9800-9842-7276-90db-2977-c9c5-cd22.ngrok-free.app/pendiente")
                            .failure("https://99e2-2803-9800-9842-7276-90db-2977-c9c5-cd22.ngrok-free.app/fallo")
                            .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .externalReference(ordenCompra.getId().toString())
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);


            return preference.getInitPoint();

        } catch (Exception e) {
            throw new RuntimeException("Error al crear la preferencia: " + e.getMessage());
        }
    }

    @GetMapping("/exito")
    public String pagoExitoso(
            @RequestParam(required = false) String payment_id
    ) {
        return "Pago exitoso. ID del pago " + payment_id;
    }

    @GetMapping("/fallo")
    public String pagoFallido(
            @RequestParam(required = false) String external_reference
    ) {
        Long ordenCompraId = Long.valueOf(external_reference);
        ordenCompraService.deleteOrdenCompraFallida(ordenCompraId);

        return "El pago fallo.";
    }

    @GetMapping("/pendiente")
    public String pagoPendiente() {
        return "El pago esta pendiente.";
    }
}

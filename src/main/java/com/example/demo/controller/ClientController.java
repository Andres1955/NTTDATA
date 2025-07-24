package com.example.demo.controller;

import com.example.demo.model.dto.ClientRequestDTO;
import com.example.demo.model.dto.ClientResponseDTO;
import com.example.demo.model.dto.ErrorDTO;
import com.example.demo.model.exception.ApiException;
import com.example.demo.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<?> getClientInfo(@RequestBody ClientRequestDTO request) {
        try {
            logger.info("Buscando cliente con tipo doc: {} y número: {}",
                    request.getDocumentType(),
                    request.getDocumentNumber());

            ClientResponseDTO response = clientService.getClientInfo(request);

            logger.info("Cliente encontrado - " +
                            "Primer nombre: {}, " +
                            "Segundo nombre: {}, " +
                            "Primer apellido: {}, " +
                            "Segundo apellido: {}, " +
                            "Teléfono: {}, " +
                            "Dirección: {}, " +
                            "Ciudad: {}",
                    response.getFirstName(),
                    response.getSecondName(),
                    response.getFirstLastName(),
                    response.getSecondLastName(),
                    response.getPhone(),
                    response.getAddress(),
                    response.getCity());

            return ResponseEntity.ok(response); // Código 200 (éxito) - Cuando encuentra el cliente

        } catch (ApiException e) {
            logger.warn("Error en la solicitud: {} - Código: {}",
                    e.getMessage(),
                    e.getStatus().value());
            return ResponseEntity.status(e.getStatus())
                    .body(new ErrorDTO(e.getMessage())); // Manejo de 400 y 404

        } catch (Exception e) {
            logger.error("Error interno procesando solicitud: {}", e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(new ErrorDTO("Error interno del servidor")); // Código 500 (error interno)
        }
    }
}
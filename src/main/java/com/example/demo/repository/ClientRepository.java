package com.example.demo.repository;

import com.example.demo.model.dto.ClientResponseDTO;
import com.example.demo.model.exception.NotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository {
    public ClientResponseDTO findClientByDocument(String documentType, String documentNumber) {
        // Datos mockeados solo para cédula 23445322
        if ("C".equalsIgnoreCase(documentType) && "23445322".equals(documentNumber)) {
            ClientResponseDTO response = new ClientResponseDTO();
            response.setFirstName("Juan");
            response.setSecondName("Carlos");
            response.setFirstLastName("Pérez");
            response.setSecondLastName("Gómez");
            response.setPhone("3001234567");
            response.setAddress("Calle 123 #45-67");
            response.setCity("Bogotá");
            return response;
        }
        throw new NotFoundException("Cliente no encontrado");
    }
}
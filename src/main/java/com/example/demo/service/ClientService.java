package com.example.demo.service;

import com.example.demo.model.dto.ClientRequestDTO;
import com.example.demo.model.dto.ClientResponseDTO;
import com.example.demo.model.exception.BadRequestException;
import com.example.demo.repository.ClientRepository;
import com.example.demo.util.DocumentType;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        // Validación que puede generar 400 (Bad Request)
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO getClientInfo(ClientRequestDTO request) {
        if (!DocumentType.isValid(request.getDocumentType())) {
            throw new BadRequestException("Tipo de documento no válido. Use C (Cédula) o P (Pasaporte)");
        }
        // La búsqueda puede generar 404 (Not Found)
        return clientRepository.findClientByDocument(
                request.getDocumentType(),
                request.getDocumentNumber()
        );
    }
}

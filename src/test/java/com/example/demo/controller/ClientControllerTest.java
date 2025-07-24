package com.example.demo.controller;

import com.example.demo.model.dto.ClientRequestDTO;
import com.example.demo.model.dto.ClientResponseDTO;
import com.example.demo.model.dto.ErrorDTO;
import com.example.demo.model.exception.BadRequestException;
import com.example.demo.model.exception.NotFoundException;
import com.example.demo.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    @Test
    void getClientInfo_shouldReturn200Ok_whenValidRequest() {
        // Arrange
        ClientRequestDTO request = new ClientRequestDTO();
        request.setDocumentType("C");
        request.setDocumentNumber("23445322");

        ClientResponseDTO mockResponse = new ClientResponseDTO();
        mockResponse.setFirstName("Juan");
        mockResponse.setSecondName("Carlos");
        mockResponse.setFirstLastName("Pérez");
        mockResponse.setSecondLastName("Gómez");
        mockResponse.setPhone("3001234567");
        mockResponse.setAddress("Calle 123 #45-67");
        mockResponse.setCity("Bogotá");

        when(clientService.getClientInfo(any(ClientRequestDTO.class)))
                .thenReturn(mockResponse);

        // Act
        ResponseEntity<?> response = clientController.getClientInfo(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        ClientResponseDTO responseBody = (ClientResponseDTO) response.getBody();
        assertEquals("Juan", responseBody.getFirstName());
        assertEquals("Carlos", responseBody.getSecondName());
        assertEquals("Pérez", responseBody.getFirstLastName());
        assertEquals("Gómez", responseBody.getSecondLastName());
        assertEquals("3001234567", responseBody.getPhone());
        assertEquals("Calle 123 #45-67", responseBody.getAddress());
        assertEquals("Bogotá", responseBody.getCity());
    }


    @Test
    void getClientInfo_shouldReturn400BadRequest_whenInvalidDocumentType() {
        // Arrange
        ClientRequestDTO request = new ClientRequestDTO();
        request.setDocumentType("X"); // Tipo inválido
        request.setDocumentNumber("23445322");

        when(clientService.getClientInfo(any(ClientRequestDTO.class)))
                .thenThrow(new BadRequestException("Tipo de documento no válido"));

        // Act
        ResponseEntity<?> response = clientController.getClientInfo(request);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Tipo de documento no válido", ((ErrorDTO) response.getBody()).getMessage());
    }

    @Test
    void getClientInfo_shouldReturn404NotFound_whenClientDoesNotExist() {
        // Arrange
        ClientRequestDTO request = new ClientRequestDTO();
        request.setDocumentType("C");
        request.setDocumentNumber("11111111"); // Número que no existe

        when(clientService.getClientInfo(any(ClientRequestDTO.class)))
                .thenThrow(new NotFoundException("Cliente no encontrado"));

        // Act
        ResponseEntity<?> response = clientController.getClientInfo(request);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Cliente no encontrado", ((ErrorDTO) response.getBody()).getMessage());
    }

    @Test
    void getClientInfo_shouldReturn500InternalServerError_whenUnexpectedError() {
        // Arrange
        ClientRequestDTO request = new ClientRequestDTO();
        request.setDocumentType("C");
        request.setDocumentNumber("23445322");

        when(clientService.getClientInfo(any(ClientRequestDTO.class)))
                .thenThrow(new RuntimeException("Error inesperado"));

        // Act
        ResponseEntity<?> response = clientController.getClientInfo(request);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Error interno del servidor", ((ErrorDTO) response.getBody()).getMessage());
    }
}
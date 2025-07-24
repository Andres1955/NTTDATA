package com.example.demo.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClientRequestDTO {
    @NotBlank(message = "El tipo de documento es obligatorio")
    @Pattern(regexp = "[C|P]", message = "Tipo de documento no válido. Use C (Cédula) o P (Pasaporte)")
    private String documentType;

    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;
}
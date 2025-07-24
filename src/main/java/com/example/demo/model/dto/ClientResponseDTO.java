package com.example.demo.model.dto;

import lombok.Data;

@Data
public class ClientResponseDTO {
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String phone;
    private String address;
    private String city;
}
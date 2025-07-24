package com.example.demo.util;

public enum DocumentType {
    CEDULA("C"),
    PASAPORTE("P");

    private final String code;

    DocumentType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static boolean isValid(String type) {
        if (type == null) {
            return false;
        }
        return type.equalsIgnoreCase(CEDULA.code) ||
                type.equalsIgnoreCase(PASAPORTE.code);
    }
}

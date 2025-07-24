# Servicio REST para Consulta de Clientes

Servicio backend desarrollado con Spring Boot que permite consultar información básica de clientes mediante tipo y número de documento.

## Características Principales

- Consulta de información de cliente mediante tipo (C/P) y número de documento
- Validación de parámetros de entrada
- Manejo de códigos HTTP adecuados (200, 400, 404, 500)
- Logging de solicitudes y respuestas
- Arquitectura limpia y código bien estructurado
- Pruebas unitarias para casos principales

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3.1.5
- Maven
- Lombok (para reducir código boilerplate)
- Validación de Spring
- SLF4J para logging

## Requisitos

- JDK 17 o superior
- Maven 3.6.3 o superior
- Opcional: IDE como IntelliJ IDEA o VS Code

## Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/servicio-clientes.git
   cd servicio-clientes
Compilar y empaquetar el proyecto:

bash
mvn clean package
Ejecutar la aplicación:

bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
La aplicación estará disponible en http://localhost:8090

Uso del API
Endpoint Disponible
GET /api/clients - Consulta información de un cliente

Parámetros (en el cuerpo de la solicitud):

json
{
  "documentType": "C",
  "documentNumber": "23445322"
}
Respuesta exitosa (200 OK):

json
{
  "firstName": "Juan",
  "secondName": "Carlos",
  "firstLastName": "Pérez",
  "secondLastName": "Gómez",
  "phone": "3001234567",
  "address": "Calle 123 #45-67",
  "city": "Bogotá"
}
Códigos de estado posibles:

200 OK: Cliente encontrado

400 Bad Request: Parámetros inválidos o faltantes

404 Not Found: Cliente no encontrado

500 Internal Server Error: Error inesperado

Estructura del Proyecto
text
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/        # Configuraciones
│   │   ├── controller/    # Controladores REST
│   │   ├── model/         # DTOs y excepciones
│   │   ├── repository/    # Acceso a datos
│   │   ├── service/       # Lógica de negocio
│   │   └── util/          # Utilidades
│   └── resources/         # Configuración y propiedades
└── test/                  # Pruebas unitarias
Pruebas
Ejecutar las pruebas unitarias con:

bash
mvn test
Configuración
El archivo application.properties contiene:

Puerto de la aplicación (8090)

Configuración básica de logging

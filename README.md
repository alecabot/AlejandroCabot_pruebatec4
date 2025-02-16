# Proyecto de Gestión de Reservas

Este proyecto es una aplicación de gestión de reservas de vuelos y hoteles, desarrollada con Java y Spring Boot.

## Requisitos

- Java 17 o superior
- Spring Boot initializr
- Maven
- MySQL

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/alecabot/AlejandroCabot_pruebatec4.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd AlejandroCabot_pruebatec4
    ```
3. Crea una base de datos en MySQL:
    ```sql
    CREATE DATABASE Bookingdb;
    ```
4. Actualiza el archivo `application.properties` con los detalles de tu base de datos:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/Bookingdb?serverTimezone=UTC
    spring.datasource.username=<NUEVO_USUARIO>
    spring.datasource.password=<NUEVA_CONTRASEÑA>
    ```


## Arquitectura del Proyecto
- **Controladores**: Clases que manejan las solicitudes HTTP (`HotelController`, `FlightController`, `ReservationController`).
- **Servicios**: Clases que contienen la lógica de negocio (`HotelService`, `FlightService`, `ReservationService`).
- **Repositorios**: Clases que manejan la persistencia de datos (`HotelRepository`, `FlightRepository`, `ReservationRepository`).
- **DTOs**: Clases que representan los datos transferidos entre el cliente y el servidor (`HotelDTO`, `FlightDTO`, `ReservationDTO`).

## Flujo de la Aplicación
- **Inicio**: La aplicación se inicia y expone varios endpoints para gestionar hoteles, vuelos y reservas.
- **Gestión de Hoteles**:
    - **Crear Hotel**: El usuario envía una solicitud POST a `/hotels` con los detalles del hotel.
    - **Actualizar Hotel**: El usuario envía una solicitud PUT a `/hotels/{id}` con los detalles actualizados del hotel.
    - **Eliminar Hotel**: El usuario envía una solicitud DELETE a `/hotels/{id}` para eliminar un hotel.
    - **Obtener Hoteles**: El usuario envía una solicitud GET a `/hotels` para obtener una lista de hoteles.
- **Gestión de Vuelos**:
    - **Crear Vuelo**: El usuario envía una solicitud POST a `/flights` con los detalles del vuelo.
    - **Actualizar Vuelo**: El usuario envía una solicitud PUT a `/flights/{id}` con los detalles actualizados del vuelo.
    - **Eliminar Vuelo**: El usuario envía una solicitud DELETE a `/flights/{id}` para eliminar un vuelo.
    - **Obtener Vuelos**: El usuario envía una solicitud GET a `/flights` para obtener una lista de vuelos.
- **Gestión de Reservas**:
    - **Crear Reserva**: El usuario envía una solicitud POST a `/reservations` con los detalles de la reserva.
    - **Actualizar Reserva**: El usuario envía una solicitud PUT a `/reservations/{id}` con los detalles actualizados de la reserva.
    - **Eliminar Reserva**: El usuario envía una solicitud DELETE a `/reservations/{id}` para eliminar una reserva.
    - **Obtener Reservas**: El usuario envía una solicitud GET a `/reservations` para obtener una lista de reservas.

## Componentes Clave
- **Controladores**: Clases que manejan las solicitudes HTTP (`HotelController`, `FlightController`, `ReservationController`).
- **Servicios**: Clases que contienen la lógica de negocio (`HotelService`, `FlightService`, `ReservationService`).
- **Repositorios**: Clases que manejan la persistencia de datos (`HotelRepository`, `FlightRepository`, `ReservationRepository`).
- **DTOs**: Clases que representan los datos transferidos entre el cliente y el servidor (`HotelDTO`, `FlightDTO`, `ReservationDTO`).

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

```
cabotalejandro_pruebatec2/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── alejandrocabot_pruebatec4/
│   │   │               ├── controller/
│   │   │               │   ├── HotelController.java
│   │   │               │   ├── FlightController.java
│   │   │               │   └── ReservationController.java
│   │   │               ├── dto/
│   │   │               │   ├── HotelDTO.java
│   │   │               │   ├── FlightDTO.java
│   │   │               │   └── ReservationDTO.java
│   │   │               ├── exception/
│   │   │               │   ├── NotFoundException.java
│   │   │               │   ├── SaveException.java
│   │   │               │   ├── NotDeletedException.java
│   │   │               │   ├── EmptyException.java
│   │   │               │   └── DateException.java
│   │   │               ├── model/
│   │   │               │   ├── Hotel.java
│   │   │               │   ├── Flight.java
│   │   │               │   └── Reservation.java
│   │   │               ├── repository/
│   │   │               │   ├── HotelRepository.java
│   │   │               │   ├── FlightRepository.java
│   │   │               │   └── ReservationRepository.java
│   │   │               ├── service/
│   │   │               │   ├── HotelService.java
│   │   │               │   ├── FlightService.java
│   │   │               │   └── ReservationService.java
│   │   ├── resources/
│   │   │   └── application.properties
```
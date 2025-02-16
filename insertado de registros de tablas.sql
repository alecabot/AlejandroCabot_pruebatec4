-- Inserción de datos en la tabla de hoteles
INSERT INTO hotels (hotel_code, name, place, room_type, room_price, date_from, date_to, is_booked) VALUES
                                                                                                       ('AR-0002', 'Atlantis Resort', 'Miami', 'Doble', 630, '2024-02-10', '2024-03-20', FALSE),
                                                                                                       ('AR-0003', 'Atlantis Resort 2', 'Miami', 'Triple', 820, '2024-02-10', '2024-03-23', FALSE),
                                                                                                       ('RC-0001', 'Ritz-Carlton', 'Buenos Aires', 'Single', 543, '2024-02-10', '2024-03-19', FALSE),
                                                                                                       ('RC-0002', 'Ritz-Carlton 2', 'Medellín', 'Doble', 720, '2024-02-12', '2024-04-17', FALSE),
                                                                                                       ('GH-0002', 'Grand Hyatt', 'Madrid', 'Doble', 579, '2024-04-17', '2024-05-23', FALSE),
                                                                                                       ('GH-0001', 'Grand Hyatt 2', 'Buenos Aires', 'Single', 415, '2024-01-02', '2024-02-19', FALSE),
                                                                                                       ('HL-0001', 'Hilton', 'Barcelona', 'Single', 390, '2024-01-23', '2024-11-23', FALSE),
                                                                                                       ('HL-0002', 'Hilton 2', 'Barcelona', 'Doble', 584, '2024-01-23', '2024-10-15', FALSE),
                                                                                                       ('MT-0003', 'Marriott', 'Barcelona', 'Doble', 702, '2024-02-15', '2024-03-27', FALSE),
                                                                                                       ('SH-0004', 'Sheraton', 'Madrid', 'Múltiple', 860, '2024-03-01', '2024-04-17', FALSE),
                                                                                                       ('SH-0002', 'Sheraton 2', 'Iguazú', 'Doble', 640, '2024-02-10', '2024-03-20', FALSE),
                                                                                                       ('IR-0004', 'InterContinental', 'Cartagena', 'Múltiple', 937, '2024-04-17', '2024-06-12', FALSE);

-- Inserción de datos en la tabla de vuelos
INSERT INTO flights (flight_number, origin, destination, seat_type, flight_price, departure_date, return_date) VALUES
                                                                                                                   ('BAMI-1235', 'Barcelona', 'Miami', 'Economy', 650, '2024-02-10', '2024-02-15'),
                                                                                                                   ('MIMA1420', 'Miami', 'Madrid', 'Business', 4320, '2024-02-10', '2024-02-20'),
                                                                                                                   ('MIMA-1420', 'Miami', 'Madrid', 'Economy', 2573, '2024-02-10', '2024-02-21'),
                                                                                                                   ('BABU-5536', 'Barcelona', 'Buenos Aires', 'Economy', 732, '2024-02-10', '2024-02-17'),
                                                                                                                   ('BUBA-3369', 'Buenos Aires', 'Barcelona', 'Business', 1253, '2024-02-12', '2024-02-23'),
                                                                                                                   ('IGBA-3369', 'Iguazú', 'Barcelona', 'Economy', 540, '2024-01-02', '2024-01-16'),
                                                                                                                   ('BOCA-4213', 'Bogotá', 'Cartagena', 'Economy', 800, '2024-01-23', '2024-02-05'),
                                                                                                                   ('CAME-0321', 'Cartagena', 'Medellín', 'Economy', 780, '2024-01-23', '2024-01-31'),
                                                                                                                   ('BOIG-6567', 'Bogotá', 'Iguazú', 'Business', 570, '2024-02-15', '2024-02-28'),
                                                                                                                   ('BOBA-6567', 'Bogotá', 'Buenos Aires', 'Economy', 398, '2024-03-01', '2024-03-14'),
                                                                                                                   ('BOMA-4442', 'Bogotá', 'Madrid', 'Economy', 1100, '2024-02-10', '2024-02-24'),
                                                                                                                   ('MEMI-9986', 'Medellín', 'Miami', 'Business', 1164, '2024-04-17', '2024-05-02');
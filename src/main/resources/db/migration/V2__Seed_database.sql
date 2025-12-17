-- ============================
-- TABLE: user
-- ============================
INSERT IGNORE INTO user (name, surname, email, born_date, username, password, role) VALUES
                                                                    ('Juan', 'Pérez', 'juan.perez@example.com', '1990-05-12', 'user', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
                                                                    ('Ana', 'Ruiz', 'ana.ruiz@example.com', '2000-10-08', 'admin', '$2a$12$VDY8aHe0tZHYLniEJilwLuFKK9EFZE04JCrRltJRXurBmvc/eEn56', 'ADMIN');

-- ============================
-- TABLE: category_product
-- ============================
INSERT IGNORE INTO category_product (label, slug) VALUES
    ('Hamburguesas', 'hamburguesas'),
    ('Acompañamientos', 'acompanamientos'),
    ('Bebidas', 'bebidas'),
    ('Postres', 'postres'),
    ('Desayunos', 'desayunos');


-- ============================
-- TABLE: product
-- ============================
INSERT IGNORE INTO product (label, slug, `desc`, price, category_product_id) VALUES
                                                                          ('Teclado Mecánico', 'teclado-mecanico', 'Teclado con switches rojos.', 49.90, 1),
                                                                          ('Ratón Gaming', 'raton-gaming', 'Ratón RGB de alta precisión.', 29.90, 1),
                                                                          ('Tarjeta Gráfica GTX 1660', 'gtx-1660', 'GeForce GTX 1660 6GB.', 229.00, 2),
                                                                          ('Memoria RAM 16GB', 'ram-16gb', 'DDR4 3200MHz.', 69.90, 2),
                                                                          ('Alfombrilla XXL', 'alfombrilla-xxl', 'Alfombrilla gaming extendida.', 12.99, 3);

-- ============================
-- TABLE: user_order
-- ============================
INSERT IGNORE INTO user_order (status, user_id) VALUES
                                             ('PENDING', 1),
                                             ('DELIVERED', 2);

-- ============================
-- TABLE: order_item
-- ============================
INSERT IGNORE INTO order_item (quantity, product_id, user_order_id) VALUES
                                                              (2, 1, 1),
                                                              (1, 3, 1),
                                                              (1, 2, 2),
                                                              (4, 5, 2);

-- ============================
-- TABLE: log
-- ============================
INSERT IGNORE INTO log (info, timestamp) VALUES
                                      ('Usuario Juan inició sesión', '2025-01-01 10:00:00'),
                                      ('Pedido creado', '2025-01-02 14:22:10'),
                                      ('Producto actualizado', '2025-01-05 18:45:31'),
                                      ('Reporte generado', '2025-01-08 09:15:55'),
                                      ('Usuario Ana cerró sesión', '2025-01-09 21:10:10');

-- ============================
-- TABLE: category_pc
-- ============================
INSERT IGNORE INTO category_pc (label, price) VALUES
                                           ('Básico', 2.50),
                                           ('Gaming', 5.00),
                                           ('Streaming', 7.00),
                                           ('Edición de Video', 6.50);

-- ============================
-- TABLE: pc
-- ============================
INSERT IGNORE INTO pc (label, slug, runtime, specs, working_since, image, category_pc_id) VALUES
                                                                                ('PC Gamer 1', 'pc-gamer-1', '12', 'Ryzen 5, RTX 3060, 16GB RAM', '2023-05-12', 'pc-gamer-1.jpg', 2),
                                                                                ('PC Gamer 2', 'pc-gamer-2', '8', 'i5, RTX 2060, 16GB RAM', '2022-11-02', 'pc-gamer-2.jpg', 2),
                                                                                ('PC Streaming 1', 'pc-stream-1', '5', 'Ryzen 7, Capture Card, 32GB RAM', '2023-01-20', 'pc-stream-1.jpg', 3),
                                                                                ('PC Básico 1', 'pc-basic-1', '20', 'i3, Integrada, 8GB RAM', '2021-08-10', 'pc-basic-1.jpg', 1),
                                                                                ('PC Edición 1', 'pc-edit-1', '10', 'Ryzen 9, 64GB RAM', '2023-07-15', 'pc-edit-1.jpg', 4);

-- ============================
-- TABLE: booking
-- ============================
INSERT IGNORE INTO booking (hours, pc_id, user_id) VALUES
                                                (2, 1, 1),
                                                (4, 3, 2);

-- ============================
-- TABLE: report
-- ============================
INSERT IGNORE INTO report (priority, `desc`, pc_id, user_id) VALUES
                                                          (1, 'La GPU está fallando', 1, 1),
                                                          (2, 'Ventiladores ruidosos', 2, 2);

-- Más usuarios
INSERT IGNORE INTO user (name, surname, email, born_date, username, password, role) VALUES
('Carlos', 'Gómez', 'carlos.gomez@example.com', '1985-03-15', 'carlos', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('María', 'López', 'maria.lopez@example.com', '1995-07-22', 'maria', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('Pedro', 'Martínez', 'pedro.martinez@example.com', '1992-11-30', 'pedro', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('Laura', 'Hernández', 'laura.hernandez@example.com', '1988-09-10', 'laura', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('José', 'Díaz', 'jose.diaz@example.com', '1998-01-05', 'jose', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('Carmen', 'Morales', 'carmen.morales@example.com', '1993-12-18', 'carmen', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('Antonio', 'Ortiz', 'antonio.ortiz@example.com', '1987-06-25', 'antonio', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('Isabel', 'Ramírez', 'isabel.ramirez@example.com', '1996-04-12', 'isabel', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT');

-- Más pedidos con created_at en diferentes meses
INSERT IGNORE INTO user_order (status, user_id, created_at) VALUES
('CONFIRMED', 3, '2025-01-15 10:00:00'),
('SHIPPED', 4, '2025-01-20 14:00:00'),
('DELIVERED', 5, '2025-02-10 09:00:00'),
('CONFIRMED', 6, '2025-02-25 16:00:00'),
('DELIVERED', 7, '2025-03-05 11:00:00'),
('SHIPPED', 8, '2025-03-18 13:00:00'),
('CONFIRMED', 9, '2025-04-02 15:00:00'),
('DELIVERED', 10, '2025-04-15 10:00:00');

-- Más order_items para los nuevos pedidos
INSERT IGNORE INTO order_item (quantity, product_id, user_order_id) VALUES
(1, 1, 3), (2, 2, 3), (1, 3, 4), (3, 4, 4), (2, 5, 5), (1, 1, 6), (4, 2, 6), (1, 3, 7), (2, 4, 7), (3, 5, 8),
(1, 1, 9), (2, 2, 9), (1, 3, 10), (3, 4, 3), (2, 5, 4), (1, 1, 5), (4, 2, 6), (1, 3, 7), (2, 4, 8), (3, 5, 9),
(1, 1, 10), (2, 2, 3), (1, 3, 4), (3, 4, 5), (2, 5, 6), (1, 1, 7), (4, 2, 8), (1, 3, 9), (2, 4, 10), (3, 5, 3);

-- Más reportes
INSERT IGNORE INTO report (priority, `desc`, pc_id, user_id) VALUES
(3, 'Pantalla parpadeando', 3, 3),
(1, 'No enciende', 4, 4),
(2, 'Lento rendimiento', 5, 5),
(3, 'Sobrecalentamiento', 1, 6),
(1, 'Error de conexión', 2, 7),
(2, 'Ruido excesivo', 3, 8),
(3, 'Actualización necesaria', 4, 1),
(1, 'Falla en periféricos', 5, 2),
(2, 'Problema de red', 1, 3),
(3, 'Configuración incorrecta', 2, 4);
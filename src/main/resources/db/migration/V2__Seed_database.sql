-- ============================
-- TABLE: user
-- ============================
INSERT INTO user (name, surname, email, born_date, username, password, role) VALUES
                                                                    ('Juan', 'Pérez', 'juan.perez@example.com', '1990-05-12', 'user', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
                                                                    ('Ana', 'Ruiz', 'ana.ruiz@example.com', '2000-10-08', 'admin', '$2a$12$VDY8aHe0tZHYLniEJilwLuFKK9EFZE04JCrRltJRXurBmvc/eEn56', 'ADMIN');

-- ============================
-- TABLE: category_product
-- ============================
INSERT INTO category_product (label, slug) VALUES
    ('Hamburguesas', 'hamburguesas'),
    ('Acompañamientos', 'acompanamientos'),
    ('Bebidas', 'bebidas'),
    ('Postres', 'postres'),
    ('Desayunos', 'desayunos');


-- ============================
-- TABLE: product
-- ============================
INSERT INTO product (label, slug, `desc`, price, category_product_id) VALUES
                                                                          ('Teclado Mecánico', 'teclado-mecanico', 'Teclado con switches rojos.', 49.90, 1),
                                                                          ('Ratón Gaming', 'raton-gaming', 'Ratón RGB de alta precisión.', 29.90, 1),
                                                                          ('Tarjeta Gráfica GTX 1660', 'gtx-1660', 'GeForce GTX 1660 6GB.', 229.00, 2),
                                                                          ('Memoria RAM 16GB', 'ram-16gb', 'DDR4 3200MHz.', 69.90, 2),
                                                                          ('Alfombrilla XXL', 'alfombrilla-xxl', 'Alfombrilla gaming extendida.', 12.99, 3);

-- ============================
-- TABLE: user_order
-- ============================
INSERT INTO user_order (status, user_id) VALUES
                                             ('pending', 1),
                                             ('completed', 2);

-- ============================
-- TABLE: order_item
-- ============================
INSERT INTO order_item (quantity, product_id, user_order_id) VALUES
                                                              (2, 1, 1),
                                                              (1, 3, 1),
                                                              (1, 2, 2),
                                                              (4, 5, 2);

-- ============================
-- TABLE: log
-- ============================
INSERT INTO log (info, timestamp) VALUES
                                      ('Usuario Juan inició sesión', '2025-01-01 10:00:00'),
                                      ('Pedido creado', '2025-01-02 14:22:10'),
                                      ('Producto actualizado', '2025-01-05 18:45:31'),
                                      ('Reporte generado', '2025-01-08 09:15:55'),
                                      ('Usuario Ana cerró sesión', '2025-01-09 21:10:10');

-- ============================
-- TABLE: category_pc
-- ============================
INSERT INTO category_pc (label, price) VALUES
                                           ('Básico', 2.50),
                                           ('Gaming', 5.00),
                                           ('Streaming', 7.00),
                                           ('Edición de Video', 6.50);

-- ============================
-- TABLE: pc
-- ============================
INSERT INTO pc (label, slug, runtime, specs, working_since, image, category_pc_id) VALUES
                                                                                ('PC Gamer 1', 'pc-gamer-1', '12', 'Ryzen 5, RTX 3060, 16GB RAM', '2023-05-12', 'pc-gamer-1.jpg', 2),
                                                                                ('PC Gamer 2', 'pc-gamer-2', '8', 'i5, RTX 2060, 16GB RAM', '2022-11-02', 'pc-gamer-2.jpg', 2),
                                                                                ('PC Streaming 1', 'pc-stream-1', '5', 'Ryzen 7, Capture Card, 32GB RAM', '2023-01-20', 'pc-stream-1.jpg', 3),
                                                                                ('PC Básico 1', 'pc-basic-1', '20', 'i3, Integrada, 8GB RAM', '2021-08-10', 'pc-basic-1.jpg', 1),
                                                                                ('PC Edición 1', 'pc-edit-1', '10', 'Ryzen 9, 64GB RAM', '2023-07-15', 'pc-edit-1.jpg', 4);

-- ============================
-- TABLE: booking
-- ============================
INSERT INTO booking (hours, pc_id, user_id) VALUES
                                                (2, 1, 1),
                                                (4, 3, 2);

-- ============================
-- TABLE: report
-- ============================
INSERT INTO report (priority, `desc`, pc_id, user_id) VALUES
                                                          (1, 'La GPU está fallando', 1, 1),
                                                          (2, 'Ventiladores ruidosos', 2, 2);
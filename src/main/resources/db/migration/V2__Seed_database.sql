-- ============================
-- TABLE: user
-- ============================
INSERT IGNORE INTO users (name, surname, email, born_date, username, password, role) VALUES
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
INSERT IGNORE INTO product (label, slug, description, price, image, category_product_id) VALUES
                                                                          ('Teclado Mecánico', 'teclado-mecanico', 'Teclado con switches rojos.', 49.90, 'burger-1.png', 1),
                                                                          ('Ratón Gaming', 'raton-gaming', 'Ratón RGB de alta precisión.', 29.90, 'burger-1.png', 1),
                                                                          ('Tarjeta Gráfica GTX 1660', 'gtx-1660', 'GeForce GTX 1660 6GB.', 229.00, 'burger-1.png', 2),
                                                                          ('Memoria RAM 16GB', 'ram-16gb', 'DDR4 3200MHz.', 69.90, 'burger-1.png', 2),
                                                                          ('Alfombrilla XXL', 'alfombrilla-xxl', 'Alfombrilla gaming extendida.', 12.99, 'burger-1.png', 3);

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

INSERT IGNORE INTO category_pc (label, slug, price) VALUES
                                           ('Básico', 'basico', 2.50),
                                           ('Gaming', 'gaming', 5.00),
                                           ('Streaming', 'streaming', 7.00),
                                           ('Edición de Video', 'edicion-de-video', 6.50),
                                           ('VIP', 'vip', 15.00),
                                           ('Workstation', 'workstation', 10.00),
                                           ('Esports', 'esports', 8.00);


-- ============================
-- TABLE: pc
-- ============================
INSERT IGNORE INTO pc (label, slug, runtime, specs, working_since, image, status, category_pc_id) VALUES
                                                                                ('PC Gamer 1', 'pc-gamer-1', '12', 'Ryzen 5, RTX 3060, 16GB RAM', '2023-05-12', 'pc-1.png', 'AVAILABLE', 2),
                                                                                ('PC Gamer 2', 'pc-gamer-2', '8', 'i5, RTX 2060, 16GB RAM', '2022-11-02', 'pc-1.png', 'AVAILABLE', 2),
                                                                                ('PC Streaming 1', 'pc-stream-1', '5', 'Ryzen 7, Capture Card, 32GB RAM', '2023-01-20', 'pc-1.png', 'AVAILABLE', 3),
                                                                                ('PC Básico 1', 'pc-basic-1', '20', 'i3, Integrada, 8GB RAM', '2021-08-10', 'pc-1.png', 'AVAILABLE', 1),
                                                                                ('PC Edición 1', 'pc-edit-1', '10', 'Ryzen 9, 64GB RAM', '2023-07-15', 'pc-1.png', 'AVAILABLE', 4);

-- ============================
-- TABLE: booking
-- ============================
INSERT IGNORE INTO booking (hours, pc_id, user_id) VALUES
                                                (2, 1, 1),
                                                (4, 3, 2);


-- Más usuarios
INSERT IGNORE INTO users (name, surname, email, born_date, username, password, role) VALUES
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


-- Más reportes
-- Más reportes

-- Más reportes
-- Más reportes
INSERT IGNORE INTO report (priority, `subject`, description, pc_id, user_id, created_at, `status`) VALUES
(3, 'Pantalla parpadeando', 'Pantalla parpadeando, la pantalla parpadea constantemente.', 3, 3, '2025-01-15 10:00:00', 'PENDING'),
(1, 'No enciende', 'No enciende, la computadora no enciende.', 4, 4, '2025-01-20 14:00:00', 'PENDING'),
(2, 'Lento rendimiento', 'Lento rendimiento, la computadora es muy lenta.', 5, 5, '2025-02-10 09:00:00', 'IN_PROGRESS'),
(3, 'Sobrecalentamiento', 'Sobrecalentamiento, la computadora se calienta mucho.', 1, 6, '2025-02-25 16:00:00', 'IN_PROGRESS'),
(1, 'Error de conexión', 'Error de conexión, la computadora no se conecta.', 2, 7, '2025-03-05 11:00:00', 'IN_PROGRESS'),
(2, 'Ruido excesivo', 'Ruido excesivo, la computadora hace mucho ruido.', 3, 8, '2025-03-18 13:00:00', 'IN_PROGRESS'),
(3, 'Actualización necesaria', 'Actualización necesaria, la computadora necesita una actualización.', 4, 1, '2025-04-02 15:00:00', 'RESOLVED'),
(1, 'Falla en periféricos', 'Falla en periféricos, la computadora tiene problemas con los periféricos.', 5, 2, '2025-04-15 10:00:00', 'RESOLVED'),
(2, 'Problema de red', 'Problema de red, la computadora tiene problemas de red.', 1, 3, '2025-04-20 14:00:00', 'RESOLVED'),
(3, 'Configuración incorrecta', 'Configuración incorrecta, la computadora tiene una configuración incorrecta.', 2, 4, '2025-04-25 16:00:00', 'RESOLVED');

-- ============================
-- 50 Additional Users
-- ============================
INSERT IGNORE INTO users (name, surname, email, born_date, username, password, role) VALUES
('User11', 'Surname11', 'user11@example.com', '1985-06-15', 'user11', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User12', 'Surname12', 'user12@example.com', '1992-04-22', 'user12', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User13', 'Surname13', 'user13@example.com', '1988-11-30', 'user13', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User14', 'Surname14', 'user14@example.com', '1995-09-10', 'user14', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User15', 'Surname15', 'user15@example.com', '1980-01-05', 'user15', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User16', 'Surname16', 'user16@example.com', '1993-12-18', 'user16', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User17', 'Surname17', 'user17@example.com', '1987-06-25', 'user17', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User18', 'Surname18', 'user18@example.com', '1996-04-12', 'user18', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User19', 'Surname19', 'user19@example.com', '1990-05-12', 'user19', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User20', 'Surname20', 'user20@example.com', '2000-10-08', 'user20', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User21', 'Surname21', 'user21@example.com', '1985-03-15', 'user21', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User22', 'Surname22', 'user22@example.com', '1995-07-22', 'user22', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User23', 'Surname23', 'user23@example.com', '1992-11-30', 'user23', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User24', 'Surname24', 'user24@example.com', '1988-09-10', 'user24', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User25', 'Surname25', 'user25@example.com', '1998-01-05', 'user25', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User26', 'Surname26', 'user26@example.com', '1993-12-18', 'user26', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User27', 'Surname27', 'user27@example.com', '1987-06-25', 'user27', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User28', 'Surname28', 'user28@example.com', '1996-04-12', 'user28', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User29', 'Surname29', 'user29@example.com', '1985-06-15', 'user29', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User30', 'Surname30', 'user30@example.com', '1992-04-22', 'user30', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User31', 'Surname31', 'user31@example.com', '1988-11-30', 'user31', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User32', 'Surname32', 'user32@example.com', '1995-09-10', 'user32', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User33', 'Surname33', 'user33@example.com', '1980-01-05', 'user33', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User34', 'Surname34', 'user34@example.com', '1993-12-18', 'user34', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User35', 'Surname35', 'user35@example.com', '1987-06-25', 'user35', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User36', 'Surname36', 'user36@example.com', '1996-04-12', 'user36', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User37', 'Surname37', 'user37@example.com', '1990-05-12', 'user37', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User38', 'Surname38', 'user38@example.com', '2000-10-08', 'user38', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User39', 'Surname39', 'user39@example.com', '1985-03-15', 'user39', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User40', 'Surname40', 'user40@example.com', '1995-07-22', 'user40', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User41', 'Surname41', 'user41@example.com', '1992-11-30', 'user41', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User42', 'Surname42', 'user42@example.com', '1988-09-10', 'user42', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User43', 'Surname43', 'user43@example.com', '1998-01-05', 'user43', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User44', 'Surname44', 'user44@example.com', '1993-12-18', 'user44', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User45', 'Surname45', 'user45@example.com', '1987-06-25', 'user45', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User46', 'Surname46', 'user46@example.com', '1996-04-12', 'user46', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User47', 'Surname47', 'user47@example.com', '1985-06-15', 'user47', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User48', 'Surname48', 'user48@example.com', '1992-04-22', 'user48', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User49', 'Surname49', 'user49@example.com', '1988-11-30', 'user49', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User50', 'Surname50', 'user50@example.com', '1995-09-10', 'user50', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User51', 'Surname51', 'user51@example.com', '1980-01-05', 'user51', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User52', 'Surname52', 'user52@example.com', '1993-12-18', 'user52', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User53', 'Surname53', 'user53@example.com', '1987-06-25', 'user53', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User54', 'Surname54', 'user54@example.com', '1996-04-12', 'user54', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User55', 'Surname55', 'user55@example.com', '1990-05-12', 'user55', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User56', 'Surname56', 'user56@example.com', '2000-10-08', 'user56', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User57', 'Surname57', 'user57@example.com', '1985-03-15', 'user57', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User58', 'Surname58', 'user58@example.com', '1995-07-22', 'user58', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User59', 'Surname59', 'user59@example.com', '1992-11-30', 'user59', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT'),
('User60', 'Surname60', 'user60@example.com', '1988-09-10', 'user60', '$2a$12$FxSD7GSeKwbw/zGf0QsXdOnzMB4FbGPswtNLdkbUah52/qv7DAd1S', 'CLIENT');

-- ============================
-- 50 Additional Product Categories
-- ============================
INSERT IGNORE INTO category_product (label, slug) VALUES
('Hamburguesas de Neón', 'hamburguesas-de-neon'),
('Sushi Ciberpunk', 'sushi-ciberpunk'),
('Pizza-Sintetiza', 'pizza-sintetiza'),
('Tacos Bio-Hack', 'tacos-bio-hack'),
('Ramen de Silicio', 'ramen-de-silicio'),
('Bebidas Electrolíticas', 'bebidas-electroliticas'),
('Snacks Cuánticos', 'snacks-cuanticos'),
('Postres Holográficos', 'postres-holograficos'),
('Café de Materia Oscura', 'cafe-materia-oscura'),
('Batidos de Nanobots', 'batidos-nanobots'),
('Teclados Mecatrónicos', 'teclados-mecatronicos'),
('Ratones Ópticos Laser', 'ratones-opticos-laser'),
('Monitores de Plasma', 'monitores-plasma'),
('Tarjetas Gráficas Pro', 'tarjetas-graficas-pro'),
('Procesadores i99', 'procesadores-i99'),
('RAM Hiper-Veloz', 'ram-hiper-veloz'),
('Discos de Estado Neural', 'discos-estado-neural'),
('Fuentes de Energía Fusión', 'fuentes-energia-fusion'),
('Placas Base Estelares', 'placas-base-estelares'),
('Cajas de Titanio', 'cajas-titanio'),
('Cascos VR 80K', 'cascos-vr-80k'),
('Guantes Hápticos', 'guantes-hapticos'),
('Sillas de Gravedad Cero', 'sillas-gravedad-cero'),
('Mesas de Luz Sólida', 'mesas-luz-solida'),
('Cámaras de Stream 4D', 'camaras-stream-4d'),
('Micrófonos de Vacío', 'microfonos-vacio'),
('Altavoces de Resonancia', 'altavoces-resonancia'),
('Cableado de Fibra Óptica', 'cableado-fibra-optica'),
('Routers Cuánticos', 'routers-cuanticos'),
('Repetidores de Señal', 'repetidores-senal'),
('Software de IA', 'software-ia'),
('Sistemas Operativos Pro', 'sistemas-operativos-pro'),
('Scripts de Automatización', 'scripts-automatizacion'),
('Plugins de Realidad', 'plugins-realidad'),
('Assets 3D', 'assets-3d'),
('Seguridad Firewall X', 'seguridad-firewall-x'),
('Antivirus Deflector', 'antivirus-deflector'),
('Encriptadores de Red', 'encriptadores-red'),
('Billeteras Crypto', 'billeteras-crypto'),
('Minería de Datos', 'mineria-datos'),
('Componentes de Drone', 'componentes-drone'),
('Motores de Propulsión', 'motores-propulsion'),
('Sensores de Movimiento', 'sensores-movimiento'),
('Baterías de Ion-S', 'baterias-ion-s'),
('Kits de Robótica', 'kits-robotica'),
('Herramientas de Soldadura', 'herramientas-soldadura'),
('Impresoras 3D Bio', 'impresoras-3d-bio'),
('Filamentos Especiales', 'filamentos-especiales'),
('Resinas Tóxicas', 'resinas-toxicas'),
('Accesorios de Estilo', 'accesorios-estilo');

-- ============================
-- 50 Additional Products
-- ============================
INSERT IGNORE INTO product (label, slug, description, price, image, category_product_id) VALUES
('Product 6', 'product-6', 'Desc 6', 99.99, 'burger-1.png', 1), ('Product 7', 'product-7', 'Desc 7', 89.99, 'burger-1.png', 2), ('Product 8', 'product-8', 'Desc 8', 79.99, 'burger-1.png', 3), ('Product 9', 'product-9', 'Desc 9', 69.99, 'burger-1.png', 4), ('Product 10', 'product-10', 'Desc 10', 59.99, 'burger-1.png', 5),
('Product 11', 'product-11', 'Desc 11', 49.99, 'burger-1.png', 1), ('Product 12', 'product-12', 'Desc 12', 39.99, 'burger-1.png', 2), ('Product 13', 'product-13', 'Desc 13', 29.99, 'burger-1.png', 3), ('Product 14', 'product-14', 'Desc 14', 19.99, 'burger-1.png', 4), ('Product 15', 'product-15', 'Desc 15', 109.99, 'burger-1.png', 5),
('Product 16', 'product-16', 'Desc 16', 119.99, 'burger-1.png', 1), ('Product 17', 'product-17', 'Desc 17', 129.99, 'burger-1.png', 2), ('Product 18', 'product-18', 'Desc 18', 139.99, 'burger-1.png', 3), ('Product 19', 'product-19', 'Desc 19', 149.99, 'burger-1.png', 4), ('Product 20', 'product-20', 'Desc 20', 159.99, 'burger-1.png', 5),
('Product 21', 'product-21', 'Desc 21', 169.99, 'burger-1.png', 6), ('Product 22', 'product-22', 'Desc 22', 179.99, 'burger-1.png', 7), ('Product 23', 'product-23', 'Desc 23', 189.99, 'burger-1.png', 8), ('Product 24', 'product-24', 'Desc 24', 199.99, 'burger-1.png', 9), ('Product 25', 'product-25', 'Desc 25', 209.99, 'burger-1.png', 10),
('Product 26', 'product-26', 'Desc 26', 219.99, 'burger-1.png', 11), ('Product 27', 'product-27', 'Desc 27', 229.99, 'burger-1.png', 12), ('Product 28', 'product-28', 'Desc 28', 239.99, 'burger-1.png', 13), ('Product 29', 'product-29', 'Desc 29', 249.99, 'burger-1.png', 14), ('Product 30', 'product-30', 'Desc 30', 259.99, 'burger-1.png', 15),
('Product 31', 'product-31', 'Desc 31', 269.99, 'burger-1.png', 16), ('Product 32', 'product-32', 'Desc 32', 279.99, 'burger-1.png', 17), ('Product 33', 'product-33', 'Desc 33', 289.99, 'burger-1.png', 18), ('Product 34', 'product-34', 'Desc 34', 299.99, 'burger-1.png', 19), ('Product 35', 'product-35', 'Desc 35', 309.99, 'burger-1.png', 20),
('Product 36', 'product-36', 'Desc 36', 319.99, 'burger-1.png', 21), ('Product 37', 'product-37', 'Desc 37', 329.99, 'burger-1.png', 22), ('Product 38', 'product-38', 'Desc 38', 339.99, 'burger-1.png', 23), ('Product 39', 'product-39', 'Desc 39', 349.99, 'burger-1.png', 24), ('Product 40', 'product-40', 'Desc 40', 359.99, 'burger-1.png', 25),
('Product 41', 'product-41', 'Desc 41', 369.99, 'burger-1.png', 26), ('Product 42', 'product-42', 'Desc 42', 379.99, 'burger-1.png', 27), ('Product 43', 'product-43', 'Desc 43', 389.99, 'burger-1.png', 28), ('Product 44', 'product-44', 'Desc 44', 399.99, 'burger-1.png', 29), ('Product 45', 'product-45', 'Desc 45', 409.99, 'burger-1.png', 30),
('Product 46', 'product-46', 'Desc 46', 419.99, 'burger-1.png', 31), ('Product 47', 'product-47', 'Desc 47', 429.99, 'burger-1.png', 32), ('Product 48', 'product-48', 'Desc 48', 439.99, 'burger-1.png', 33), ('Product 49', 'product-49', 'Desc 49', 449.99, 'burger-1.png', 34), ('Product 50', 'product-50', 'Desc 50', 459.99, 'burger-1.png', 35),
('Product 51', 'product-51', 'Desc 51', 469.99, 'burger-1.png', 36), ('Product 52', 'product-52', 'Desc 52', 479.99, 'burger-1.png', 37), ('Product 53', 'product-53', 'Desc 53', 489.99, 'burger-1.png', 38), ('Product 54', 'product-54', 'Desc 54', 499.99, 'burger-1.png', 39), ('Product 55', 'product-55', 'Desc 55', 509.99, 'burger-1.png', 40);

-- ============================
-- 50 Additional Logs
-- ============================
INSERT IGNORE INTO log (info, timestamp) VALUES
('Action 6', '2025-01-10 10:00:00'), ('Action 7', '2025-01-11 11:00:00'), ('Action 8', '2025-01-12 12:00:00'), ('Action 9', '2025-01-13 13:00:00'), ('Action 10', '2025-01-14 14:00:00'),
('Action 11', '2025-01-15 15:00:00'), ('Action 12', '2025-01-16 16:00:00'), ('Action 13', '2025-01-17 17:00:00'), ('Action 14', '2025-01-18 18:00:00'), ('Action 15', '2025-01-19 19:00:00'),
('Action 16', '2025-01-20 20:00:00'), ('Action 17', '2025-01-21 21:00:00'), ('Action 18', '2025-01-22 22:00:00'), ('Action 19', '2025-01-23 23:00:00'), ('Action 20', '2025-01-24 00:00:00'),
('Action 21', '2025-01-25 01:00:00'), ('Action 22', '2025-01-26 02:00:00'), ('Action 23', '2025-01-27 03:00:00'), ('Action 24', '2025-01-28 04:00:00'), ('Action 25', '2025-01-29 05:00:00'),
('Action 26', '2025-01-30 06:00:00'), ('Action 27', '2025-01-31 07:00:00'), ('Action 28', '2025-02-01 08:00:00'), ('Action 29', '2025-02-02 09:00:00'), ('Action 30', '2025-02-03 10:00:00'),
('Action 31', '2025-02-04 11:00:00'), ('Action 32', '2025-02-05 12:00:00'), ('Action 33', '2025-02-06 13:00:00'), ('Action 34', '2025-02-07 14:00:00'), ('Action 35', '2025-02-08 15:00:00'),
('Action 36', '2025-02-09 16:00:00'), ('Action 37', '2025-02-10 17:00:00'), ('Action 38', '2025-02-11 18:00:00'), ('Action 39', '2025-02-12 19:00:00'), ('Action 40', '2025-02-13 20:00:00'),
('Action 41', '2025-02-14 21:00:00'), ('Action 42', '2025-02-15 22:00:00'), ('Action 43', '2025-02-16 23:00:00'), ('Action 44', '2025-02-17 00:00:00'), ('Action 45', '2025-02-18 01:00:00'),
('Action 46', '2025-02-19 02:00:00'), ('Action 47', '2025-02-20 03:00:00'), ('Action 48', '2025-02-21 04:00:00'), ('Action 49', '2025-02-22 05:00:00'), ('Action 50', '2025-02-23 06:00:00'),
('Action 51', '2025-02-24 07:00:00'), ('Action 52', '2025-02-25 08:00:00'), ('Action 53', '2025-02-26 09:00:00'), ('Action 54', '2025-02-27 10:00:00'), ('Action 55', '2025-02-28 11:00:00');



-- ============================
-- 50 Additional PCs
-- ============================
INSERT IGNORE INTO pc (label, slug, runtime, specs, working_since, image, status, category_pc_id) VALUES
('PC 6', 'pc-6', '10', 'i7, 32GB RAM', '2023-01-01', 'pc-1.png', 'AVAILABLE', 1), ('PC 7', 'pc-7', '12', 'i9, 64GB RAM', '2023-01-02', 'pc-1.png', 'AVAILABLE', 2), ('PC 8', 'pc-8', '8', 'Ryzen 7, 16GB RAM', '2023-01-03', 'pc-1.png', 'AVAILABLE', 3), ('PC 9', 'pc-9', '15', 'Ryzen 5, 16GB RAM', '2023-01-04', 'pc-1.png', 'AVAILABLE', 4), ('PC 10', 'pc-10', '20', 'i5, 8GB RAM', '2023-01-05', 'pc-1.png', 'AVAILABLE', 5),
('PC 11', 'pc-11', '10', 'Specs 11', '2023-01-06', 'pc-1.png', 'AVAILABLE', 6), ('PC 12', 'pc-12', '12', 'Specs 12', '2023-01-07', 'pc-1.png', 'AVAILABLE', 7), ('PC 13', 'pc-13', '8', 'Specs 13', '2023-01-08', 'pc-1.png', 'AVAILABLE', 1), ('PC 14', 'pc-14', '15', 'Specs 14', '2023-01-09', 'pc-1.png', 'AVAILABLE', 2), ('PC 15', 'pc-15', '20', 'Specs 15', '2023-01-10', 'pc-1.png', 'AVAILABLE', 3),
('PC 16', 'pc-16', '10', 'Specs 16', '2023-01-11', 'pc-1.png', 'AVAILABLE', 4), ('PC 17', 'pc-17', '12', 'Specs 17', '2023-01-12', 'pc-1.png', 'AVAILABLE', 5), ('PC 18', 'pc-18', '8', 'Specs 18', '2023-01-13', 'pc-1.png', 'AVAILABLE', 6), ('PC 19', 'pc-19', '15', 'Specs 19', '2023-01-14', 'pc-1.png', 'AVAILABLE', 7), ('PC 20', 'pc-20', '20', 'Specs 20', '2023-01-15', 'pc-1.png', 'AVAILABLE', 1),
('PC 21', 'pc-21', '10', 'Specs 21', '2023-01-16', 'pc-1.png', 'AVAILABLE', 2), ('PC 22', 'pc-22', '12', 'Specs 22', '2023-01-17', 'pc-1.png', 'AVAILABLE', 3), ('PC 23', 'pc-23', '8', 'Specs 23', '2023-01-18', 'pc-1.png', 'AVAILABLE', 4), ('PC 24', 'pc-24', '15', 'Specs 24', '2023-01-19', 'pc-1.png', 'AVAILABLE', 5), ('PC 25', 'pc-25', '20', 'Specs 25', '2023-01-20', 'pc-1.png', 'AVAILABLE', 6),
('PC 26', 'pc-26', '10', 'Specs 26', '2023-01-21', 'pc-1.png', 'AVAILABLE', 7), ('PC 27', 'pc-27', '12', 'Specs 27', '2023-01-22', 'pc-1.png', 'AVAILABLE', 1), ('PC 28', 'pc-28', '8', 'Specs 28', '2023-01-23', 'pc-1.png', 'AVAILABLE', 2), ('PC 29', 'pc-29', '15', 'Specs 29', '2023-01-24', 'pc-1.png', 'AVAILABLE', 3), ('PC 30', 'pc-30', '20', 'Specs 30', '2023-01-25', 'pc-1.png', 'AVAILABLE', 4),
('PC 31', 'pc-31', '10', 'Specs 31', '2023-01-26', 'pc-1.png', 'AVAILABLE', 5), ('PC 32', 'pc-32', '12', 'Specs 32', '2023-01-27', 'pc-1.png', 'AVAILABLE', 6), ('PC 33', 'pc-33', '8', 'Specs 33', '2023-01-28', 'pc-1.png', 'AVAILABLE', 7), ('PC 34', 'pc-34', '15', 'Specs 34', '2023-01-29', 'pc-1.png', 'AVAILABLE', 1), ('PC 35', 'pc-35', '20', 'Specs 35', '2023-01-30', 'pc-1.png', 'AVAILABLE', 2),
('PC 36', 'pc-36', '10', 'Specs 36', '2023-01-31', 'pc-1.png', 'AVAILABLE', 3), ('PC 37', 'pc-37', '12', 'Specs 37', '2023-02-01', 'pc-1.png', 'AVAILABLE', 4), ('PC 38', 'pc-38', '8', 'Specs 38', '2023-02-02', 'pc-1.png', 'AVAILABLE', 5), ('PC 39', 'pc-39', '15', 'Specs 39', '2023-02-03', 'pc-1.png', 'AVAILABLE', 6), ('PC 40', 'pc-40', '20', 'Specs 40', '2023-02-04', 'pc-1.png', 'AVAILABLE', 7),
('PC 41', 'pc-41', '10', 'Specs 41', '2023-02-05', 'pc-1.png', 'AVAILABLE', 1), ('PC 42', 'pc-42', '12', 'Specs 42', '2023-02-06', 'pc-1.png', 'AVAILABLE', 2), ('PC 43', 'pc-43', '8', 'Specs 43', '2023-02-07', 'pc-1.png', 'AVAILABLE', 3), ('PC 44', 'pc-44', '15', 'Specs 44', '2023-02-08', 'pc-1.png', 'AVAILABLE', 4), ('PC 45', 'pc-45', '20', 'Specs 45', '2023-02-09', 'pc-1.png', 'AVAILABLE', 5),
('PC 46', 'pc-46', '10', 'Specs 46', '2023-02-10', 'pc-1.png', 'AVAILABLE', 6), ('PC 47', 'pc-47', '12', 'Specs 47', '2023-02-11', 'pc-1.png', 'AVAILABLE', 7), ('PC 48', 'pc-48', '8', 'Specs 48', '2023-02-12', 'pc-1.png', 'AVAILABLE', 1), ('PC 49', 'pc-49', '15', 'Specs 49', '2023-02-13', 'pc-1.png', 'AVAILABLE', 2), ('PC 50', 'pc-50', '20', 'Specs 50', '2023-02-14', 'pc-1.png', 'AVAILABLE', 3),
('PC 51', 'pc-51', '10', 'Specs 51', '2023-02-15', 'pc-1.png', 'AVAILABLE', 4), ('PC 52', 'pc-52', '12', 'Specs 52', '2023-02-16', 'pc-1.png', 'AVAILABLE', 5), ('PC 53', 'pc-53', '8', 'Specs 53', '2023-02-17', 'pc-1.png', 'AVAILABLE', 6), ('PC 54', 'pc-54', '15', 'Specs 54', '2023-02-18', 'pc-1.png', 'AVAILABLE', 7), ('PC 55', 'pc-55', '20', 'Specs 55', '2023-02-19', 'pc-1.png', 'AVAILABLE', 1);

-- ============================
-- 50 Additional User Orders
-- ============================
INSERT IGNORE INTO user_order (status, user_id, created_at) VALUES
('CONFIRMED', 11, '2025-05-01 10:00:00'), ('SHIPPED', 12, '2025-05-02 11:00:00'), ('DELIVERED', 13, '2025-05-03 12:00:00'), ('CONFIRMED', 14, '2025-05-04 13:00:00'), ('PENDING', 15, '2025-05-05 14:00:00'),
('CONFIRMED', 16, '2025-05-06 15:00:00'), ('SHIPPED', 17, '2025-05-07 16:00:00'), ('DELIVERED', 18, '2025-05-08 17:00:00'), ('CONFIRMED', 19, '2025-05-09 18:00:00'), ('PENDING', 20, '2025-05-10 19:00:00'),
('CONFIRMED', 21, '2025-05-11 20:00:00'), ('SHIPPED', 22, '2025-05-12 21:00:00'), ('DELIVERED', 23, '2025-05-13 22:00:00'), ('CONFIRMED', 24, '2025-05-14 23:00:00'), ('PENDING', 25, '2025-05-15 00:00:00'),
('CONFIRMED', 26, '2025-05-16 01:00:00'), ('SHIPPED', 27, '2025-05-17 02:00:00'), ('DELIVERED', 28, '2025-05-18 03:00:00'), ('CONFIRMED', 29, '2025-05-19 04:00:00'), ('PENDING', 30, '2025-05-20 05:00:00'),
('CONFIRMED', 31, '2025-05-21 06:00:00'), ('SHIPPED', 32, '2025-05-22 07:00:00'), ('DELIVERED', 33, '2025-05-23 08:00:00'), ('CONFIRMED', 34, '2025-05-24 09:00:00'), ('PENDING', 35, '2025-05-25 10:00:00'),
('CONFIRMED', 36, '2025-05-26 11:00:00'), ('SHIPPED', 37, '2025-05-27 12:00:00'), ('DELIVERED', 38, '2025-05-28 13:00:00'), ('CONFIRMED', 39, '2025-05-29 14:00:00'), ('PENDING', 40, '2025-05-30 15:00:00'),
('CONFIRMED', 41, '2025-05-31 16:00:00'), ('SHIPPED', 42, '2025-06-01 17:00:00'), ('DELIVERED', 43, '2025-06-02 18:00:00'), ('CONFIRMED', 44, '2025-06-03 19:00:00'), ('PENDING', 45, '2025-06-04 20:00:00'),
('CONFIRMED', 46, '2025-06-05 21:00:00'), ('SHIPPED', 47, '2025-06-06 22:00:00'), ('DELIVERED', 48, '2025-06-07 23:00:00'), ('CONFIRMED', 49, '2025-06-08 00:00:00'), ('PENDING', 50, '2025-06-09 01:00:00'),
('CONFIRMED', 51, '2025-06-10 02:00:00'), ('SHIPPED', 52, '2025-06-11 03:00:00'), ('DELIVERED', 53, '2025-06-12 04:00:00'), ('CONFIRMED', 54, '2025-06-13 05:00:00'), ('PENDING', 55, '2025-06-14 06:00:00'),
('CONFIRMED', 56, '2025-06-15 07:00:00'), ('SHIPPED', 57, '2025-06-16 08:00:00'), ('DELIVERED', 58, '2025-06-17 09:00:00'), ('CONFIRMED', 59, '2025-06-18 10:00:00'), ('PENDING', 60, '2025-06-19 11:00:00');

-- Más order_items para los nuevos pedidos
INSERT IGNORE INTO order_item (quantity, product_id, user_order_id) VALUES
(1, 1, 3), (2, 2, 3), (1, 3, 4), (3, 4, 4), (2, 5, 5), (1, 1, 6), (4, 2, 6), (1, 3, 7), (2, 4, 7), (3, 5, 8),
(1, 1, 9), (2, 2, 9), (1, 3, 10), (3, 4, 3), (2, 5, 4), (1, 1, 5), (4, 2, 6), (1, 3, 7), (2, 4, 8), (3, 5, 9),
(1, 1, 10), (2, 2, 3), (1, 3, 4), (3, 4, 5), (2, 5, 6), (1, 1, 7), (4, 2, 8), (1, 3, 9), (2, 4, 10), (3, 5, 3);


-- ============================
-- 50 Additional Order Items
-- ============================
INSERT IGNORE INTO order_item (quantity, product_id, user_order_id) VALUES
(1, 6, 11), (2, 7, 11), (1, 8, 12), (3, 9, 12), (2, 10, 13), (1, 11, 14), (4, 12, 15), (1, 13, 16), (2, 14, 17), (3, 15, 18),
(1, 16, 19), (2, 17, 20), (1, 18, 21), (3, 19, 22), (2, 20, 23), (1, 21, 24), (4, 22, 25), (1, 23, 26), (2, 24, 27), (3, 25, 28),
(1, 26, 29), (2, 27, 30), (1, 28, 31), (3, 29, 32), (2, 30, 33), (1, 31, 34), (4, 32, 35), (1, 33, 36), (2, 34, 37), (3, 35, 38),
(1, 36, 39), (2, 37, 40), (1, 38, 41), (3, 39, 42), (2, 40, 43), (1, 41, 44), (4, 42, 45), (1, 43, 46), (2, 44, 47), (3, 45, 48),
(1, 46, 49), (2, 47, 50), (1, 48, 51), (3, 49, 52), (2, 50, 53), (1, 51, 54), (4, 52, 55), (1, 53, 56), (2, 54, 57), (3, 55, 58);

-- ============================
-- 50 Additional Bookings
-- ============================
INSERT IGNORE INTO booking (hours, pc_id, user_id) VALUES
(2, 6, 11), (4, 7, 12), (3, 8, 13), (5, 9, 14), (2, 10, 15), (4, 11, 16), (3, 12, 17), (5, 13, 18), (2, 14, 19), (4, 15, 20),
(3, 16, 21), (5, 17, 22), (2, 18, 23), (4, 19, 24), (3, 20, 25), (5, 21, 26), (2, 22, 27), (4, 23, 28), (3, 24, 29), (5, 25, 30),
(2, 26, 31), (4, 27, 32), (3, 28, 33), (5, 29, 34), (2, 30, 35), (4, 31, 36), (3, 32, 37), (5, 33, 38), (2, 34, 39), (4, 35, 40),
(3, 36, 41), (5, 37, 42), (2, 38, 43), (4, 39, 44), (3, 40, 45), (5, 41, 46), (2, 42, 47), (4, 43, 48), (3, 44, 49), (5, 45, 50),
(2, 46, 51), (4, 47, 52), (3, 48, 53), (5, 49, 54), (2, 50, 55), (4, 51, 56), (3, 52, 57), (5, 53, 58), (2, 54, 59), (4, 55, 60);

-- ============================
-- 50 Additional Reports
-- ============================
INSERT IGNORE INTO report (priority, `subject`, description, pc_id, user_id, created_at, `status`) VALUES
(1, 'Issue 6', 'Detail 6', 6, 11, '2025-07-01 10:00:00', 'PENDING'), (2, 'Issue 7', 'Detail 7', 7, 12, '2025-07-02 11:00:00', 'IN_PROGRESS'), (3, 'Issue 8', 'Detail 8', 8, 13, '2025-07-03 12:00:00', 'RESOLVED'), (1, 'Issue 9', 'Detail 9', 9, 14, '2025-07-04 13:00:00', 'PENDING'), (2, 'Issue 10', 'Detail 10', 10, 15, '2025-07-05 14:00:00', 'IN_PROGRESS'),
(3, 'Issue 11', 'Detail 11', 11, 16, '2025-07-06 15:00:00', 'RESOLVED'), (1, 'Issue 12', 'Detail 12', 12, 17, '2025-07-07 16:00:00', 'PENDING'), (2, 'Issue 13', 'Detail 13', 13, 18, '2025-07-08 17:00:00', 'IN_PROGRESS'), (3, 'Issue 14', 'Detail 14', 14, 19, '2025-07-09 18:00:00', 'RESOLVED'), (1, 'Issue 15', 'Detail 15', 15, 20, '2025-07-10 19:00:00', 'PENDING'),
(2, 'Issue 16', 'Detail 16', 16, 21, '2025-07-11 20:00:00', 'IN_PROGRESS'), (3, 'Issue 17', 'Detail 17', 17, 22, '2025-07-12 21:00:00', 'RESOLVED'), (1, 'Issue 18', 'Detail 18', 18, 23, '2025-07-13 22:00:00', 'PENDING'), (2, 'Issue 19', 'Detail 19', 19, 24, '2025-07-14 23:00:00', 'IN_PROGRESS'), (3, 'Issue 20', 'Detail 20', 20, 25, '2025-07-15 00:00:00', 'RESOLVED'),
(1, 'Issue 21', 'Detail 21', 21, 26, '2025-07-16 01:00:00', 'PENDING'), (2, 'Issue 22', 'Detail 22', 22, 27, '2025-07-17 02:00:00', 'IN_PROGRESS'), (3, 'Issue 23', 'Detail 23', 23, 28, '2025-07-18 03:00:00', 'RESOLVED'), (1, 'Issue 24', 'Detail 24', 24, 29, '2025-07-19 04:00:00', 'PENDING'), (2, 'Issue 25', 'Detail 25', 25, 30, '2025-07-20 05:00:00', 'IN_PROGRESS'),
(3, 'Issue 26', 'Detail 26', 26, 31, '2025-07-21 06:00:00', 'RESOLVED'), (1, 'Issue 27', 'Detail 27', 27, 32, '2025-07-22 07:00:00', 'PENDING'), (2, 'Issue 28', 'Detail 28', 28, 33, '2025-07-23 08:00:00', 'IN_PROGRESS'), (3, 'Issue 29', 'Detail 29', 29, 34, '2025-07-24 09:00:00', 'RESOLVED'), (1, 'Issue 30', 'Detail 30', 30, 35, '2025-07-25 10:00:00', 'PENDING'),
(2, 'Issue 31', 'Detail 31', 31, 36, '2025-07-26 11:00:00', 'IN_PROGRESS'), (3, 'Issue 32', 'Detail 32', 32, 37, '2025-07-27 12:00:00', 'RESOLVED'), (1, 'Issue 33', 'Detail 33', 33, 38, '2025-07-28 13:00:00', 'PENDING'), (2, 'Issue 34', 'Detail 34', 34, 39, '2025-07-29 14:00:00', 'IN_PROGRESS'), (3, 'Issue 35', 'Detail 35', 35, 40, '2025-07-30 15:00:00', 'RESOLVED'),
(1, 'Issue 36', 'Detail 36', 36, 41, '2025-07-31 16:00:00', 'PENDING'), (2, 'Issue 37', 'Detail 37', 37, 42, '2025-08-01 17:00:00', 'IN_PROGRESS'), (3, 'Issue 38', 'Detail 38', 38, 43, '2025-08-02 18:00:00', 'RESOLVED'), (1, 'Issue 39', 'Detail 39', 39, 44, '2025-08-03 19:00:00', 'PENDING'), (2, 'Issue 40', 'Detail 40', 40, 45, '2025-08-04 20:00:00', 'IN_PROGRESS'),
(3, 'Issue 41', 'Detail 41', 41, 46, '2025-08-05 21:00:00', 'RESOLVED'), (1, 'Issue 42', 'Detail 42', 42, 47, '2025-08-06 22:00:00', 'PENDING'), (2, 'Issue 43', 'Detail 43', 43, 48, '2025-08-07 23:00:00', 'IN_PROGRESS'), (3, 'Issue 44', 'Detail 44', 44, 49, '2025-08-08 00:00:00', 'RESOLVED'), (1, 'Issue 45', 'Detail 45', 45, 50, '2025-08-09 01:00:00', 'PENDING'),
(2, 'Issue 46', 'Detail 46', 46, 51, '2025-08-10 02:00:00', 'IN_PROGRESS'), (3, 'Issue 47', 'Detail 47', 47, 52, '2025-08-11 03:00:00', 'RESOLVED'), (1, 'Issue 48', 'Detail 48', 48, 53, '2025-08-12 04:00:00', 'PENDING'), (2, 'Issue 49', 'Detail 49', 49, 54, '2025-08-13 05:00:00', 'IN_PROGRESS'), (3, 'Issue 50', 'Detail 50', 50, 55, '2025-08-14 06:00:00', 'RESOLVED'),
(1, 'Issue 51', 'Detail 51', 51, 56, '2025-08-15 07:00:00', 'PENDING'), (2, 'Issue 52', 'Detail 52', 52, 57, '2025-08-16 08:00:00', 'IN_PROGRESS'), (3, 'Issue 53', 'Detail 53', 53, 58, '2025-08-17 09:00:00', 'RESOLVED'), (1, 'Issue 54', 'Detail 54', 54, 59, '2025-08-18 10:00:00', 'PENDING'), (2, 'Issue 55', 'Detail 55', 55, 60, '2025-08-19 11:00:00', 'IN_PROGRESS');

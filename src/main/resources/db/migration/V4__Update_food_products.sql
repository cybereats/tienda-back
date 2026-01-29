-- ============================
-- Actualizar categorías de comida
-- ============================
UPDATE category_product SET label = 'Hamburguesas', slug = 'hamburguesas' WHERE id = 1;
UPDATE category_product SET label = 'Pizzas', slug = 'pizzas' WHERE id = 2;
UPDATE category_product SET label = 'Bebidas', slug = 'bebidas' WHERE id = 3;
UPDATE category_product SET label = 'Postres', slug = 'postres' WHERE id = 4;
UPDATE category_product SET label = 'Combos', slug = 'combos' WHERE id = 5;

-- Añadir más categorías
INSERT INTO category_product (label, slug) VALUES
    ('Entrantes', 'entrantes'),
    ('Smoothies', 'smoothies'),
    ('Hot Dogs', 'hot-dogs'),
    ('Ensaladas', 'ensaladas'),
    ('Wraps', 'wraps');

-- ============================
-- Limpiar tablas relacionadas primero
-- ============================
DELETE FROM cart_item;
DELETE FROM order_item;
DELETE FROM product;

-- ============================
-- HAMBURGUESAS (category_id = 1)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Cyber Burger', 'cyber-burger', 'Nuestra hamburguesa clásica con carne de 200g, queso cheddar, lechuga, tomate y salsa especial.', 8.99, 'burger-1.png', 1),
('Gamer Deluxe', 'gamer-deluxe', 'Doble carne de 150g, bacon crujiente, queso americano, cebolla caramelizada y salsa BBQ.', 11.99, 'burger-1.png', 1),
('Pixel Bacon', 'pixel-bacon', 'Carne de 180g con doble bacon, queso suizo, jalapeños y mayonesa picante.', 10.49, 'burger-1.png', 1),
('Veggie Quest', 'veggie-quest', 'Hamburguesa vegetariana con beyond meat, aguacate, rúcula y pesto casero.', 9.99, 'burger-1.png', 1),
('Boss Battle', 'boss-battle', 'Triple carne de 150g, triple queso, bacon, huevo frito y salsa especial. ¡Para campeones!', 14.99, 'burger-1.png', 1),
('Crispy Chicken', 'crispy-chicken', 'Pechuga de pollo empanizada, lechuga iceberg, tomate y mayonesa de ajo.', 8.49, 'burger-1.png', 1),
('Smash Burger', 'smash-burger', 'Dos carnes smash de 90g, queso americano derretido, pepinillos y salsa mil islas.', 9.49, 'burger-1.png', 1),
('Mushroom Level', 'mushroom-level', 'Carne de 200g con champiñones salteados, queso brie y cebolla crujiente.', 10.99, 'burger-1.png', 1),
('Spicy Fire', 'spicy-fire', 'Carne de 180g con habanero, queso pepper jack, jalapeños y salsa sriracha. ¡Extremo!', 10.49, 'burger-1.png', 1),
('Classic Cheese', 'classic-cheese', 'Simple pero perfecta: carne de 150g, queso cheddar, ketchup y mostaza.', 6.99, 'burger-1.png', 1);

-- ============================
-- PIZZAS (category_id = 2)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Pepperoni Arcade', 'pepperoni-arcade', 'Pizza clásica con pepperoni premium y extra queso mozzarella.', 10.99, 'pizza-1.png', 2),
('4 Quesos Gaming', '4-quesos-gaming', 'Mozzarella, gorgonzola, parmesano y queso de cabra sobre base de tomate.', 11.99, 'pizza-1.png', 2),
('Hawaiana Pixel', 'hawaiana-pixel', 'Jamón york, piña natural, bacon y mozzarella. La combinación ganadora.', 10.49, 'pizza-1.png', 2),
('BBQ Chicken', 'bbq-chicken', 'Pollo marinado en BBQ, bacon, cebolla morada y cilantro fresco.', 12.49, 'pizza-1.png', 2),
('Vegetariana Pro', 'vegetariana-pro', 'Pimientos, champiñones, cebolla, aceitunas, tomate cherry y albahaca.', 9.99, 'pizza-1.png', 2),
('Meat Lovers XL', 'meat-lovers-xl', 'Pepperoni, salchicha italiana, bacon, jamón y carne picada. Para carnívoros.', 13.99, 'pizza-1.png', 2),
('Margherita Classic', 'margherita-classic', 'Tomate San Marzano, mozzarella fresca, albahaca y aceite de oliva virgen.', 8.99, 'pizza-1.png', 2),
('Carbonara Style', 'carbonara-style', 'Base de crema, bacon crujiente, huevo, parmesano y pimienta negra.', 11.49, 'pizza-1.png', 2);

-- ============================
-- BEBIDAS (category_id = 3)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Coca-Cola', 'coca-cola', 'Coca-Cola original bien fría. 500ml.', 2.49, 'drink-1.png', 3),
('Coca-Cola Zero', 'coca-cola-zero', 'Todo el sabor sin azúcar. 500ml.', 2.49, 'drink-1.png', 3),
('Fanta Naranja', 'fanta-naranja', 'Refrescante Fanta de naranja. 500ml.', 2.49, 'drink-1.png', 3),
('Sprite', 'sprite', 'Lima-limón burbujeante. 500ml.', 2.49, 'drink-1.png', 3),
('Agua Mineral', 'agua-mineral', 'Agua mineral natural. 500ml.', 1.99, 'drink-1.png', 3),
('Nestea Limón', 'nestea-limon', 'Té frío con sabor a limón. 500ml.', 2.79, 'drink-1.png', 3),
('Red Bull', 'red-bull', 'Bebida energética para las sesiones largas. 250ml.', 3.49, 'drink-1.png', 3),
('Monster Energy', 'monster-energy', 'Energía extrema para gamers. 500ml.', 3.29, 'drink-1.png', 3),
('Cerveza Mahou', 'cerveza-mahou', 'Cerveza Mahou 5 estrellas. 330ml.', 2.99, 'drink-1.png', 3),
('Cerveza Estrella', 'cerveza-estrella', 'Estrella Galicia especial. 330ml.', 2.99, 'drink-1.png', 3),
('Café Americano', 'cafe-americano', 'Café recién hecho, intenso y aromático.', 1.99, 'drink-1.png', 3),
('Café Latte', 'cafe-latte', 'Espresso con leche cremosa.', 2.49, 'drink-1.png', 3),
('Capuccino', 'capuccino', 'Espresso, leche espumada y cacao.', 2.79, 'drink-1.png', 3),
('Zumo de Naranja', 'zumo-naranja', 'Zumo de naranja natural recién exprimido.', 3.49, 'drink-1.png', 3);

-- ============================
-- POSTRES (category_id = 4)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Brownie Gamer', 'brownie-gamer', 'Brownie de chocolate con nueces, servido caliente con helado de vainilla.', 4.99, 'dessert-1.png', 4),
('Cheesecake NY', 'cheesecake-ny', 'Tarta de queso estilo New York con coulis de frutos rojos.', 5.49, 'dessert-1.png', 4),
('Helado Artesano', 'helado-artesano', '3 bolas de helado artesano. Elige tus sabores favoritos.', 4.49, 'dessert-1.png', 4),
('Tarta de Chocolate', 'tarta-chocolate', 'Tarta death by chocolate con ganache y virutas de chocolate.', 5.99, 'dessert-1.png', 4),
('Churros con Chocolate', 'churros-chocolate', '6 churros crujientes con chocolate caliente para mojar.', 4.99, 'dessert-1.png', 4),
('Cookies & Cream', 'cookies-cream', 'Galletas caseras con helado de cookies and cream y sirope de chocolate.', 5.49, 'dessert-1.png', 4),
('Apple Pie', 'apple-pie', 'Tarta de manzana casera con canela y helado de vainilla.', 4.99, 'dessert-1.png', 4),
('Coulant de Chocolate', 'coulant-chocolate', 'Bizcocho de chocolate con corazón fundido. Irresistible.', 5.99, 'dessert-1.png', 4),
('Tiramisú', 'tiramisu', 'Clásico italiano con mascarpone, café y cacao.', 5.49, 'dessert-1.png', 4),
('Donuts Variados', 'donuts-variados', '3 donuts glaseados con diferentes toppings.', 3.99, 'dessert-1.png', 4);

-- ============================
-- COMBOS (category_id = 5)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Combo Gamer', 'combo-gamer', 'Cyber Burger + Patatas Fritas + Bebida mediana. El clásico.', 11.99, 'combo-1.png', 5),
('Combo Deluxe', 'combo-deluxe', 'Gamer Deluxe + Aros de Cebolla + Bebida grande + Brownie.', 16.99, 'combo-1.png', 5),
('Combo Duo', 'combo-duo', '2 Smash Burgers + 2 Patatas + 2 Bebidas. Perfecto para compartir.', 19.99, 'combo-1.png', 5),
('Combo Pizza Party', 'combo-pizza-party', 'Pizza mediana + 4 Alitas + Patatas Bravas + 2 Bebidas.', 22.99, 'combo-1.png', 5),
('Combo Kids', 'combo-kids', 'Mini Burger + Patatas + Nuggets + Zumo + Helado pequeño.', 8.99, 'combo-1.png', 5),
('Combo Streaming', 'combo-streaming', 'Boss Battle + Nachos Supreme + Monster Energy + Brownie.', 21.99, 'combo-1.png', 5),
('Combo Fit', 'combo-fit', 'Veggie Quest + Ensalada César + Agua + Fruta fresca.', 14.99, 'combo-1.png', 5),
('Combo Noche', 'combo-noche', '2 Pizzas medianas + Alitas BBQ + Nachos + 4 Cervezas.', 34.99, 'combo-1.png', 5);

-- ============================
-- ENTRANTES (category_id = 6)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Patatas Fritas', 'patatas-fritas', 'Patatas fritas crujientes con sal. Ración generosa.', 3.49, 'sides-1.png', 6),
('Patatas Deluxe', 'patatas-deluxe', 'Patatas gajo especiadas con salsa sour cream.', 4.49, 'sides-1.png', 6),
('Aros de Cebolla', 'aros-cebolla', 'Aros de cebolla rebozados con salsa ranch.', 4.99, 'sides-1.png', 6),
('Nuggets de Pollo', 'nuggets-pollo', '8 nuggets de pollo crujientes con salsa BBQ.', 5.49, 'sides-1.png', 6),
('Alitas BBQ', 'alitas-bbq', '8 alitas de pollo bañadas en salsa BBQ casera.', 7.99, 'sides-1.png', 6),
('Alitas Buffalo', 'alitas-buffalo', '8 alitas picantes estilo Buffalo con salsa blue cheese.', 7.99, 'sides-1.png', 6),
('Nachos Supreme', 'nachos-supreme', 'Nachos con queso fundido, jalapeños, guacamole, pico de gallo y sour cream.', 8.99, 'sides-1.png', 6),
('Mozzarella Sticks', 'mozzarella-sticks', '6 palitos de mozzarella rebozados con salsa marinara.', 5.99, 'sides-1.png', 6),
('Patatas Bravas', 'patatas-bravas', 'Patatas con salsa brava picante y alioli casero.', 4.99, 'sides-1.png', 6),
('Tequeños', 'tequenos', '6 tequeños de queso con guasacaca.', 5.49, 'sides-1.png', 6),
('Jalapeño Poppers', 'jalapeno-poppers', '6 jalapeños rellenos de queso crema, rebozados y fritos.', 5.99, 'sides-1.png', 6),
('Fingers de Pollo', 'fingers-pollo', '5 fingers de pollo crujientes con salsa honey mustard.', 6.49, 'sides-1.png', 6);

-- ============================
-- SMOOTHIES (category_id = 7)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Smoothie Tropical', 'smoothie-tropical', 'Mango, piña, maracuyá y zumo de naranja. Vitamina pura.', 4.99, 'smoothie-1.png', 7),
('Smoothie Berry Blast', 'smoothie-berry-blast', 'Fresas, arándanos, frambuesas y yogur griego.', 4.99, 'smoothie-1.png', 7),
('Smoothie Green Power', 'smoothie-green-power', 'Espinacas, plátano, manzana verde y jengibre. Energía natural.', 5.49, 'smoothie-1.png', 7),
('Smoothie Protein', 'smoothie-protein', 'Plátano, mantequilla de cacahuete, avena y proteína de suero.', 5.99, 'smoothie-1.png', 7),
('Smoothie Chocolate', 'smoothie-chocolate', 'Plátano, cacao puro, leche de almendras y dátiles.', 5.49, 'smoothie-1.png', 7),
('Milkshake Vainilla', 'milkshake-vainilla', 'Batido cremoso de vainilla con nata montada.', 4.49, 'smoothie-1.png', 7),
('Milkshake Chocolate', 'milkshake-chocolate', 'Batido de chocolate belga con sirope y nata.', 4.49, 'smoothie-1.png', 7),
('Milkshake Fresa', 'milkshake-fresa', 'Batido de fresas naturales con helado de fresa.', 4.49, 'smoothie-1.png', 7),
('Milkshake Oreo', 'milkshake-oreo', 'Batido con galletas Oreo trituradas, helado y nata.', 5.49, 'smoothie-1.png', 7),
('Frappe Café', 'frappe-cafe', 'Café helado con leche, hielo y sirope de caramelo.', 4.29, 'smoothie-1.png', 7);

-- ============================
-- HOT DOGS (category_id = 8)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Hot Dog Clásico', 'hot-dog-clasico', 'Salchicha Frankfurt, ketchup, mostaza y cebolla crujiente.', 4.99, 'hotdog-1.png', 8),
('Hot Dog NYC', 'hot-dog-nyc', 'Salchicha premium con chucrut, mostaza Dijon y pepinillos.', 5.99, 'hotdog-1.png', 8),
('Hot Dog Bacon', 'hot-dog-bacon', 'Salchicha envuelta en bacon, queso cheddar y salsa BBQ.', 6.49, 'hotdog-1.png', 8),
('Hot Dog Chili', 'hot-dog-chili', 'Salchicha con chili con carne, queso fundido y jalapeños.', 6.99, 'hotdog-1.png', 8),
('Hot Dog Mexicano', 'hot-dog-mexicano', 'Salchicha con guacamole, pico de gallo, crema y nachos.', 6.49, 'hotdog-1.png', 8),
('Corn Dog', 'corn-dog', 'Salchicha rebozada en masa de maíz, frita y dorada.', 4.49, 'hotdog-1.png', 8);

-- ============================
-- ENSALADAS (category_id = 9)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Ensalada César', 'ensalada-cesar', 'Lechuga romana, pollo a la plancha, parmesano, croutons y salsa César.', 8.99, 'salad-1.png', 9),
('Ensalada Griega', 'ensalada-griega', 'Tomate, pepino, cebolla morada, aceitunas Kalamata, queso feta y orégano.', 7.99, 'salad-1.png', 9),
('Ensalada Caprese', 'ensalada-caprese', 'Tomate de temporada, mozzarella fresca, albahaca y reducción de balsámico.', 7.49, 'salad-1.png', 9),
('Bowl Poke', 'bowl-poke', 'Arroz, salmón marinado, aguacate, edamame, mango y salsa ponzu.', 11.99, 'salad-1.png', 9),
('Buddha Bowl', 'buddha-bowl', 'Quinoa, garbanzos, boniato asado, aguacate, hummus y tahini.', 10.99, 'salad-1.png', 9);

-- ============================
-- WRAPS (category_id = 10)
-- ============================
INSERT INTO product (label, slug, description, price, image, category_product_id) VALUES
('Wrap de Pollo', 'wrap-pollo', 'Tortilla con pollo a la plancha, lechuga, tomate, queso y salsa ranch.', 7.49, 'wrap-1.png', 10),
('Wrap Mexicano', 'wrap-mexicano', 'Tortilla con carne especiada, frijoles, queso, guacamole y crema agria.', 7.99, 'wrap-1.png', 10),
('Wrap Falafel', 'wrap-falafel', 'Tortilla con falafel casero, hummus, ensalada, pepinillos y tahini.', 7.49, 'wrap-1.png', 10),
('Wrap César', 'wrap-cesar', 'Tortilla con pollo crispy, lechuga romana, parmesano y salsa César.', 7.99, 'wrap-1.png', 10),
('Burrito Supreme', 'burrito-supreme', 'Tortilla grande con arroz, carne, frijoles, queso, pico de gallo y guacamole.', 9.99, 'wrap-1.png', 10);

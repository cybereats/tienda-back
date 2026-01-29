-- ============================
-- Actualizar imágenes usando SOLO las que existen en assets/food
-- ============================

-- HAMBURGUESAS (10 productos, 10 imágenes disponibles)
UPDATE product SET image = 'food/cyber_burger.png' WHERE slug = 'cyber-burger';
UPDATE product SET image = 'food/gamer_deluxe.png' WHERE slug = 'gamer-deluxe';
UPDATE product SET image = 'food/pixel_bacon.png' WHERE slug = 'pixel-bacon';
UPDATE product SET image = 'food/veggie_quest.png' WHERE slug = 'veggie-quest';
UPDATE product SET image = 'food/boss_battle.png' WHERE slug = 'boss-battle';
UPDATE product SET image = 'food/crispy_chicken.png' WHERE slug = 'crispy-chicken';
UPDATE product SET image = 'food/smash_burger.png' WHERE slug = 'smash-burger';
UPDATE product SET image = 'food/mushroom_level.png' WHERE slug = 'mushroom-level';
UPDATE product SET image = 'food/spicy_fire.png' WHERE slug = 'spicy-fire';
UPDATE product SET image = 'food/classic_cheese.png' WHERE slug = 'classic-cheese';

-- PIZZAS (8 productos, 8 imágenes disponibles)
UPDATE product SET image = 'food/pepperoni_arcade.png' WHERE slug = 'pepperoni-arcade';
UPDATE product SET image = 'food/4_quesos_gaming.png' WHERE slug = '4-quesos-gaming';
UPDATE product SET image = 'food/hawaiana_pixel.png' WHERE slug = 'hawaiana-pixel';
UPDATE product SET image = 'food/BBQ_chicken.png' WHERE slug = 'bbq-chicken';
UPDATE product SET image = 'food/vegetariana_pro.png' WHERE slug = 'vegetariana-pro';
UPDATE product SET image = 'food/meat_lovers_XL.png' WHERE slug = 'meat-lovers-xl';
UPDATE product SET image = 'food/margherita_classic.png' WHERE slug = 'margherita-classic';
UPDATE product SET image = 'food/carbonara_style.png' WHERE slug = 'carbonara-style';

-- BEBIDAS (3 imágenes disponibles: coca-cola_original, coca-cola_zero, fanta)
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'coca-cola';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'coca-cola-zero';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'fanta-naranja';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'sprite';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'agua-mineral';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'nestea-limon';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'red-bull';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'monster-energy';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'cerveza-mahou';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'cerveza-estrella';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'cafe-americano';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'cafe-latte';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'capuccino';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'zumo-naranja';

-- POSTRES (reutilizamos imágenes de hamburguesas/pizzas)
UPDATE product SET image = 'food/cyber_burger.png' WHERE slug = 'brownie-gamer';
UPDATE product SET image = 'food/gamer_deluxe.png' WHERE slug = 'cheesecake-ny';
UPDATE product SET image = 'food/pixel_bacon.png' WHERE slug = 'helado-artesano';
UPDATE product SET image = 'food/veggie_quest.png' WHERE slug = 'tarta-chocolate';
UPDATE product SET image = 'food/boss_battle.png' WHERE slug = 'churros-chocolate';
UPDATE product SET image = 'food/crispy_chicken.png' WHERE slug = 'cookies-cream';
UPDATE product SET image = 'food/smash_burger.png' WHERE slug = 'apple-pie';
UPDATE product SET image = 'food/mushroom_level.png' WHERE slug = 'coulant-chocolate';
UPDATE product SET image = 'food/spicy_fire.png' WHERE slug = 'tiramisu';
UPDATE product SET image = 'food/classic_cheese.png' WHERE slug = 'donuts-variados';

-- COMBOS (reutilizamos imágenes)
UPDATE product SET image = 'food/cyber_burger.png' WHERE slug = 'combo-gamer';
UPDATE product SET image = 'food/gamer_deluxe.png' WHERE slug = 'combo-deluxe';
UPDATE product SET image = 'food/boss_battle.png' WHERE slug = 'combo-duo';
UPDATE product SET image = 'food/pepperoni_arcade.png' WHERE slug = 'combo-pizza-party';
UPDATE product SET image = 'food/smash_burger.png' WHERE slug = 'combo-kids';
UPDATE product SET image = 'food/meat_lovers_XL.png' WHERE slug = 'combo-streaming';
UPDATE product SET image = 'food/veggie_quest.png' WHERE slug = 'combo-fit';
UPDATE product SET image = 'food/BBQ_chicken.png' WHERE slug = 'combo-noche';

-- ENTRANTES (reutilizamos imágenes)
UPDATE product SET image = 'food/classic_cheese.png' WHERE slug = 'patatas-fritas';
UPDATE product SET image = 'food/pixel_bacon.png' WHERE slug = 'patatas-deluxe';
UPDATE product SET image = 'food/crispy_chicken.png' WHERE slug = 'aros-cebolla';
UPDATE product SET image = 'food/smash_burger.png' WHERE slug = 'nuggets-pollo';
UPDATE product SET image = 'food/spicy_fire.png' WHERE slug = 'alitas-bbq';
UPDATE product SET image = 'food/mushroom_level.png' WHERE slug = 'alitas-buffalo';
UPDATE product SET image = 'food/4_quesos_gaming.png' WHERE slug = 'nachos-supreme';
UPDATE product SET image = 'food/carbonara_style.png' WHERE slug = 'mozzarella-sticks';
UPDATE product SET image = 'food/hawaiana_pixel.png' WHERE slug = 'patatas-bravas';
UPDATE product SET image = 'food/margherita_classic.png' WHERE slug = 'tequenos';
UPDATE product SET image = 'food/vegetariana_pro.png' WHERE slug = 'jalapeno-poppers';
UPDATE product SET image = 'food/pepperoni_arcade.png' WHERE slug = 'fingers-pollo';

-- SMOOTHIES (reutilizamos imágenes de bebidas)
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'smoothie-tropical';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'smoothie-berry-blast';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'smoothie-green-power';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'smoothie-protein';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'smoothie-chocolate';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'milkshake-vainilla';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'milkshake-chocolate';
UPDATE product SET image = 'food/coca-cola_original.png' WHERE slug = 'milkshake-fresa';
UPDATE product SET image = 'food/coca-cola_zero.png' WHERE slug = 'milkshake-oreo';
UPDATE product SET image = 'food/fanta.png' WHERE slug = 'frappe-cafe';

-- HOT DOGS (reutilizamos imágenes de hamburguesas)
UPDATE product SET image = 'food/cyber_burger.png' WHERE slug = 'hot-dog-clasico';
UPDATE product SET image = 'food/gamer_deluxe.png' WHERE slug = 'hot-dog-nyc';
UPDATE product SET image = 'food/pixel_bacon.png' WHERE slug = 'hot-dog-bacon';
UPDATE product SET image = 'food/boss_battle.png' WHERE slug = 'hot-dog-chili';
UPDATE product SET image = 'food/spicy_fire.png' WHERE slug = 'hot-dog-mexicano';
UPDATE product SET image = 'food/crispy_chicken.png' WHERE slug = 'corn-dog';

-- ENSALADAS (reutilizamos imágenes)
UPDATE product SET image = 'food/veggie_quest.png' WHERE slug = 'ensalada-cesar';
UPDATE product SET image = 'food/vegetariana_pro.png' WHERE slug = 'ensalada-griega';
UPDATE product SET image = 'food/margherita_classic.png' WHERE slug = 'ensalada-caprese';
UPDATE product SET image = 'food/hawaiana_pixel.png' WHERE slug = 'bowl-poke';
UPDATE product SET image = 'food/4_quesos_gaming.png' WHERE slug = 'buddha-bowl';

-- WRAPS (reutilizamos imágenes)
UPDATE product SET image = 'food/crispy_chicken.png' WHERE slug = 'wrap-pollo';
UPDATE product SET image = 'food/spicy_fire.png' WHERE slug = 'wrap-mexicano';
UPDATE product SET image = 'food/veggie_quest.png' WHERE slug = 'wrap-falafel';
UPDATE product SET image = 'food/smash_burger.png' WHERE slug = 'wrap-cesar';
UPDATE product SET image = 'food/boss_battle.png' WHERE slug = 'burrito-supreme';

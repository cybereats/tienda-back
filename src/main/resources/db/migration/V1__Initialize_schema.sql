-- ============================
-- SCHEMA: category_product
-- ============================
CREATE TABLE category_product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(255),
    slug VARCHAR(255)
);

-- ============================
-- SCHEMA: product
-- ============================
CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(255),
    slug VARCHAR(255),
    description TEXT,
    price DECIMAL(10,2),
    image VARCHAR(255),
    category_product_id INT,
    FOREIGN KEY (category_product_id) REFERENCES category_product(id)
);

-- ============================
-- SCHEMA: users
-- ============================
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255),
    born_date DATE,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

-- ============================
-- SCHEMA: user_order
-- ============================
CREATE TABLE user_order (
    id INT PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(255),
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    delivery_type VARCHAR(20) DEFAULT 'TABLE',
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ============================
-- SCHEMA: order_item
-- ============================
CREATE TABLE order_item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantity INT,
    product_id INT,
    user_order_id INT,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (user_order_id) REFERENCES user_order(id)
);

-- ============================
-- SCHEMA: log
-- ============================
CREATE TABLE log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    info TEXT,
    timestamp DATETIME
);

-- ============================
-- SCHEMA: category_pc
-- ============================
CREATE TABLE category_pc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(255),
    slug VARCHAR(255) UNIQUE,
    price DECIMAL(10,2)
);

-- ============================
-- SCHEMA: pc
-- ============================
CREATE TABLE pc (
    id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(255),
    slug VARCHAR(255),
    runtime VARCHAR(255),
    specs TEXT,
    status VARCHAR(50) DEFAULT 'DISPONIBLE',
    working_since DATE,
    image VARCHAR(255),
    category_pc_id INT,
    FOREIGN KEY (category_pc_id) REFERENCES category_pc(id)
);

-- ============================
-- SCHEMA: booking
-- ============================
CREATE TABLE booking (
    id INT PRIMARY KEY AUTO_INCREMENT,
    hours INT,
    pc_id INT,
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pc_id) REFERENCES pc(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ============================
-- SCHEMA: report
-- ============================
CREATE TABLE report (
    id INT PRIMARY KEY AUTO_INCREMENT,
    priority INT,
    description TEXT,
    subject VARCHAR(255),
    status VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pc_id INT,
    user_id INT,
    FOREIGN KEY (pc_id) REFERENCES pc(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- ============================
-- SCHEMA: cart
-- ============================
CREATE TABLE cart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ============================
-- SCHEMA: cart_item
-- ============================
CREATE TABLE cart_item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    UNIQUE KEY unique_cart_product (cart_id, product_id)
);

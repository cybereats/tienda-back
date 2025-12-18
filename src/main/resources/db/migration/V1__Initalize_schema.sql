CREATE TABLE category_product (
                                  id INT PRIMARY KEY AUTO_INCREMENT,
                                  label VARCHAR(255),
                                  slug VARCHAR(255)
);

CREATE TABLE product (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         label VARCHAR(255),
                         slug VARCHAR(255),
                         `desc` TEXT,
                         price DECIMAL(10,2),
                         category_product_id INT,
                         FOREIGN KEY (category_product_id) REFERENCES category_product(id)
);

CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(255),
                      surname VARCHAR(255),
                      email VARCHAR(255),
                      born_date DATE,
                      username VARCHAR(255),
                      password VARCHAR(255),
                      role VARCHAR(255)
);
CREATE TABLE user_order (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            status VARCHAR(255),
                            user_id INT UNIQUE,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE order_item (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            quantity INT,
                            product_id INT,
                            user_order_id INT,
                            FOREIGN KEY (product_id) REFERENCES product(id),
                            FOREIGN KEY (user_order_id) REFERENCES user_order(id)
);

CREATE TABLE log (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     info TEXT,
                     timestamp DATETIME
);

CREATE TABLE category_pc (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             label VARCHAR(255),
                             slug VARCHAR(255) UNIQUE,
                             price DECIMAL(10,2)
);

CREATE TABLE pc (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    label VARCHAR(255),
                    slug VARCHAR(255),
                    runtime VARCHAR(255),
                    specs TEXT,
                    working_since DATE,
                    image VARCHAR(255),
                    category_pc_id INT,
                    FOREIGN KEY (category_pc_id) REFERENCES category_pc(id)
);

CREATE TABLE booking (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         hours INT,
                         pc_id INT,
                         user_id INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (pc_id) REFERENCES pc(id),
                         FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE report (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        priority INT,
                        `desc` TEXT,
                        subject VARCHAR(255),
                        status VARCHAR(255),
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        pc_id INT,
                        user_id INT,
                        FOREIGN KEY (pc_id) REFERENCES pc(id),
                        FOREIGN KEY (user_id) REFERENCES user(id)
);
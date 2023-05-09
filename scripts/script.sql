DROP TABLE IF EXISTS online_shop.cart;
DROP TABLE IF EXISTS online_shop.reviews;
DROP TABLE IF EXISTS online_shop.order_items;
DROP TABLE IF EXISTS online_shop.orders;
DROP TABLE IF EXISTS online_shop.products;
DROP TABLE IF EXISTS online_shop.categories;
DROP TABLE IF EXISTS online_shop.manufacturers;
DROP TABLE IF EXISTS online_shop.clients;

CREATE TABLE online_shop.categories
(
    category_id   SERIAL PRIMARY KEY,
    category_name TEXT NOT NULL
);
CREATE TABLE online_shop.manufacturers
(
    manufacturer_id   SERIAL PRIMARY KEY,
    manufacturer_name TEXT NOT NULL
);
CREATE TABLE online_shop.products
(
    product_id          SERIAL PRIMARY KEY,
    product_name        TEXT  NOT NULL,
    manufacturer_id     INT   NOT NULL,
    category_id         INT   NOT NULL,
    product_price       FLOAT NOT NULL,
    product_description TEXT  NOT NULL,
    product_image       TEXT,
    product_rating      FLOAT,
    product_discount    INT,
    product_is_active   INT,
    FOREIGN KEY (manufacturer_id) REFERENCES online_shop.manufacturers (manufacturer_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES online_shop.categories (category_id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- CREATE TABLE online_shop.stores
-- (
--     store_id      SERIAL PRIMARY KEY,
--     store_name    TEXT NOT NULL,
--     store_address TEXT
-- );
-- CREATE TABLE online_shop.deliveries
-- (
--     product_id     INT  NOT NULL,
-- --     store_id       INT  NOT NULL,
--     delivery_date  DATE NOT NULL,
--     products_count INT  NOT NULL,
--     PRIMARY KEY (product_id, delivery_date),
--     FOREIGN KEY (product_id) REFERENCES online_shop.products (product_id) ON UPDATE CASCADE ON DELETE CASCADE
-- --     FOREIGN KEY (store_id) REFERENCES online_shop.stores (store_id) ON UPDATE CASCADE ON DELETE CASCADE
-- );
CREATE TABLE online_shop.clients
(
    client_id           SERIAL PRIMARY KEY,
    client_fname        TEXT NOT NULL,
    client_lname        TEXT NOT NULL,
    client_login        TEXT NOT NULL UNIQUE,
    client_email        TEXT NOT NULL UNIQUE,
    client_phone_number VARCHAR(20) UNIQUE,
    client_password     TEXT NOT NULL,
    client_index        INT,
    client_city         TEXT,
    client_address      TEXT,
    user_role           TEXT NOT NULL,
    CHECK (char_length(client_login) >= 2),
    CHECK (char_length(client_password) >= 5)
);

CREATE TABLE online_shop.orders
(
    order_id     SERIAL PRIMARY KEY,
    client_id    INT  NOT NULL,
--     store_id     INT  NOT NULL,
    order_date   DATE NOT NULL,
    order_status TEXT,
    FOREIGN KEY (client_id) REFERENCES online_shop.clients (client_id) ON UPDATE CASCADE ON DELETE CASCADE
--     FOREIGN KEY (store_id) REFERENCES online_shop.stores (store_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE online_shop.order_items
(
    order_id      INT   NOT NULL,
    product_id    INT   NOT NULL,
    product_count INT   NOT NULL,
    product_price FLOAT NOT NULL,
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (product_id) REFERENCES online_shop.products (product_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES online_shop.orders (order_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE online_shop.reviews
(
    review_id     SERIAL PRIMARY KEY,
    product_id    INT   NOT NULL,
    client_id     INT   NOT NULL,
    review_date   DATE  NOT NULL,
    review_title  TEXT  NOT NULL,
    review_text   TEXT  NOT NULL,
    review_rating FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES online_shop.products (product_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES online_shop.clients (client_id) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE online_shop.cart
(
    cart_id        INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_id     INTEGER NOT NULL,
    client_id      INTEGER NOT NULL,
    count_products INTEGER DEFAULT 1,
    FOREIGN KEY (client_id) REFERENCES online_shop.clients (client_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES online_shop.products (product_id) ON UPDATE CASCADE ON DELETE CASCADE

);
INSERT INTO online_shop.categories (category_name)
VALUES ('Laptops'),
       ('Smartphones'),
       ('Tablets'),
       ('TVs'),
       ('Headphones'),
       ('Cameras'),
       ('Printers'),
       ('Gaming Consoles'),
       ('Smart Watches'),
       ('Smart Home Devices');


INSERT INTO online_shop.manufacturers (manufacturer_name)
VALUES ('Apple'),
       ('Samsung'),
       ('Microsoft'),
       ('Lenovo'),
       ('Dell'),
       ('Sony'),
       ('LG'),
       ('HP'),
       ('Canon'),
       ('Nikon');
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('MacBook Pro', 1, 1, 1999.99, 'Powerful and portable laptop from Apple',
        '../images/mac_book_pro.jpg', 4.5, 10, 1),
       ('Galaxy S21', 2, 2, 899.99, 'High-end smartphone from Samsung', 'images/galaxy_s21.jpeg',
        4.7, 0, 1),
       ('iPad Air', 1, 3, 599.99, 'Thin and light tablet from Apple', 'images/ipad_air.jpg', 4.6, 5,
        1),
       ('55" OLED TV', 7, 4, 1499.99, 'Stunning OLED TV from LG', 'images/lg_tv_55.jpeg', 4.8, 15,
        1),
       ('Noise-Cancelling Headphones', 5, 5, 349.99, 'Premium headphones from Bose',
        'https://example.com/images/bose-headphones.jpg', 4.5, 0, 1),
       ('Mirrorless Camera', 10, 6, 1299.99, 'High-quality camera from Nikon',
        'https://example.com/images/nikon-camera.jpg', 4.9, 20, 1),
       ('Wireless Printer', 8, 7, 249.99, 'Versatile printer from HP', 'https://example.com/images/hp-printer.jpg', 4.2,
        0, 1),
       ('PS5', 4, 8, 499.99, 'Next-gen gaming console from Sony', 'https://example.com/images/ps5.jpg', 4.7, 0, 1),
       ('Apple Watch Series 6', 1, 9, 399.99, 'Advanced smartwatch from Apple',
        'images/apple_watch_s6.jpeg', 4.8, 0, 1),
       ('Smart Thermostat', 3, 10, 149.99, 'Intelligent thermostat from Nest',
        'https://example.com/images/nest-thermostat.jpg', 4.3, 0, 1);

-- INSERT INTO online_shop.stores (store_name, store_address)
-- VALUES ('Electronics4U', '123 Main St, Anytown, USA'),
--        ('Gadgets R Us', '456 Oak St, Anytown, USA'),
--        ('Tech Depot', '789 Pine St, Anytown, USA'),
--        ('The Electronics Store', '321 Maple St, Anytown, USA'),
--        ('Digital Dreams', '654 Cedar St, Anytown, USA'),
--        ('The Gadget Shop', '987 Elm St, Anytown, USA'),
--        ('PC Place', '246 Birch St, Anytown, USA'),
--        ('Electronic Express', '864 Walnut St, Anytown, USA'),
--        ('Tech Solutions', '159 Cherry St, Anytown, USA'),
--        ('Electronic Emporium', '753 Oak St, Anytown, USA');

-- INSERT INTO online_shop.deliveries (product_id, store_id, delivery_date, products_count)
-- VALUES (1, 1, '2023-03-01', 100),
--        (2, 1, '2023-03-01', 50),
--        (3, 2, '2023-03-01', 75),
--        (4, 2, '2023-03-01', 25),
--        (5, 3, '2023-03-01', 150),
--        (6, 3, '2023-03-01', 100),
--        (7, 4, '2023-03-01', 200),
--        (8, 4, '2023-03-01', 50),
--        (9, 5, '2023-03-01', 100),
--        (10, 5, '2023-03-01', 75);

INSERT INTO online_shop.clients (client_fname, client_lname, client_login, client_email, client_phone_number,
                                 client_password, client_index, client_city, client_address, user_role)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@mail.com', '123456789', 'password123', 12345, 'New York', '123 Main St',
        'ROLE_CLIENT'),
       ('Jane', 'Doe', 'janedoe', 'janedoe@mail.com', '987654321', 'password456', 54321, 'Los Angeles', '456 Oak Ave',
        'ROLE_CLIENT'),
       ('Bob', 'Smith', 'bobsmith', 'bobsmith@mail.com', '5551234567', 'password789', 67890, 'Chicago', '789 Elm St',
        'ROLE_CLIENT'),
       ('Alice', 'Johnson', 'alicejohnson', 'alicejohnson@mail.com', '5559876543', 'passwordabc', 45678, 'Houston',
        '101 Maple St', 'ROLE_CLIENT'),
       ('David', 'Brown', 'davidbrown', 'davidbrown@mail.com', '5555551212', 'passworddef', 34567, 'Phoenix',
        '555 Pine St', 'ROLE_CLIENT'),
       ('Samsung', 'Samsa', 'samsung', 'samsung@mail.com', '123', 'passwordsamsung', NULL, NULL, NULL,
        'ROLE_MANUFACTURER'),
       ('Apple', 'aple', 'apple', 'apple@mail.com', '321', 'passwordapple', NULL, NULL, NULL, 'ROLE_MANUFACTURER'),
       ('Sony', 'sonya', 'sony', 'sony@mail.com', '213', 'passwordsony', NULL, NULL, NULL, 'ROLE_MANUFACTURER'),
       ('TEST_Manager', 'Smith', 'managersmith', 'managersmith@mail.com', '5555555555', 'passwordmanager', NULL, NULL,
        NULL,
        'ROLE_MANAGER'),
       ('TESTAdmin', 'Jones', 'Noadmin', 'adminjones@mail.com', '4564356', 'admin', NULL, NULL, NULL,
        'ROLE_ADMIN, ROLE_MANUFACTURER, ROLE_USER'),
       ('TEST_Guest', 'Guest', 'Noguest', 'guest@mail.com', '543236', 'passwordguest', NULL, NULL, NULL, 'GUEST');
INSERT INTO online_shop.orders (client_id , order_date, order_status)
VALUES (1, '2023-03-01', 'Complete'),
       (2, '2023-03-01', 'Pending'),
       (3, '2023-03-01', 'Processing'),
       (4, '2023-03-01', 'Complete'),
       (5, '2023-03-01', 'Complete'),
       (6, '2023-03-01', 'Pending'),
       (7, '2023-03-01', 'Processing'),
       (8, '2023-03-01', 'Complete'),
       (9, '2023-03-01', 'Complete'),
       (10, '2023-03-01', 'Pending');


INSERT INTO online_shop.order_items (order_id, product_id, product_count, product_price)
VALUES (1, 1, 2, 10.99),
       (1, 3, 1, 5.99),
       (2, 2, 3, 15.99),
       (2, 4, 2, 9.99),
       (2, 5, 1, 4.99),
       (3, 1, 4, 10.99),
       (4, 2, 1, 15.99),
       (4, 4, 3, 9.99),
       (5, 3, 2, 5.99),
       (5, 5, 2, 4.99);


INSERT INTO online_shop.reviews (product_id, client_id, review_title, review_text, review_rating, review_date)
VALUES (1, 1, 'Great laptop!',
        'I``ve been using the MacBook Pro for a few weeks now and it``s been fantastic. Fast and reliable.', 4.5,
        '2023-03-03'),
       (1, 2, 'Excellent performance',
        'This laptop is definitely worth the investment. It has everything I need and more.', 5, '2023-03-01'),
       (2, 3, 'Great camera!', 'The Galaxy S21 has an amazing camera that takes beautiful photos. I love this phone!',
        4.8, '2023-03-04'),
       (3, 1, 'Good tablet for work',
        'The iPad Air is a great tablet for getting work done on the go. It``s lightweight and has a long battery life.',
        4.2, '2023-03-02'),
       (4, 2, 'Stunning picture quality',
        'This OLED TV has a beautiful picture and great sound. It``s perfect for watching movies or playing games.',
        4.9, '2023-03-05'),
       (5, 3, 'Amazing headphones',
        'These noise-cancelling headphones are worth every penny. They have great sound quality and are very comfortable to wear.',
        4.7, '2023-03-03'),
       (6, 1, 'Best camera I``ve ever used',
        'The Nikon camera is amazing. It takes beautiful photos and is very easy to use. I would definitely recommend it to anyone.',
        5, '2023-03-01'),
       (7, 2, 'Great printer for home use',
        'This wireless printer from HP is perfect for home use. It``s easy to set up and has great print quality.', 4.3,
        '2023-03-04'),
       (8, 3, 'Awesome gaming console',
        'The PS5 is the best gaming console on the market. It has amazing graphics and is very fast and responsive.',
        4.9, '2023-03-02'),
       (9, 1, 'Great smartwatch',
        'The Apple Watch Series 6 is a great smartwatch. It has all the features I need and looks great too.', 4.6,
        '2023-03-05'),
       (10, 2, 'Intelligent thermostat',
        'The Nest thermostat is a great way to control the temperature in your home. It``s very easy to use and has a lot of features.',
        4.2, '2023-03-03');



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
ALTER TABLE online_shop.clients
    ADD COLUMN activated INTEGER DEFAULT 1;

-- SELECT * FROM online_shop.clients
ALTER TABLE online_shop.clients
    ALTER COLUMN activated SET DEFAULT 0;
CREATE TABLE online_shop.orders
(
    order_id     SERIAL PRIMARY KEY,
    client_id    INT  NOT NULL,
--     store_id     INT  NOT NULL,
    order_date   DATE NOT NULL,
    order_status TEXT,
    FOREIGN KEY (client_id) REFERENCES online_shop.clients (client_id) ON UPDATE CASCADE ON DELETE CASCADE
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
       ('Nikon'),
       ('Google'),
       ('TCL'),
       ('Bose');
*

-- INSERT statements for Laptops
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('MacBook Pro', 1, 1, 1999.00, 'A powerful laptop for creative professionals.',
        'images/mac_book_pro.jpg', 0.0, 0.1, 1),
       ('Galaxy Book Pro', 2, 1, 1399.00, 'A sleek and lightweight laptop for on-the-go productivity.',
        'images/galaxy_book_pro.jpg', 0.0, 0.05, 1),
       ('Surface Laptop 4', 3, 1, 1299.00, 'A versatile laptop with a detachable touch screen.',
        'images/surface_laptop_4.jpg', 0.0, 0.1, 1),
       ('ThinkPad X1 Carbon', 4, 1, 1499.00, 'A durable and high-performance business laptop.',
        'images/thinkpad_x1_carbon.jpeg', 0.0, 0.05, 1),
       ('Inspiron 14 5000', 5, 1, 899.00, 'A budget-friendly laptop with good performance.',
        'images/inspiron_14_5000.png', 0.0, 0.1, 1);

-- INSERT statements for Smartphones
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('iPhone 13 Pro', 1, 2, 1099.00, 'A premium smartphone with advanced camera and performance features.',
        'images/iphone_13_pro.jpeg', 0.0, 0.1, 1),
       ('Galaxy S21 Ultra', 2, 2, 1199.00, 'A flagship smartphone with a large display and powerful processor.',
        'images/galaxy_s21_ultra.jpg', 0.0, 0.1, 1),
       ('Surface Duo 2', 3, 2, 1499.00, 'A unique dual-screen smartphone with productivity features.',
        'images/surface_duo_2.png', 0.0, 0.05, 1),
       ('Xperia 1 III', 6, 2, 1299.00, 'A premium smartphone with advanced camera and audio features.',
        'images/xperia_1_iii.jpg', 0.0, 0.1, 1),
       ('Pixel 6 Pro', 11, 2, 999.00, 'A flagship smartphone with a powerful camera and Googles latest software.',
        'images/pixel_6_pro.jpg', 0.0, 0.05, 1);

-- INSERT statements for Tablets
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('iPad Pro', 1, 3, 799.00, 'A powerful tablet with a large display and Apple Pencil support.',
        'images/ipad_pro.jpeg', 0.0, 0.1, 1),
       ('Galaxy Tab S7+', 2, 3, 849.00, 'A premium Android tablet with a large display and S Pen support.',
        'images/galaxy_tab_s7+.jpg', 0.0, 0.05, 1),
       ('Surface Pro 7', 3, 3, 1299.00, 'A versatile Windows tablet with a detachable keyboard and stylus.',
        'images/surface_pro_7.jpeg', 0.0, 0.1, 1),
       ('Yoga Tab 13', 4, 3, 999.00, 'A unique Android tablet with a built-in kickstand and projector.',
        'images/yoga_tab_13.png', 0.0, 0.05, 1);

-- INSERT statements for TVs
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('LG C1 OLED', 7, 4, 1699.00, 'A premium 4K OLED TV with Dolby Vision and Atmos support.',
        'images/lg_c1_oled.jpeg', 0.0, 0.1, 1),
       ('Q80A QLED', 2, 4, 1499.00, 'A high-performance 4K QLED TV with Quantum HDR and Alexa built-in.',
        'images/q80a_qled.jpg', 0.0, 0.1, 1),
       ('X950H LED', 6, 4, 1299.00, 'A high-end 4K LED TV with Dolby Vision and Atmos support.',
        'images/x950h_led.jpg', 0.0, 0.05, 1),
       ('Bravia A8H OLED', 6, 4, 1999.00, 'A premium 4K OLED TV with Sony X1 Ultimate processor.',
        'images/bravia_a8h_oled.jpg', 0.0, 0.1, 1),
       ('TCL 6-Series', 12, 4, 699.00, 'A budget-friendly 4K QLED TV with Roku TV built-in.',
        'images/tcl_6_series.jpg', 0.0, 0.05, 1);

-- INSERT statements for Headphones
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('AirPods Pro', 1, 5, 249.00, 'Apple premium noise-cancelling earbuds with spatial audio.',
        'images/airpods_pro.jpeg', 0.0, 0.1, 1),
       ('Galaxy Buds Pro', 2, 5, 199.00, 'Samsung high-end true wireless earbuds with ANC and 360 Audio.',
        'images/galaxy_buds_pro.jpg', 0.0, 0.1, 1),
       ('WH-1000XM4', 6, 5, 349.99, 'Sony flagship noise-cancelling headphones with LDAC and 360 Reality Audio.',
        'images/wh_1000xm4.jpeg', 0.0, 0.05, 1),
       ('QuietComfort 35 II', 13, 5, 299.00, 'Bose popular noise-cancelling headphones with Alexa built-in.',
        'images/quietcomfort_35_ii.jpeg', 0.0, 0.1, 1);

-- INSERT statements for Cameras
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('EOS R5', 9, 6, 3899.00, 'Canon high-end full-frame mirrorless camera with 45MP sensor and 8K video.',
        'images/eos_r5.jpeg', 0.0, 0.1, 1),
       ('Alpha 1', 6, 6, 6499.00, 'Sony flagship full-frame mirrorless camera with 50MP sensor and 8K video.',
        'images/alpha_1.jpg', 0.0, 0.1, 1),
       ('Z6 II', 10, 6, 1999.95, 'Nikon versatile full-frame mirrorless camera with 24.5MP sensor and 4K video.',
        'images/z6_ii.jpeg', 0.0, 0.05, 1);

-- INSERT statements for Printers
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('OfficeJet Pro 9025e', 8, 7, 329.99, 'HP smart all-in-one printer with HP Instant Ink and Alexa compatibility.',
        'images/officejet_pro_9025e.jpeg', 0.0, 0.1, 1),
       ('PIXMA TR4520', 9, 7, 99.99, 'Canon affordable all-in-one printer with wireless printing and scanning.',
        'images/pixma_tr4520.jpeg', 0.0, 0.1, 1),
       ('Color LaserJet Pro MFP M281fdw', 8, 7, 429.00,
        'HP high-performance color laser all-in-one printer with auto duplex printing and scanning.',
        'images/color_laserjet_pro_mfp_m281fdw.jpeg', 0.0, 0.1, 1);

-- INSERT statements for Gaming Consoles
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('PlayStation 5', 6, 8, 499.99,
        'Sony next-generation gaming console with ultra-high-speed SSD and DualSense wireless controller.',
        'images/playstation_5.jpeg', 0.0, 0.1, 1),
       ('Xbox Series X', 3, 8, 499.99,
        'Microsoft most powerful gaming console with 12 teraflops of processing power and Xbox Wireless Controller.',
        'images/xbox_series_x.jpeg', 0.0, 0.1, 1);

-- INSERT statements for Smart Watches
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('Apple Watch Series 6', 1, 9, 399.00,
        'Apple latest smartwatch with blood oxygen sensor and always-on Retina display.',
        'images/apple_watch_s6.jpeg', 0.0, 0.1, 1),
       ('Galaxy Watch3', 2, 9, 399.99, 'Samsung premium smartwatch with built-in GPS and ECG monitoring.',
        'images/galaxy_watch3.jpg', 0.0, 0.1, 1);

-- INSERT statements for Smart Home Devices
INSERT INTO online_shop.products (product_name, manufacturer_id, category_id, product_price, product_description,
                                  product_image, product_rating, product_discount, product_is_active)
VALUES ('Nest Learning Thermostat', 11, 10, 249.99,
        'Google smart thermostat that learns your schedule and saves energy',
        'images/nest_learning_thermostat.jpeg', 0.0, 0.1, 1);



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
INSERT INTO online_shop.orders (client_id, order_date, order_status)
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
        'I``ve been using the MacBook Pro for a few weeks now and it``s been fantastic. Fast and reliable.', 4,
        '2023-03-03'),
       (1, 2, 'Excellent performance',
        'This laptop is definitely worth the investment. It has everything I need and more.', 5, '2023-03-01'),
       (2, 3, 'Great camera!', 'The Galaxy S21 has an amazing camera that takes beautiful photos. I love this phone!',
        4, '2023-03-04'),
       (3, 1, 'Good tablet for work',
        'The iPad Air is a great tablet for getting work done on the go. It``s lightweight and has a long battery life.',
        4, '2023-03-02'),
       (4, 2, 'Stunning picture quality',
        'This OLED TV has a beautiful picture and great sound. It``s perfect for watching movies or playing games.',
        4, '2023-03-05'),
       (5, 3, 'Amazing headphones',
        'These noise-cancelling headphones are worth every penny. They have great sound quality and are very comfortable to wear.',
        4, '2023-03-03'),
       (6, 1, 'Best camera I``ve ever used',
        'The Nikon camera is amazing. It takes beautiful photos and is very easy to use. I would definitely recommend it to anyone.',
        5, '2023-03-01'),
       (7, 2, 'Great printer for home use',
        'This wireless printer from HP is perfect for home use. It``s easy to set up and has great print quality.', 4,
        '2023-03-04'),
       (8, 3, 'Awesome gaming console',
        'The PS5 is the best gaming console on the market. It has amazing graphics and is very fast and responsive.',
        4, '2023-03-02'),
       (9, 1, 'Great smartwatch',
        'The Apple Watch Series 6 is a great smartwatch. It has all the features I need and looks great too.', 4,
        '2023-03-05'),
       (10, 2, 'Intelligent thermostat',
        'The Nest thermostat is a great way to control the temperature in your home. It``s very easy to use and has a lot of features.',
        4, '2023-03-03');



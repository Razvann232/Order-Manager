USE warehousedb;

INSERT INTO clients(name, address, email, age) VALUES ("Tudor Popescu", "Str. Nucilor Nr. 12", "tudorP@yahoo.com", 24);
INSERT INTO clients(name, address, email, age) VALUES ("Mihai Ion", "Str. Florilor Nr. 55", "mihaiIon@gmail.com", 50);
INSERT INTO clients(name, address, email, age) VALUES ("Luca Pop", "Str. Unirii Nr. 3", "lucPop@gmail.com", 33);
INSERT INTO clients(name, address, email, age) VALUES ("Alex Mircea", "Str. Barierei Nr. 1", "alexMi@yahoo.com", 18);
INSERT INTO clients(name, address, email, age) VALUES ("Dana Ionescu", "Str. Padurii Nr. 19", "danAIon@yahoo.com", 29);

INSERT INTO products(name, quantity, weight, price) VALUES ("Surubelnita", 400, 0.5, 12.99);
INSERT INTO products(name, quantity, weight, price) VALUES ("Creion", 150, 0.2, 3.99);
INSERT INTO products(name, quantity, weight, price) VALUES ("Caiet", 220, 0.5, 5);
INSERT INTO products(name, quantity, weight, price) VALUES ("Ghiozdan", 100, 1.25, 39.99);
INSERT INTO products(name, quantity, weight, price) VALUES ("Scaun", 150, 5.50, 79.99);

INSERT INTO orders(clientId) VALUES (2);
INSERT INTO orders(clientId) VALUES (5);
INSERT INTO orders(clientId) VALUES (2);
INSERT INTO orders(clientId) VALUES (4);
INSERT INTO orders(clientId) VALUES (4);

INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (1, 5, 10);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (1, 2, 10);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (2, 2, 20);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (5, 3, 30);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (5, 5, 1);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (4, 5, 2);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (4, 4, 3);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (3, 4, 4);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (3, 1, 5);
INSERT INTO products_order(idProduct, idOrder, quantity) VALUES (2, 1, 6);
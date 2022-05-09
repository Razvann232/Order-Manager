CREATE SCHEMA IF NOT EXISTS warehousedb;
USE warehousedb;

DROP TABLE IF EXISTS products_order;	-- stergem tabelele pentru a le putea repopula
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    address VARCHAR(25) NOT NULL,
    email VARCHAR(25) NOT NULL UNIQUE,
    age INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE products(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL UNIQUE,
    quantity INT NOT NULL,
    weight FLOAT NOT NULL,
    price FLOAT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE orders(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    clientId INT NOT NULL,
    price FLOAT DEFAULT 0,
    PRIMARY KEY(id),
    FOREIGN KEY(clientId) REFERENCES clients(id) ON DELETE CASCADE
);

CREATE TABLE products_order(	-- tabel de jonctiune dintre produse si comenzi (ce produse au fost achizitionate la o anumita comanda)
	idProduct INT NOT NULL,
    idOrder INT NOT NULL,
    quantity INT NOT NULL,
    price FLOAT NOT NULL DEFAULT 0,
    FOREIGN KEY(idProduct) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY(idOrder) REFERENCES orders(id) ON DELETE CASCADE
)
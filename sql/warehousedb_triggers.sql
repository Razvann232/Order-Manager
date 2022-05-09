DROP TRIGGER IF EXISTS product_price;

DELIMITER //

CREATE TRIGGER product_price	-- trigger ce decrementeaza automat cantitatea produsului achizitionat
BEFORE INSERT					-- si incrementeaza pretul total al comenzii
ON products_order FOR EACH ROW
BEGIN
	SET @prodId = NEW.idProduct;
	SET NEW.price = NEW.quantity*(SELECT price FROM products WHERE id = @prodId);
    SET @price = NEW.price;
    SET @orderId = NEW.idOrder;
    SET @quantity = NEW.quantity;
    UPDATE orders SET price = price + @price WHERE id = @orderId;
    UPDATE products SET quantity = quantity - @quantity WHERE id = @prodId;
END;//

DELIMITER ;
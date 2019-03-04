USE sky_touch_db;
DELIMITER $$
CREATE PROCEDURE `find_all` ()
BEGIN
	SELECT * FROM generic_product;
END $$
DELIMITER ;

USE sky_touch_db;
DELIMITER $$
CREATE PROCEDURE `insert_product` 
(
	IN id_param VARCHAR(255),
    IN product_name_param VARCHAR(255),
    IN creation_date_param DATE,
    IN description_param VARCHAR(2000)
)
BEGIN
	INSERT INTO generic_product(id,product_name,creation_date,description) VALUES(id_param,product_name_param,creation_date_param,description_param);
END $$
DELIMITER ;

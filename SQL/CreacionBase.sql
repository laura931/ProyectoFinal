CREATE DATABASE Base_Caso;

use Base_Nueva;

CREATE TABLE USUARIOS(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    apellido VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    telefono VARCHAR(45) NOT NULL
    
    
);


INSERT INTO usuarios(nombre, apellido,email,telefono)
VALUES
('Laura','Maza','lauramaza@gmail.com','3814067084'),
('Juan','Perez','juanperez@gmail.com','3511234567'),
('Nicolas','Scaglione','nicoscaglione@gmail.com','3519876543'),
('Nicolas','Berdu','nicolasberdu@gmail.com','3516543217'),
('Azoka','Tano','azokatano@gmail.com','3511237894'),
('Han','Solo','hansolo@gmail.com','3517845126'),
('Laura','Maza','lauramaza@gmail.com','3814067084');


SELECT * FROM usuarios;
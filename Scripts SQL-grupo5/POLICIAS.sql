#DROP DATABASE POLICIAS;
CREATE DATABASE IF NOT EXISTS POLICIAS;
USE POLICIAS;

CREATE TABLE IF NOT EXISTS USERS(
	ID_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30),
    password VARCHAR(30)
);

CREATE TABLE  IF NOT EXISTS ADMINISTRADOR(
	ID_administrador INT PRIMARY KEY DEFAULT 0,
	ID_user INT,
    admin_name VARCHAR(30),
    admin_pass VARCHAR(30),
    FOREIGN KEY (ID_user) REFERENCES USERS(ID_user) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS POLICIA(
    ID_policia INT AUTO_INCREMENT PRIMARY KEY,
    foto_policia LONGBLOB,
    dni VARCHAR(9),
    nombre_policia VARCHAR(30),
    apellido_policia VARCHAR(30),
    rango ENUM('Cabo','Sargento',
    'Teniente','Capitan','Comandante'),
	ID_user INT,
    FOREIGN KEY (ID_user) REFERENCES USERS(ID_user) 
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE  IF NOT EXISTS ARSENAL(
	ID_arsenal INT PRIMARY KEY,
    foto_arsenal LONGBLOB,
    nombre_arsenal VARCHAR(30),
    descripcion_arsenal VARCHAR(300),
    tipo ENUM('Arma','Armadura','Utensilio'),
    ID_administrador INT,
    FOREIGN KEY (ID_administrador) REFERENCES
    ADMINISTRADOR(ID_administrador) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE  IF NOT EXISTS CRIMINAL(
	ID_criminal INT PRIMARY KEY,
    foto_criminal LONGBLOB,
    dni VARCHAR(9),
    nombre_criminal VARCHAR(30),
    apellido_criminal VARCHAR(30),
    descripcion_criminal VARCHAR(500),
    ID_administrador INT,
    ID_policia INT,
    FOREIGN KEY (ID_administrador) REFERENCES
    ADMINISTRADOR(ID_administrador) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ID_policia) REFERENCES
    POLICIA(ID_policia) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE  IF NOT EXISTS NOTICIA(
	ID_noticia INT PRIMARY KEY,
    foto_noticia LONGBLOB,
    titulo VARCHAR(60),
    descripcion_noticia VARCHAR(700),
    ID_administrador INT,
    FOREIGN KEY (ID_administrador) REFERENCES
    ADMINISTRADOR(ID_administrador) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS BUSCA(
	ID_policia INT,
    ID_arsenal INT,
    PRIMARY KEY(ID_policia,ID_Arsenal),
    FOREIGN KEY (ID_policia) REFERENCES
    POLICIA(ID_policia) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (ID_arsenal) REFERENCES
    ARSENAL(ID_arsenal) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO USERS(username,password) 
VALUES('admin','1234');
INSERT INTO USERS(username,password) 
VALUES('marta25','5678');
INSERT INTO USERS(username,password) 
VALUES('alberto28','7890');
INSERT INTO USERS(username,password) 
VALUES('fernando35','101112');
INSERT INTO USERS(username,password) 
VALUES('nico42','4321');
INSERT INTO USERS(username,password) 
VALUES('pedro59','202122');


INSERT INTO ADMINISTRADOR(ID_user,admin_name,admin_pass)
VALUES(1,'policeman','T0S3rv34ndPr0tect*');

INSERT INTO POLICIA(foto_policia,dni,nombre_policia,apellido_policia,rango,ID_user) values
(LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/policia1.jpg'),'12345678J','Marta','Ramírez','Capitan',2),
(LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/policia2.jpg'),'12345678L','Alberto','Rodríguez','Cabo',3),
(LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/policia3.jpg'),'12345678V','Fernando','Gutiérrez','Sargento',4),
(LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/policia4.jpg'),'12345678N','Nicolás','Prado','Teniente',5),
(LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/policia5.jpg'),'14911414P','Pedro','Jiménez','Comandante',6);

INSERT INTO ARSENAL VALUES
(0,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal1.jpg'),'9mm','Ideal para la protección a distancias cortas. Versátil y ligera.','Arma',0);
INSERT INTO ARSENAL VALUES
(1,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal2.jpg'),'Escopeta de goma','Arma obligatoria para la brigada de antidisturbios.','Arma',0);
INSERT INTO ARSENAL VALUES
(2,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal3.jpg'),'Chaleco antibalas','Hecho con material ignífugo.','Armadura',0);
INSERT INTO ARSENAL VALUES
(3,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal4.jpg'),'Esposas','Para detener criminales.','Utensilio',0);
INSERT INTO ARSENAL VALUES
(4,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal5.jpg'),'Porra','El arma de último recurso.','Arma',0);

INSERT INTO CRIMINAL VALUES
(0,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal1.jpg'),'87654321J','Javier','Ruiz','Armado y muy peligroso.',0,1),
(1,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal2.jpg'),'87654321P','David','Ramírez','Tráfico de cocaína.',0,2),
(2,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal3.jpg'),'87654321L','Arturo','López','Maníaco violento.',0,3),
(3,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal4.jpg'),'87654321A','Pedro','Zeta','Traficante de armas.',0,4),
(4,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal5.jpg'),'87654321O','Alberto','Rodríguez','Cultiva marihuana.',0,5);


INSERT INTO NOTICIA VALUES
(0,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/noticia1.jpg'),'Nueva ley antitabaco.','Las autoridades Sanitarias advierten que el tabaco es muy perjudicial para la salud...',0),
(1,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/noticia2.jpg'),'Protestas en Córdoba','Los manifestantes tiraron piedras y botes de humo...',0),
(2,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/noticia3.jpg'),'Manifestación en Madrid','Cientos de personas se reunieron...',0),
(3,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/noticia4.jpg'),'Extraños objetos avistados.','Nadie se explica este fenómeno que...',0),
(4,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/noticia5.jpg'),'La carrera espacial continúa','Las naciones se disputan la supremacía por la carrera espacial...',0);

INSERT INTO BUSCA VALUES
(1,0),
(2,1),
(3,2),
(4,3),
(5,4);

select * from users;

select * from administrador;

select * from policia;

select * from arsenal;

select * from criminal;

select * from noticia;

select * from busca;
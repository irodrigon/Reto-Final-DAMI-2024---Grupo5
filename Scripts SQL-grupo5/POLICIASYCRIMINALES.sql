#DROP DATABASE POLICIASYCRIMINALES;

CREATE DATABASE IF NOT EXISTS POLICIASYCRIMINALES;

USE POLICIASYCRIMINALES;

CREATE TABLE IF NOT EXISTS PERSONA(
	dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(30),
    apellido VARCHAR(30),
    contrasena VARCHAR(30),
    fotografia_persona LONGBLOB
);

CREATE TABLE IF NOT EXISTS ADMINISTRADOR(
	dni VARCHAR(9) PRIMARY KEY,
	fecha_primerLog DATE,
    fecha_ultimoLog DATE,
    FOREIGN KEY (dni) REFERENCES 
    PERSONA(dni) ON UPDATE CASCADE
    ON DELETE CASCADE
); 

CREATE TABLE IF NOT EXISTS NOTICIA(
	id_noticia INT PRIMARY KEY,
    fotografia_noticia LONGBLOB,
    titulo VARCHAR(70),
    descripcion VARCHAR(1000),
    dni VARCHAR(9),
	FOREIGN KEY (dni) REFERENCES 
    ADMINISTRADOR(dni)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);  

CREATE TABLE IF NOT EXISTS POLICIA(
	dni_policia VARCHAR(9) PRIMARY KEY,
    rango ENUM('Cabo','Teniente','Sargento',
    'Capitan','Comandante'),
    FOREIGN KEY (dni_policia) REFERENCES 
    PERSONA(dni)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ARSENAL(
	id_arsenal INT PRIMARY KEY,
    fotografia_arsenal LONGBLOB,
    nombre VARCHAR(30),
    tipo ENUM('arma','armadura','herramienta'),
    descripcion VARCHAR(1000)
);  

CREATE TABLE IF NOT EXISTS CRIMINAL(
	dni VARCHAR(9) PRIMARY KEY,
    descripcion VARCHAR(1000),
	dni_policia VARCHAR(9),
    FOREIGN KEY (dni) REFERENCES 
    PERSONA(dni) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (dni_policia) REFERENCES 
    POLICIA(dni_policia) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ELIGE(
	dni_policia VARCHAR(9),
    id_arsenal int,
    PRIMARY KEY(dni_policia,id_arsenal),
    FOREIGN KEY (dni_policia) REFERENCES 
    POLICIA(dni_policia) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_arsenal) REFERENCES 
    ARSENAL(id_arsenal) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO PERSONA VALUES
('12345678A','Marta','Jimenez','1001',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia1.jpg')),
('12345678B','Juan','Ortega','1002',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia2.jpg')),
('12345678C','Roberto','Rodriguez','1003',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia3.jpg')),
('12345678D','Vanessa','Ramirez','1004',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia4.jpg')),
('12345678E','Fernando','Gutierrez','1005',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia5.jpg')),
('12345678F','Marcos','Martinez','1006',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia6.jpg')),
('12345678G','Nicolas','Prado','1007',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia7.jpg')),
('12345678H','Francisco','Gomez','1008',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotospolicias/fotospol/policia8.jpg')),
('87654321A','Alberto','Ruiz','1009',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal1.jpg')),
('87654321B','Pedro','Zeta','1010',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal2.jpg')),
('87654321C','Felix','De Pablo','1011',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal3.jpeg')),
('87654321D','Carlos','Mazo','1012',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal4.jpeg')),
('87654321E','Guillermo','Sancho','1013',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal5.jpg')),
('87654321F','Miguel','Cortazar','1014',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal6.jpg')),
('87654321G','Juan Andres','Carrasco','1015',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal7.jpg')),
('87654321I','Xabier','Motril','1017',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal11.jpg')),
('87654321J','Daniel','Madrid','1018',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal12.jpg')),
('87654321K','Arturo','Davila','1019',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal13.jpg')),
('87654321L','Mario','Marquez','1020',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal14.jpg')),
('87654321M','Sergio','Da Silva','1021',LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotoscriminales/criminal15.jpg'));


INSERT INTO ADMINISTRADOR VALUES
('12345678H','2023-07-21',current_date()); 

INSERT INTO NOTICIA VALUES
(1, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia10.png'),'Crecida del rio en Dakota del Norte','La situación sigue siendo peligrosa en los aledaños...','12345678H'),
(2, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia11.jpg'),'Choque de trenes en Rusia','Tres muertos y diez heridos graves en esta catástrofe que...','12345678H'),
(3, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia12.jpg'),'Campamentos de refugiados en África tras el último atentado','Cientos de personas sin techo buscan un lugar en el cual obtener comida y bebida...','12345678H'),
(4, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia13.jpg'),'Dos ultras detenidos en Valencia','Los violentos estaban armando jaleo en los exteriores del campo...','12345678H'),
(5, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia14.jpg'),'El ejército se moviliza en Gaza','Ante los últimos altercados homicidas de la franja...','12345678H'),
(6, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia7.jpg'),'El Athletic celebra la Copa del Rey','Miles de aficionados siguieron a los jugadores por la ciudad...','12345678H'),
(7, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia8.jpg'),'Puigdemont se presenta a presidente de La Generalitat','Le ha elegido la junta directiva de sus consejeros...','12345678H'),
(8, LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosnoticias/fotosnot/noticia9.jpg'),'El presidente de Estados Unidos se enfrenta a China','Las dos súper potencias...','12345678H');

INSERT INTO POLICIA VALUES
('12345678A','Cabo'),
('12345678B','Cabo'),
('12345678C','Teniente'),
('12345678D','Teniente'),
('12345678E','Sargento'),
('12345678F','Sargento'),
('12345678G','Capitan'),
('12345678H','Comandante');

INSERT INTO ARSENAL VALUES
(1,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal1.jpg'),'AK-47','arma','Incautada en una redada en Málaga'),
(2,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal2.jpg'),'Escopeta','arma','Arma útil en distancias cortas'),
(3,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal3.png'),'Rifle SCAR','arma','Cedido por el ejército'),
(4,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal5.jpg'),'Escudo antidisturbios','herramienta','Sirve para proteger al agente en situaciones de violencia'),
(5,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal6.jpg'),'Granada','arma','Utilizada por las brigadas especiales'),
(6,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal7.png'),'Gas lacrimógeno','herramienta','Útil para dispersar multitudes'),
(7,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal8.jpg'),'9mm','arma','Arma principal del policía'),
(8,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal9.jpg'),'Escopeta de bolas','arma','Obligatoria en la brigada de antidisturbios'),
(9,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal10.jpg'),'Chaleco antibalas','armadura','Indispensable para protegerse'),
(10,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal11.jpg'),'Esposas','herramienta','Para detener criminales'),
(11,LOAD_FILE('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/fotosarsenal/arsenal12.jpg'),'Porra','arma','Útil para reducir criminales');

INSERT INTO CRIMINAL VALUES
('87654321A','Armado y muy peligroso','12345678A'),
('87654321B','Maniaco violento','12345678B'),
('87654321C','Traficante de cocaina','12345678C'),
('87654321D','Traficante de marihuana','12345678D'),
('87654321E','Exhibicionista','12345678E'),
('87654321F','Ultra violento','12345678F'),
('87654321G','Habil con armas blancas','12345678G'),
('87654321I','Buen tirador','12345678H'),
('87654321J','Genio malvado','12345678A');

INSERT INTO ELIGE VALUES
('12345678A',1),
('12345678B',2),
('12345678C',3),
('12345678D',4),
('12345678E',5),
('12345678F',6),
('12345678G',7),
('12345678H',8);

SELECT * FROM PERSONA;

SELECT * FROM NOTICIA;

SELECT * FROM ADMINISTRADOR;

SELECT * FROM POLICIA;

SELECT * FROM ARSENAL;

SELECT * FROM CRIMINAL;

SELECT * FROM ELIGE;


/*DROP USER 'news'@'localhost';
DROP USER 'policia'@'localhost';
DROP USER 'administrador'@'localhost';

DROP ROLE 'noticias';
DROP ROLE 'policia';
DROP ROLE 'admin';*/

#Si es posible crear todos los usuarios línea por línea y no usando el botón "ejecutar todo"
#Usuario root

GRANT All ON *.* TO 'root'@'localhost' WITH GRANT OPTION;

#Usuario noticias
CREATE ROLE IF NOT EXISTS 'noticias';

GRANT SELECT ON POLICIASYCRIMINALES.NOTICIA TO 'noticias';

CREATE USER 'news'@'localhost' IDENTIFIED BY 'news' DEFAULT ROLE 'noticias';

show grants for 'news'@'localhost';

#Usuario policia
CREATE ROLE IF NOT EXISTS 'policia';

GRANT SELECT,DELETE,UPDATE,INSERT ON POLICIASYCRIMINALES.persona TO 'policia';
GRANT ALL ON POLICIASYCRIMINALES.policia TO 'policia';
GRANT SELECT,DELETE,UPDATE,INSERT ON POLICIASYCRIMINALES.criminal TO 'policia';
GRANT SELECT,DELETE,UPDATE,INSERT ON POLICIASYCRIMINALES.arsenal TO 'policia';
GRANT SELECT,DELETE,UPDATE,INSERT ON POLICIASYCRIMINALES.elige TO 'policia';

CREATE USER 'policia'@'localhost' IDENTIFIED BY 'T0S3rv34ndPr0tect*' DEFAULT ROLE 'policia';

show grants for 'policia'@'localhost';

#Usuario Administrador

CREATE ROLE IF NOT EXISTS 'admin';

GRANT ALL ON policiasycriminales.* to 'admin';

CREATE USER 'administrador'@'localhost' IDENTIFIED BY 'Francisco.*?' DEFAULT ROLE 'admin';

show grants for 'administrador'@'localhost';

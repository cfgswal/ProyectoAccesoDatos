/* ******** TABLAS PIEZAS, PROVEEDORES, PROYECTOS, GESTION:  MySQL********** */

SET FOREIGN_KEY_CHECKS = 0; 
drop table if EXISTS piezas;
drop table if EXISTS proveedores;
drop table if EXISTS proyectos;
drop table if EXISTS gestion;


CREATE TABLE IF NOT EXISTS piezas
(
CODIGO VARCHAR(6) NOT NULL primary key,
NOMBRE VARCHAR(20) NOT NULL,
PRECIO FLOAT NOT NULL,
DESCRIPCION  TEXT 
) ;

CREATE TABLE IF NOT EXISTS proveedores
(
CODIGO VARCHAR(6) NOT NULL primary key,
NOMBRE VARCHAR(20) NOT NULL,
APELLIDOS VARCHAR(30) NOT NULL,
DIRECCION VARCHAR(40) NOT NULL
) ;

CREATE TABLE IF NOT EXISTS proyectos
(
CODIGO VARCHAR(6) NOT NULL primary key,
NOMBRE VARCHAR(40) NOT NULL,
CIUDAD VARCHAR(40) NOT NULL
) ;

CREATE TABLE IF NOT EXISTS gestion
(

CODPROVEEDOR VARCHAR(6) NOT NULL,
CODPIEZA VARCHAR(6) NOT NULL,
CODPROYECTO VARCHAR(6) NOT NULL,
CANTIDAD FLOAT,
primary key (CODPROVEEDOR,CODPIEZA,CODPROYECTO),
foreign key (CODPROVEEDOR) REFERENCES proveedores(CODIGO),
foreign key (CODPIEZA) REFERENCES piezas(CODIGO),
foreign key (CODPROYECTO) REFERENCES proyectos(CODIGO)
) ;

INSERT IGNORE INTO piezas VALUES ('pie001','tornillo 40',0.10,'tornillo cabeza estrella');
INSERT IGNORE INTO piezas VALUES ('pie002','tornillo 50',0.10,'tornillo cabeza estrella');
INSERT IGNORE INTO piezas VALUES ('pie010','tuerca 5',0.05,'tuerca metrica 5');
INSERT IGNORE INTO piezas VALUES ('pie012','tuerca 8',0.08,'tuerca metric 8');
INSERT IGNORE INTO piezas VALUES ('pie110','Alicate',9.90,'Alicate punta redonda');

INSERT IGNORE INTO proveedores VALUES ('pro001','Tornilleria','Gomez','Avenida 12');
INSERT IGNORE INTO proveedores VALUES ('pro002','Tuercas','Perez','Calle Leon 23');
INSERT IGNORE INTO proveedores VALUES ('pro003','Herramientas','Martin','Avenida primera 34');

INSERT IGNORE INTO proyectos VALUES ('pry001','proyecto1','Vitoria');
INSERT IGNORE INTO proyectos VALUES ('pry002','proyecto2','Bilbao');
INSERT IGNORE INTO proyectos VALUES ('pry003','proyecto3','Logroño');
INSERT IGNORE INTO proyectos VALUES ('pry004','proyecto4','Burgos');

INSERT IGNORE INTO gestion VALUES ('pro001','pie002','pry1',200);
INSERT IGNORE INTO gestion VALUES ('pro002','pie010','pry1',200);
INSERT IGNORE INTO gestion VALUES ('pro003','pie110','pry2',20);
commit;


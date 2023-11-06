drop database if exists coladegato;

create database coladegato;

use coladegato;

CREATE TABLE categorias
(
    id_categoria     int auto_increment,
    nom_categoria    varchar(40),
    imagen_categoria mediumblob,
    constraint pk_cat primary key (id_categoria)
);

CREATE TABLE productos
(
    id_producto     int auto_increment,
    nombre          text,
    precio          decimal(10, 2),
    id_categoria    int,
    imagen_producto mediumblob,
    CONSTRAINT pk_prod PRIMARY KEY (id_producto),
    CONSTRAINT fk_cat FOREIGN KEY (id_categoria) references categorias (id_categoria)
);

CREATE TABLE ticket
(
    id_ticket      int auto_increment,
    fecha_creacion datetime,
    total          decimal(10, 2),
    CONSTRAINT pk_ticket PRIMARY KEY (id_ticket)
);

CREATE TABLE detalle_ticket
(
    id_detalle      int auto_increment,
    id_ticket       int,
    id_producto     int,
    precio_unitario decimal(10, 2),
    CONSTRAINT pk_detalle PRIMARY KEY (id_detalle),
    CONSTRAINT fk_ticket FOREIGN KEY (id_ticket) REFERENCES ticket (id_ticket),
    CONSTRAINT fk_prod FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

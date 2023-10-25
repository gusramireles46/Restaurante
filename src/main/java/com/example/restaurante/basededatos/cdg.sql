drop database if exists coladegato;

create database coladegato;

use coladegato;

CREATE TABLE categorias
(
    id_categoria     int auto_increment,
    nom_categoria    varchar(40),
    constraint pk_cat primary key (id_categoria)
);

CREATE TABLE clientes
(
    id_clientes int auto_increment,
    CONSTRAINT pk_clie PRIMARY KEY (id_clientes)
);

CREATE TABLE productos
(
    id_producto    int auto_increment,
    nombre          text,
    precio          float,
    id_categoria    int,
    CONSTRAINT pk_prod PRIMARY KEY (id_producto),
    CONSTRAINT fk_cat FOREIGN KEY (id_categoria) references categorias (id_categoria)
);

CREATE TABLE ticket
(
    id_ticket      int auto_increment,
    id_cliente     int,
    fecha_creacion datetime,
    total          decimal(10, 2),
    CONSTRAINT pk_ticket PRIMARY KEY (id_ticket),
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES clientes (id_clientes)
);

CREATE TABLE detalle_ticket
(
    id_detalle      int auto_increment,
    id_ticket       int,
    id_producto     int,
    cantidad        int,
    precio_unitario decimal(10, 2),
    CONSTRAINT pk_detalle PRIMARY KEY (id_detalle),
    CONSTRAINT fk_ticket FOREIGN KEY (id_ticket) REFERENCES ticket (id_ticket),
    CONSTRAINT fk_prod FOREIGN KEY (id_producto) REFERENCES productos (id_producto)
);

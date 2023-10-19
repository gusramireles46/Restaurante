drop
database if exists coladegato;

create
database coladegato;

use
coladegato;

CREATE TABLE categorias
(
    id_categoria     int auto_increment,
    nom_categoria    varchar(40),
    imagen_categoria blob,
    constraint pk_cat primary key (id_categoria)
);

CREATE TABLE clientes
(
    id_clientes int auto_increment,
    CONSTRAINT pk_clie PRIMARY KEY (id_clientes)
);

CREATE TABLE productos
(
    id_productos    int auto_increment,
    nombre          text,
    precio          float,
    imagen_producto blob,
    id_categoria    int,
    CONSTRAINT pk_prod PRIMARY KEY (id_productos),
    foreign key (id_categoria) references categorias (id_categoria)
);

CREATE TABLE ticket
(
    id_ticket      int auto_increment,
    id_cliente     int,
    fecha_creacion datetime,
    total          decimal(10, 2),
    CONSTRAINT pk_ticket PRIMARY KEY (id_ticket),
    FOREIGN KEY (id_cliente) REFERENCES clientes (id_clientes)
);

CREATE TABLE detalle_ticket
(
    id_detalle      int auto_increment,
    id_ticket       int,
    id_producto     int,
    cantidad        int,
    precio_unitario decimal(10, 2),
    CONSTRAINT pk_detalle PRIMARY KEY (id_detalle),
    FOREIGN KEY (id_ticket) REFERENCES ticket (id_ticket),
    FOREIGN KEY (id_producto) REFERENCES productos (id_productos)
);

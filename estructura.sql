CREATE TYPE estado_sucursal AS ENUM ('operativa', 'no operativa');
CREATE TYPE estado_ruta AS ENUM ('operativa', 'no operativa');
CREATE TYPE estado_orden AS ENUM ('pendiente', 'en proceso');
CREATE TYPE tipo_sucursal AS ENUM ('puerto', 'centro', 'normal');
CREATE TABLE sucursal(
	id serial primary key,
	nombre varchar(50) not null unique,
	horario_apertura time not null,
	horario_cierre time not null,
	estado estado_sucursal not null,
	tipo tipo_sucursal not null
);

CREATE TABLE producto(
	id serial primary key,
	nombre varchar(50) not null unique,
	descripcion varchar(200) not null,
	precio_unitario real not null,
	peso_kg real not null
);

CREATE TABLE orden_provision(
	id serial primary key,
	nombre varchar(50) not null unique,
	fecha date not null,
	estado estado_orden not null,
	tiempo real not null,
	id_sucursal integer references sucursal(id) not null
);

CREATE TABLE cantidad_producto(
	id_orden_provision integer references orden_provision(id) not null,
	id_producto integer references producto(id) not null,
	cantidad integer not null,
	primary key (id_orden_provision,id_producto)
);

CREATE TABLE stock(
	id_sucursal integer references sucursal(id) not null,
	id_producto integer references producto(id) not null,
	cantidad integer not null,
	primary key (id_sucursal,id_producto)
);

CREATE TABLE ruta(
	id serial primary key,
	capacidad real not null,
	estado estado_ruta not null,
	id_sucursal_origen integer references sucursal(id) not null,
	id_sucursal_destino integer references sucursal(id) not null
);

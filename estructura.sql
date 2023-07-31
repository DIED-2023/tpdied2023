CREATE TABLE sucursal(
	id serial primary key,
	nombre varchar(50) not null unique,
	horario_apertura varchar(50) not null,
	horario_cierre varchar(50) not null,
	estado varchar(50) not null,
	tipo varchar(50) not null
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
	estado varchar(50) not null,
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
	estado varchar(50) not null,
	id_sucursal_origen integer references sucursal(id) not null,
	id_sucursal_destino integer references sucursal(id) not null
);

insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('musimundo','7:00','18:00','OPERATIVA','CENTRO');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('fravega','7:00','18:00','OPERATIVA','PUERTO');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('electrohogar','8:00','18:00','OPERATIVA','NORMAL');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('urbanelectro','8:00','18:00','OPERATIVA','NORMAL');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('garbarino','8:00','18:00','OPERATIVA','NORMAL');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('chemes','8:00','18:00','OPERATIVA','NORMAL');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('tecnocrazy','8:30','18:30','OPERATIVA','NORMAL');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('cetrogar','8:30','18:30','OPERATIVA','NORMAL');
insert into sucursal(nombre,horario_apertura,horario_cierre,estado,tipo) values ('nexon','8:00','18:00','OPERATIVA','NORMAL');


insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('smart tv 70" 4k','televisor inteligente de 70 pulgadas con resolucion 4k',200000,25.3);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('celular samsung s22','celular modelo s22 color negro',150000,0.1);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('smart tv 55" 4k', 'televisor inteligente de 55 pulgadas con resolución 4K, conectividad wi-fi y aplicaciones integradas.', 149999.99, 20.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('licuadora lg', 'Licuadora de 1000W con cuchillas de acero inoxidable para mezclar y triturar alimentos.', 2599.99, 2.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('lavarropas automatico dream next','lavarropa inteligente next modelo 346FA3',80000,61.5);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('celular samsung a21','celular modelo a21 color negro, pantalla full hd',90000,0.1);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('licuadora dexter', 'Licuadora de 800W con cuchillas de acero inoxidable para mezclar y triturar alimentos.', 4599.99, 2.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('smart tv 40" full hd', 'televisor inteligente de 55 pulgadas con resolución full hd, conectividad wi-fi y aplicaciones integradas.', 99999.99, 20.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('notebook hp i7','notebook marca hp con procesador i7 de ultima generacion',335000,1.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('notebook samsung i5','notebook marca samsung con procesador i5 de ultima generacion',395000,1.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('notebook acer nitro 5','notebook marca acer, modelo nitro 5, con procesador i9 de ultima generacion',495000,2.0);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('auriculares xtrike gamer','auriculares gamer con microfono',49000,0.2);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('auriculares sony','auriculares sony con alta calidad de sonido',99999.99,0.1);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('auriculares gigabite gamer','auriculares gigabite gamer con microfono',79999.99,0.1);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('lavarropas lg','lavarropa inteligente lg',100000,68.5);
insert into producto(nombre,descripcion,precio_unitario,peso_kg) values ('lavarropas samsung','lavarropa inteligente samsung color blanco',120000,67.5);


insert into ruta (capacidad,duracion,estado,id_sucursal_origen,id_sucursal_destino) values (100,5,'OPERATIVA',2,3);
insert into ruta (capacidad,duracion,estado,id_sucursal_origen,id_sucursal_destino) values (100,5,'OPERATIVA',2,4);
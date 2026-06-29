BEGIN TRANSACTION;
INSERT INTO "roles" ("id","descripcion","nombre") VALUES 
	(1,NULL,'ADMINISTRADOR'),
	(2,NULL,'USUARIOS');
 
INSERT INTO "usuarios" (id, email, nombre, password, rol_id) VALUES 
	(1,'javier@email.net','Javier','{noop}javier',1),
	(2,'pepe@email.net','Pepe','{noop}pepe',2),
	(3,'juan@email.net','Juan','{noop}juan',2);
	
INSERT INTO "categorias" (id, nombre) VALUES 
	(1, 'Informática'),
	(2, 'Accesorios');

COMMIT;

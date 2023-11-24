/*Tabla Rol*/
insert into rols(id_rol,name) values (1,"ADMIN");

/*Tabla usuario*/
insert into users(user_Name,password) values ("omar","pass123");

/*Tabla usario roles*/
insert into user_rols(id_User,id_rol) values (1,1);


/*Tabla de proveedores*/
insert into suppliers(name,city,address,phone) values('Industria Galva','San Salvador','Avenida Juan Pablo',23013234);
insert into suppliers(name,city,address,phone) values('Industria Contrucion','Santa Ana','Avenida Bulev',2334554);
insert into suppliers(name,city,address,phone) values('Industria Hierro','San Salvador','Avenida norte',23013254);
insert into suppliers(name,city,address,phone) values('Industria Ganadera','San Salvador','Avenida norte',23013254);
/*Insertando datos a la tabla tipos de producto*/
insert into category(name) value ('Agroservicio');
insert into category(name) value ('Veterinaria');
insert into category(name) value ('Ferreteria');
insert into category(name) value ('Herramientas');
insert into category(name) value ('Electricidad');
insert into category(name) value ('Fontaneria');
insert into category(name) value ('Construccion');
/*Insertando datos a la tabla productos*/
insert into products(name, unit_price, unit_Weight,category_id,supplier_id,sale_price,stock) values ('Varilla lisa',15,'3/4',3,3,25,30);
insert into products(name, unit_price, unit_Weight,category_id,supplier_id,sale_price,stock) values ('Varilla lisa',15,'3/4',3,3,25,30);
insert into products(name,unit_price,unit_Weight, category_id,supplier_id,sale_price,stock)values ('Chorro Crom',5,'1/4',6,2,25,30);
insert into products(name,unit_price,unit_Weight,  category_id,supplier_id,sale_price,stock) values ('Chorro Crom',5,'1',6,2,25,30);
insert into products(name,unit_price,unit_Weight, category_id,supplier_id,sale_price,stock) values ('Valvula palanca',5,'1/4',6,2,25,30);
insert into products(name,unit_price,unit_Weight,  category_id,supplier_id,sale_price,stock) values ('Valvula palanca',5,'3/4',6,2,25,30);
insert into products(name,unit_price,unit_Weight,  category_id,supplier_id,sale_price,stock) values ('Valvula palanca',5,'1',6,2,25,30);
insert into products(name,unit_price,unit_Weight, category_id,supplier_id,sale_price,stock) values ('Valvula palanca',5,'2',6,2,25,30);
insert into products(name,unit_price,unit_Weight,  category_id,supplier_id,sale_price,stock) values ('Varilla corrugada',15,'3/8',3,1,25,30);
insert into products(name,unit_price,unit_Weight, category_id,supplier_id,sale_price,stock) values ('Varilla corrugada',15,'5/8',3,1,25,30);
insert into products(name,unit_price,unit_Weight,  category_id,supplier_id,sale_price,stock) values ('Valvula pvac',5,'1/4',6,2,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Valvula pvac',6,'1',6,2,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Regadera redonda',12,'',6,2,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Glifosato aleman',20,'',1,4,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Paraquat Aleman',20,'',1,4,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Semilla de maiz',4,'1 libra',1,4,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Pala para arena',8,'',4,4,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('Martillo',8,'',4,1,25,30);
insert into products(name,unit_price,unit_Weight,category_id,supplier_id,sale_price,stock) values ('juego de alicates',8,'',4,1,25,30);




/*Insertando datos en cliente*/
INSERT INTO clients(id,name,adress,email,identification,last_name,phone)VALUES(null,"Omar","Bo El Calvario","omarmenjivar@gmail.com","1234567","Menjivar","7458-8785");
INSERT INTO clients(id,name,adress,email,identification,last_name,phone)VALUES(null,"Juan","San Salvador","juanperez@gmail.com","1234569","Perez","7458-8798");
INSERT INTO clients(id,name,adress,email,identification,last_name,phone)VALUES(null,"Jesus","Chalatenango","jesushenriquez@gmail.com","1234547","Henriquez","7453-8785");
INSERT INTO clients(id,name,adress,email,identification,last_name,phone)VALUES(null,"Xenia","Totolco","ceniarivas@gmail.com","1234342","Rivas","7458-34785");
INSERT INTO clients(id,name,adress,email,identification,last_name,phone)VALUES(null,"Pablo","San Salvador","pablocalles@gmail.com","1234532","Calles","7438-8385");

/*Insertar datos en factura */
INSERT INTO bills(id,client_id,create_at,description,observation) VALUES(null,1,NOW(),null,null);

/*Insertar datos en item_factura*/
INSERT INTO item_bills(id_item_bill,bill_id,cantidad,name_payment_method,product_id)VALUES(null,1,2,"Efectivo",1);



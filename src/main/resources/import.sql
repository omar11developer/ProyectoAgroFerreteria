/*Tabla de usuario*/
insert into Users(roles,user_Name,password) value ('admin','omar','pass123')

/*Tabla de proveedores*/
insert into suppliers(name,city,address,phone) values('Industria Galva','San Salvador','Avenida Juan Pablo',23013234);
insert into suppliers(name,city,address,phone) values('Industria Contrucion','Santa Ana','Avenida Bulev',2334554);
insert into suppliers(name,city,address,phone) values('Industria Hierro','San Salvador','Avenida norte',23013254);
insert into suppliers(name,city,address,phone) values('Industria Ganadera','San Salvador','Avenida norte',23013254);
/*Insertando datos a la tabla tipos de producto*/
insert into type_Products(name) value ('Agroservicio');
insert into type_Products(name) value ('Veterinaria');
insert into type_Products(name) value ('Ferreteria');
insert into type_Products(name) value ('Herramientas');
insert into type_Products(name) value ('Electricidad');
insert into type_Products(name) value ('Fontaneria');
insert into type_Products(name) value ('Construccion');
/*Insertando datos a la tabla productos*/
insert into products(name, unit_price, unit_Weight,stock,id_Type_Product) values ('Varilla lisa',15,'3/4',20,3);
insert into products(name, unit_price, unit_Weight,stock,id_Type_Product) values ('Varilla lisa',15,'3/4',20,3);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product)values ('Chorro Crom',5,'1/4',20,6);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Chorro Crom',5,'1',20,6);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product) values ('Valvula palanca',5,'1/4',20,6);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Valvula palanca',5,'3/4',20,6);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Valvula palanca',5,'1',20,6);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product) values ('Valvula palanca',5,'2',20,6);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Varilla corrugada',15,'3/8',20,3);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product) values ('Varilla corrugada',15,'5/8',20,3);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Valvula pvac',5,'1/4',20,6);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Valvula pvac',6,'1',20,6);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product) values ('Regadera redonda',12,'',20,6);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Glifosato aleman',20,'',20,1);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product) values ('Paraquat Aleman',20,'',20,1);
insert into products(name,unit_price,unit_Weight,stock, id_Type_Product) values ('Semilla de maiz',4,'1 libra',20,1);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Pala para arena',8,'',20,4);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('Martillo',8,'',20,4);
insert into products(name,unit_price,unit_Weight,stock,  id_Type_Product) values ('juego de alicates',8,'',20,4);

/*Insertando datos a la tabla inventario*/
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (20,NOW(),1,3);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (20,NOW(),2,3);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),3,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),4,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),5,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),6,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),7,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),8,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (19,NOW(),9,1);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (18,NOW(),10,1);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (7,NOW(),11,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (9,NOW(),12,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (12,NOW(),13,2);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (26,NOW(),14,4);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (25,NOW(),15,4);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (8,NOW(),16,4);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (12,NOW(),17,1);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (12,NOW(),18,1);
INSERT INTO inventories(sale_price,date_entry,id_product,id_supplier)VALUES (12,NOW(),19,1);



/*Tabla de proveedores*/
insert into suppliers(name,city,address,phone) values('Industria Galva','San Salvador','Avenida Juan Pablo',23013234);
insert into suppliers(name,city,address,phone) values('Industria Contrucion','Santa Ana','Avenida Bulev',2334554);
insert into suppliers(name,city,address,phone) values('Industria Hierro','San Salvador','Avenida norte',23013254);
insert into suppliers(name,city,address,phone) values('Industria Ganadera','San Salvador','Avenida norte',23013254);

insert into type_Products(name) value ('Agroservicio');
insert into type_Products(name) value ('Veterinaria');
insert into type_Products(name) value ('Ferreteria');
insert into type_Products(name) value ('Herramientas');
insert into type_Products(name) value ('Electricidad');
insert into type_Products(name) value ('Fontaneria');
insert into type_Products(name) value ('Construccion');

insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product)values ('Varilla lisa',15,20,'3/4',20,NOW(),3,3);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product)values ('Chorro Crom',5,8,'1/4',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Chorro Crom',5,8,'1',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Valvula palanca',5,7,'1/4',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Valvula palanca',5,7,'3/4',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Valvula palanca',5,7,'1',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Valvula palanca',5,7,'2',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Varilla corrugada',15,20,'3/8',20,NOW(),3,3);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Varilla corrugada',15,20,'5/8',20,NOW(),3,3);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Valvula pvac',5,7,'1/4',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Valvula pvac',6,8,'1',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Regadera redonda',12,18,'',20,NOW(),2,6);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Glifosato aleman',20,28,'',20,NOW(),4,1);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Paraquat Aleman',20,28,'',20,NOW(),4,1);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Semilla de maiz',4,8,'1 libra',20,NOW(),4,1);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Pala para arena',8,14,'',20,NOW(),2,4);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('Martillo',8,14,'',20,NOW(),2,4);
insert into products(name,unit_price,sale_price,unit_Weight,stock, create_at, supplier_id_supplier, type_product_id_type_product) values ('juego de alicates',8,14,'',20,NOW(),2,4);
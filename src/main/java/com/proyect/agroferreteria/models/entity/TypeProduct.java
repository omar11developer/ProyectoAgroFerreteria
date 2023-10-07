package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Creando la clase Tipos de producto
@Entity
@Table(name = "type_Products")
public class TypeProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Type_Product")
    private Long idTypeProduct;

    @NotNull
    private String name;


    @OneToMany(mappedBy = "typeProduct", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    public TypeProduct() {
    products = new ArrayList<Product>();
    }
    //Getter y Setter de la clase
    public Long getIdTypeProduct() {
        return idTypeProduct;
    }



    public void setIdTypeProduct(Long idTypeProduct) {
        this.idTypeProduct = idTypeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    //Serializable de la clase
    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;

}

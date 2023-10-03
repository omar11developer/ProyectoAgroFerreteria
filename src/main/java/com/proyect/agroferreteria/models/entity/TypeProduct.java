package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
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

    public TypeProduct() {
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
    //Serializable de la clase
    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;

}

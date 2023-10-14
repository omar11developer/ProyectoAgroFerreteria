package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//Creando la clase Tipos de producto
@Entity
@Table(name = "type_Products")
public class TypeProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Type_Product")
    private Long id;

    @NotNull
    private String name;


    @OneToMany(
            mappedBy = "typeProduct",
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"typeProduct"})
    private Set<Product> products;

    public TypeProduct() {

    }
    //Getter y Setter de la clase


    public TypeProduct(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }



    public void setId(Long idTypeProduct) {
        this.id = idTypeProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    //Serializable de la clase
    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;

    @Override
    public String toString() {
        return "TypeProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeProduct that = (TypeProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

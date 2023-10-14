package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

//Creando la entidad Producto
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Product")
    private Long idProduct;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "unit_price")
    private Double unitPrice;


    @Column(name = "unit_Weight")
    private String unitWeight;

    @NotNull
    public Integer stock;

    @OneToMany(
            mappedBy = "product", fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"product"})
    private Set<Inventories> inventories;


    @ManyToOne(
            optional = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id_Type_Product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    private TypeProduct typeProduct;


    public Product() {

    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    public Set<Inventories> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventories> inventories) {
        this.inventories = inventories;
    }

    //Serializaciond e la clase
    public static long getSerializacionUID(){
        return serializacionUID;
    }
    private static final long serializacionUID= 1L;
}

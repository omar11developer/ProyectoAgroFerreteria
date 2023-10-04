package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "unit_Weight")
    private String unitWeight;

    @NotNull
    @Column(name = "sale_Price")
    private Double salePrice;

    private Double stock;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Order_Details")
    private List<OrderDetails> orderDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    private TypeProduct typeProduct;

    public Product() {
        this.orderDetails = new ArrayList<OrderDetails>();
    }

    //getter y setter de la clase
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

    public String getUnitWeight() {
        return unitWeight;
    }

    public void setUnitWeight(String unitWeight) {
        this.unitWeight = unitWeight;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }
    //Serializaciond e la clase
    public static long getSerializacionUID(){
        return serializacionUID;
    }
    private static final long serializacionUID= 1L;
}

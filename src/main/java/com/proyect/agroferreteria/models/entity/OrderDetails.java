package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
//Creando la Entidad orderDetails
@Entity
@Table(name = "order_Details")
public class OrderDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Order_Details")
    private Long idOrderDetails;

    @NotNull
    @Column(name = "cost_Product")
    private Double costProduct;
    //Contructor de la clase
    public OrderDetails() {
    }
    //getter y setter de la clase order Details
    public Long getIdOrderDetails() {
        return idOrderDetails;
    }

    public void setIdOrderDetails(Long idOrderDetails) {
        this.idOrderDetails = idOrderDetails;
    }

    public Double getCostProduct() {
        return costProduct;
    }

    public void setCostProduct(Double costProduct) {
        this.costProduct = costProduct;
    }
    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;
}

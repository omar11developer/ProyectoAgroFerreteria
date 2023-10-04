package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Supplier supplier;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAtOrder;


    @NotEmpty
    private Integer units;

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }


    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;
}

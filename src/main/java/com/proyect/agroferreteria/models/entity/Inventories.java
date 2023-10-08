package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Inventories")
public class Inventories implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Inventory;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_Entry")
    private Date createAtOrder;

    @NotNull
    @Column(name = "sale_price")
    private Double salePrice;


    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name = "id_Supplier")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name = "id_Product")
    private Product product;
/*@OneToMany(mappedBy = "inventories",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
private List<ItemBill>itemBillList;


    public Inventories() {
        itemBillList=new ArrayList<ItemBill>();
    }

    public List<ItemBill> getItemBillList() {
        return itemBillList;
    }

    public void setItemBillList(List<ItemBill> itemBillList) {
        this.itemBillList = itemBillList;
    }*/
@PrePersist
public void prePersist(){
    createAtOrder =new Date();
}


    public Long getId_Inventory() {
        return id_Inventory;
    }

    public void setId_Inventory(Long id_Inventory) {
        this.id_Inventory = id_Inventory;
    }

    public Date getCreateAtOrder() {
        return createAtOrder;
    }

    public void setCreateAtOrder(Date createAtOrder) {
        this.createAtOrder = createAtOrder;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;
}

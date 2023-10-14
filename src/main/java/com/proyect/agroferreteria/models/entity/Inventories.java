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


    @ManyToOne(
            optional = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id_Supplier")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Inventories"})
    private Supplier supplier;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id_Product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "Inventories"})
    private Product product;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "inventories"
    )
    @JsonIgnoreProperties({"Inventories"})
    private Set<ItemBill> itemBill;

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

    public Set<ItemBill> getItemBill() {
        return itemBill;
    }

    public void setItemBill(Set<ItemBill> itemBill) {
        this.itemBill = itemBill;
    }

    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;
}

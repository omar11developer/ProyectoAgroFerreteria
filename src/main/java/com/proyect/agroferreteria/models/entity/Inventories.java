package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.*;

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
    @JsonIgnore
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
    private Product product;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "inventories"
    )
    @JsonIgnore
    private Set<ItemBill> itemBill;


    public Inventories() {
    }

    public Inventories(Long id_Inventory, Double salePrice) {
        this.id_Inventory = id_Inventory;
        this.salePrice = salePrice;
    }

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

    @Override
    public String toString() {
        return "Inventories{" +
                "id_Inventory=" + id_Inventory +
                ", createAtOrder=" + createAtOrder +
                ", salePrice=" + salePrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventories that = (Inventories) o;
        return Objects.equals(id_Inventory, that.id_Inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_Inventory);
    }
}

package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "item_Bills")
public class ItemBill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Item_Bill")
    private Long idItemBill;
    @NotNull
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name = "id_Inventories")
    private Inventories inventories;

    public Long getIdItemBill() {
        return idItemBill;
    }

    public void setIdItemBill(Long idItemBill) {
        this.idItemBill = idItemBill;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Inventories getInventories() {
        return inventories;
    }

    public void setInventories(Inventories inventories) {
        this.inventories = inventories;
    }

    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;
}

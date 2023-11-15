package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "item_Bills")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ItemBill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Item_Bill")
    private Long idItemBill;
    @NotNull
    private Integer cantidad;

    @ManyToOne(
            fetch = FetchType.LAZY,optional = true,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    @JoinColumn(name = "id_Inventories")
    private Inventories inventories;

    @ManyToOne(
            fetch = FetchType.LAZY, optional = true,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    @JoinColumn(name = "id_Bill")
    private Bill bill;


    public ItemBill(Long idItemBill, Integer cantidad) {
        this.idItemBill = idItemBill;
        this.cantidad = cantidad;
    }



}

package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Inventories")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
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



    @NotNull
    private Integer stock;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id_Product")
    @JsonIgnoreProperties({"hibernateLazyInitializer","Inventories"})
    private Product product;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "inventories"
    )
    @JsonIgnore
    private Set<ItemBill> itemBill;

    public Inventories(Date createAtOrder, Double salePrice, Integer stock) {
        this.createAtOrder = createAtOrder;
        this.salePrice = salePrice;
        this.stock = stock;
    }

    @PrePersist
    public void prePersist(){
    createAtOrder =new Date();
}


}

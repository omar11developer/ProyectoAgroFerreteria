package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_Entry")
    private Date createAtOrder;

    @NotNull
    @Column(name = "sale_price")
    private Double salePrice;



    @NotNull
    private Integer stock;

    @ManyToOne(
            fetch = FetchType.LAZY
            ,optional = true,
            cascade = {
                    CascadeType.ALL
            }
    )
    @JoinColumn(name = "id_product")
    @JsonIgnoreProperties({"hibernateLazyInitializer","Inventories"})
    private Product product;

    @OneToMany(
            fetch = FetchType.LAZY,
            //mappedBy = "inventories",
           cascade = {
                    CascadeType.PERSIST
           }
           //cascade = CascadeType.MERGE
            //orphanRemoval = true
    )
    @JsonIgnoreProperties({"Inventories"})
    @JoinColumn(name = "inventories_id")
    private Set<ItemBill> itemBill = new HashSet<>();



    @PrePersist
    public void prePersist(){
    createAtOrder =new Date();
}


}

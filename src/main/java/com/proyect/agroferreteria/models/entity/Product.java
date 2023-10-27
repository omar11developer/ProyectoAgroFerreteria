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

//Creando la entidad Producto
@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Product")
    private Long idProduct;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "unit_price")
    private Double unitPrice;


    @Column(name = "unit_Weight")
    private String unitWeight;

    @NotNull
    private Integer stock;

    @OneToMany(
            mappedBy = "product", fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Set<Inventories> inventories = new HashSet<>();


    @ManyToOne(
            optional = true,
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name = "id_Type_Product")
    // @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    //@JsonIgnore
    private TypeProduct typeProduct;




    public Product(String name, Double unitPrice, String unitWeight, Integer stock) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.unitWeight = unitWeight;
        this.stock = stock;
    }


}

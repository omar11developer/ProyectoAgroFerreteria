package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    //@Column(name = "id_product")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "unit_price")
    private Double unitPrice;


    @Column(name = "unit_Weight")
    private String unitWeight;


    @OneToMany(
            mappedBy = "product", fetch = FetchType.LAZY
            /*cascade = {
                    CascadeType.ALL
            },*/
           // orphanRemoval = true

    )
    @JsonIgnore
    private Set<Inventories> inventories = new HashSet<>();


    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    /*        optional = true,
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
    )
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey (name = "FK_CATEGORY_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    private Category category;

    @ManyToOne(
           optional = true,
            //fetch = FetchType.LAZY,
          cascade = {CascadeType.PERSIST}
          /*  cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
    )
    @JoinColumn(
            name = "supplier_id",
            foreignKey = @ForeignKey(name = "FK_SUPPLIER_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    private Supplier supplier;




}

package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @Min(0)
    @Column(name = "unit_price")
    private Double unitPrice;


    @Column(name = "unit_Weight")
    private String unitWeight;
    @NotNull
    @Min(0)
    @Column(name = "sale_price")
    private Double salePrice;


    @NotNull
    @Min(value = 1, message = "El stok debe de ser al menos de 1")
    @Positive(message = "Este campo debe ser positivo")
    private Integer stock;


    @OneToMany(

            fetch = FetchType.LAZY,
            cascade =  {CascadeType.PERSIST,CascadeType.MERGE}
    )
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    private Set<ItemBill> itemBills = new HashSet<>();

    @NotNull
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(
            name = "category_id",
            foreignKey = @ForeignKey (name = "FK_CATEGORY_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    private Category category;

    @ManyToOne(
           optional = true,
          cascade = {CascadeType.PERSIST}
    )
    @JoinColumn(
            name = "supplier_id",
            foreignKey = @ForeignKey(name = "FK_SUPPLIER_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "products"})
    private Supplier supplier;


}

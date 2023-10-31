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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//Creando la clase provedor
@Entity
@Table(name = "suppliers")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Supplier")
    private Long idSupplier;

    @NotNull
    private String name;

    private String city;

    private String address;
    @NotNull
    private Integer phone;
    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","suppliers"})
    private Set<Product> products;


    public Supplier(String name, String city, String address, Integer phone) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }


}

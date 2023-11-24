package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

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
    //@Column(name = "id_Supplier")
    private Long id;

    @NotNull
    private String name;

    private String city;

    private String address;

    @NotEmpty(message = "El campo no debe ir vacio")
    @Size(min = 8, max = 8, message = "El minimo y maximo es de 8 numeros")
    private String phone;

    @OneToMany(
            //mappedBy = "supplier",
            fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST
        /*    cascade = CascadeType.PERSIST,
            orphanRemoval = true*/
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer","suppliers"})
    @JoinColumn(name = "supplier_id")
    private Set<Product> products = new HashSet<>();




}

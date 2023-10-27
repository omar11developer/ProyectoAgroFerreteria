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

//Creando la clase Tipos de producto
@Entity
@Table(name = "type_Products")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class TypeProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Type_Product")
    private Long id;

    @NotNull
    private String name;

    /*
    @OneToMany(
            mappedBy = "typeProduct",
            fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties({"type_Products"})
    @JsonIgnore
    private Set<Product> products = new HashSet<>();
    */

}

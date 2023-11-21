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
import java.util.HashSet;
import java.util.Set;

//Creando la clase Tipos de producto
@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long id;

    @NotNull
    private String name;


    @OneToMany(
            mappedBy = "category", //Mapea la entidad
            fetch = FetchType.LAZY, // Tipo de busqueda y respuesta que ara a la base de datos
            cascade = CascadeType.PERSIST, //Que persista la entidad
            orphanRemoval = true //Eliminar una entidad relacionada de la coleccion de entidades
    )
    @JsonIgnore
    private Set<Product> products = new HashSet<>();


}

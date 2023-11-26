package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(

            fetch = FetchType.LAZY,
           cascade = CascadeType.PERSIST
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer","category"})
    @JoinColumn(name = "category_id")
    private Set<Product> products = new HashSet<>();


}

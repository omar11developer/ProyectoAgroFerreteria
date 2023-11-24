package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "no puede quedar vacio")
    @Size(min = 3,max = 50,message = " debe contener al menos 3 letras")
    private String name;
    @NotEmpty(message = "no puede quedar vacio")
    private String lastName;
    @NotEmpty(message = "no puede quedar vacio y debe ser un único")
    @Column(nullable = false,unique = true)
    private String identification;
    @NotEmpty(message = "es requerido")
    private String adress;
    @NotEmpty(message = "puede quedar vacio y debe ser un único")
    @Email(message = "Por favor ingresa un correo valido!")
    @Column(nullable = false, unique = true)
    private String email;
    @NotEmpty(message = "El numero teléfonico es importante, por favor ingresalo")
    private String phone;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "client",
            cascade = {CascadeType.ALL}
            //orphanRemoval = true
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer","clients"})
    //@JsonIgnore
    private Set<Bill> bill = new HashSet<>();




}

package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Creando la entidad de la tabla usuario
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Users  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_User")
    private Long idUser;


    @NotNull
    private String userName;

    @NotNull
    @JoinColumn(name = "id_users_rol")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "users")
    @JsonIgnore
    private List<UserRols> usersRoles = new ArrayList<>();



}

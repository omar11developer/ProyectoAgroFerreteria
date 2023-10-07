package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Creando la entidad de la tabla usuario
@Entity
@Table(name = "users")
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
    private Set<UserRols> usersRoles = new HashSet<>();

    //Constructores de la entidad
    public Users() {
    }

    public Long getIdUser() {
        return idUser;
    }

    // Metodos GETTER Y SETTER de la clase User
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRols> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UserRols> usersRoles) {
        this.usersRoles = usersRoles;
    }
}

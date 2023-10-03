package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

//Creando la entidad de la tabla usuario
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotNull
    private String roles;
    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String password;

    //Constructores de la entidad
    public User() {
    }

    public Long getIdUser() {
        return idUser;
    }

    // Metodos GETTER Y SETTER de la clase User
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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
    //Serializando la clase User
    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
    private static final long serialVersionUID = 1L;
}

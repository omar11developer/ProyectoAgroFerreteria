package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rols")
public class Rols {
    @Id
    @Column(name = "id_rol")
    private Long rolId;
    @Column(name = "name")
    private String nameRol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private List<UserRols> usersRoles = new ArrayList<>();


    public Rols() {
    }

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public List<UserRols> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(List<UserRols> usersRoles) {
        this.usersRoles = usersRoles;
    }
}

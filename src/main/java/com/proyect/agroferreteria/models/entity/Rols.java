package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rols")
public class Rols {
    @Id
    @Column(name = "id_rol")
    private Long rolId;
    @Column(name = "name")
    private String nameRol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<UserRols> usersRoles = new HashSet<>();


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

    public Set<UserRols> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UserRols> usersRoles) {
        this.usersRoles = usersRoles;
    }
}

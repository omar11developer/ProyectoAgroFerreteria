package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rols")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Rols {
    @Id
    @Column(name = "id_rol")
    private Long rolId;
    @Column(name = "name")
    private String nameRol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private List<UserRols> usersRoles = new ArrayList<>();


}

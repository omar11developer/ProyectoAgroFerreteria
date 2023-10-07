package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_rols")
public class UserRols {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users_rol")
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_User")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rols rol;

    public UserRols() {
    }

    public Long getUsuarioRolId() {
        return usuarioRolId;
    }

    public void setUsuarioRolId(Long usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Rols getRol() {
        return rol;
    }

    public void setRol(Rols rol) {
        this.rol = rol;
    }
}

package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_rols")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class UserRols {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users_rol")
    private Long usuarioRolId;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @JoinColumn(name = "id_User")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rols rol;

}

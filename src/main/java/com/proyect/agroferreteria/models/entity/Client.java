package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;
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
    @Column(name = "id_Client")
    private Long idClient;
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
            mappedBy = "client"
    )
    @JsonIgnoreProperties({"clients"})
    private Set<Bill> bill;



    public Client(String name, String lastName, String identification, String adress, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(idClient, client.idClient) && Objects.equals(name, client.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, name);
    }
}

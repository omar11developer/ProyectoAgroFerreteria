package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Client")
    private Long idClient;
    @NotEmpty(message = "no puede quedar vacio")
    @Size(min = 3,max = 50,message = " debe contener al menos 3 letras")
    @Column(nullable = false)
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

    public Client() {
    }

    public Client(String name, String lastName, String identification, String adress, String email, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.adress = adress;
        this.email = email;
        this.phone = phone;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Set<Bill> getBill() {
        return bill;
    }

    public void setBill(Set<Bill> bill) {
        this.bill = bill;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public static long getSerializableUID(){
        return serializableUID;
    }
    private static final long serializableUID=1L;

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", identification='" + identification + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
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

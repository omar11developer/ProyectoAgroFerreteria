package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

//Creando la clase provedor
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Supplier")
    private Long idSupplier;

    @NotNull
    private String name;

    private String city;

    private String address;
    @NotNull
    private Integer phone;



    //Constructores de la clase

    public Supplier() {
    }
    //Metodos getter and setter de la clase

    public Long getIdSupplier() {
        return idSupplier;
    }

    public void setIdSupplier(Long idSupplier) {
        this.idSupplier = idSupplier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
    //Serializacion de la clase proveedor
    public static long getSerialversionuid(){
        return serialVersionUID;
    }
    private static final long serialVersionUID=1L;
}

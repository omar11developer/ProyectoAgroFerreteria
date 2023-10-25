package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//Creando la clase provedor
@Entity
@Table(name = "suppliers")
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
    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"suppliers"})
    private Set<Inventories> inventories;

    public Supplier() {

    }

    public Supplier(String name, String city, String address, Integer phone) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

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

    public Set<Inventories> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventories> inventories) {
        this.inventories = inventories;
    }

    //Serializacion de la clase proveedor
    public static long getSerialversionuid(){
        return serialVersionUID;
    }
    private static final long serialVersionUID=1L;

    @Override
    public String toString() {
        return "Supplier{" +
                "idSupplier=" + idSupplier +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(idSupplier, supplier.idSupplier) && Objects.equals(name, supplier.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSupplier, name);
    }
}

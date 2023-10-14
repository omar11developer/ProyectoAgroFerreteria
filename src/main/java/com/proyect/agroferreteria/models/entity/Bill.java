package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bills")
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Bill")
    private Long idBill;

    private String description;
    private String observation;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_At",nullable = false)
    private Date creatAt;

    @NotEmpty(message = "Este campo no puede quedar vacio")
    @Size(min = 10, max = 50)
    private String paymentMethod;

    @ManyToOne(
            fetch = FetchType.LAZY,optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "bills"})
    @JoinColumn(name = "id_Client")
    private Client client;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "bill"
    )
    @JsonIgnoreProperties({"bills"})
    private Set<ItemBill> itemBills;
    @PrePersist
    public void prePersist(){
        creatAt =new Date();
    }

    public Bill() {

    }

    public Long getIdBill() {
        return idBill;
    }

    public void setIdBill(Long idBill) {
        this.idBill = idBill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ItemBill> getItemBills() {
        return itemBills;
    }

    public void setItemBills(Set<ItemBill> itemBills) {
        this.itemBills = itemBills;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

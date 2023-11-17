package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Length;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bills")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
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
    @JsonIgnore
    private Set<ItemBill> itemBills;
    @PrePersist
    public void prePersist(){
        creatAt =new Date();
    }





}

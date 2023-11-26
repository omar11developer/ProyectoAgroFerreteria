package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "bills")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String observation;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_At",nullable = false)
    private Date creatAt;



    @ManyToOne(
            fetch = FetchType.LAZY
            ,
            cascade = {
                    CascadeType.PERSIST
            }
    )
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_CLIENT_ID"))
    @JsonIgnoreProperties({"hibernateLazyInitializer", "bill"})
    private Client client;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
    )
    @JoinColumn(name = "bill_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "bills"})
    private Set<ItemBill> itemBills = new HashSet<>();
    @PrePersist
    public void prePersist(){
        creatAt =new Date();
    }

}

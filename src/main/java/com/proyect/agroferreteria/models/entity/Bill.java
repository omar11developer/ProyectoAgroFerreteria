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
           /* ,optional = true,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "bills"})
    //@JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(
            fetch = FetchType.LAZY,
            //mappedBy = "bill",
            cascade = CascadeType.ALL
            //orphanRemoval = true
    )
    @JoinColumn(name = "bill_id")
    private Set<ItemBill> itemBills = new HashSet<>();
    @PrePersist
    public void prePersist(){
        creatAt =new Date();
    }

}

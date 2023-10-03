package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Order")
    private Long idOrder;
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date_order")
    private Date createDateOrder;

}

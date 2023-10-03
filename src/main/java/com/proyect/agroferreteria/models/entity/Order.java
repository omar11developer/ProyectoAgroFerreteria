package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;
//Creando entidad Order
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

    @NotNull
    private Integer units;

    //Contructor de la clase Order

    public Order() {
    }
    //Getter y setter de la clase Order

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Date getCreateDateOrder() {
        return createDateOrder;
    }

    public void setCreateDateOrder(Date createDateOrder) {
        this.createDateOrder = createDateOrder;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    //Serializacion de la clase
    public static long getSerializacionUID(){
        return serializacionUID;
    }
    private static final long serializacionUID=1L;

}

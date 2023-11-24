package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "item_Bills")
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class ItemBill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Item_Bill")
    private Long id;
    @NotNull
    private Integer cantidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date createAtOrder;

    @ManyToOne(
           fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            }
    )
    @JoinColumn(
            name = "product_id",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_ID")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    private Product product;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REMOVE
            }
    )
    @JoinColumn(
            name = "bill_id",
            foreignKey = @ForeignKey(name = "FK_BILL_ID")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    private Bill bill;



    //@NotEmpty(message = "Este campo no puede quedar vacio")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "namePaymentMethod", column = @Column(name = "name_payment_method")),
            @AttributeOverride(name = "firstPayment", column = @Column(name = "first_payment")),
            @AttributeOverride(name = "secondPayment", column = @Column(name = "second_payment")),
            @AttributeOverride(name = "thirdPayment", column = @Column(name = "third_payment")),
            @AttributeOverride(name = "fourthPayment", column = @Column(name = "fourth_payment")),
    })
    private PaymentMethod paymentMethod;

   // @Positive(message = "Este campo tiene que ser positivo")
    @Column(name = "price_total")
    private Double priceTotal;




    public void caluclarMonto(){
        paymentMethod.setMonto(priceTotal/4);
    }

    public void calcularPagos(){/* public void crearPrecioFinal(){
        Integer cantidadProductos = cantidad;
        Double priceProducto = inventories.getSalePrice();
        priceTotal = cantidadProductos * priceProducto;
    }*/
        Double debe = paymentMethod.getDebe();
            Double primerPago = paymentMethod.getFirstPayment();
            Double segundoPago = paymentMethod.getSecondPayment() + primerPago;
            Double tercerPago = paymentMethod.getThirdPayment() + segundoPago;
            Double cuartoPAgo = paymentMethod.getFourthPayment() + tercerPago;
            if(primerPago <= debe){
                  paymentMethod.setDebe(debe-primerPago);
            } else if (segundoPago <= debe) {
                paymentMethod.setDebe(debe-segundoPago);
            } else if (tercerPago <= debe) {
                paymentMethod.setDebe(debe-tercerPago);
            } else if (cuartoPAgo <= debe) {
                paymentMethod.setDebe(debe-cuartoPAgo);
            }
    }

    @PrePersist
    public void prePersist(){
        createAtOrder =new Date();
    }

}

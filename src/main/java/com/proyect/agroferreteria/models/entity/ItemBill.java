package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

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
    private Long idItemBill;
    @NotNull
    private Integer cantidad;

    @ManyToOne(
            fetch = FetchType.LAZY,optional = true,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    @JoinColumn(name = "id_Inventories")
    private Inventories inventories;

    @ManyToOne(
            fetch = FetchType.LAZY, optional = true,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    @JoinColumn(name = "id_Bill")
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


    public void crearPrecioFinal(){
        Integer cantidadProductos = cantidad;
        Double priceProducto = inventories.getSalePrice();
        priceTotal = cantidadProductos * priceProducto;
    }

    public void caluclarMonto(){
        paymentMethod.setMonto(priceTotal/4);
    }

    public void calcularPagos(){
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



}

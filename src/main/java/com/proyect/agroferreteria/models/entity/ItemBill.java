package com.proyect.agroferreteria.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @NotNull(message = "Este campo no puede ir vacio")
    @Min(value = 1,message = "Al menos debe ser mayor a 1")
    private Integer cantidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_creation")
    private Date createAtOrder;

    @ManyToOne(
           fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST
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
                    CascadeType.ALL
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
            @AttributeOverride(name = "namePaymentMethod", column = @Column(name = "name_payment_method"))
    })
    private PaymentMethod paymentMethod;

   // @Positive(message = "Este campo tiene que ser positivo")
    @Column(name = "price_total")
    private Double priceTotal;



    @PrePersist
    public void prePersist(){
        createAtOrder =new Date();
    }

}

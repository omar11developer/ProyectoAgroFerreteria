package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.models.entity.PaymentMethod;
import com.proyect.agroferreteria.models.entity.Product;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemBillDTO {
    private Long id_Item_Bill;
    @NotNull
    @NotEmpty
    private Integer cantidad;

    private Date date_create;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    private Product product;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    private Bill bill;



    private PaymentMethod paymentMethod;

    //@Positive(message = "Este campo tiene que ser positivo")
    private Double priceTotal;


}

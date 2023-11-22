package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Bill;
import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemBillDTO {
    private Long id_Item_Bill;
    @NotNull
    @NotEmpty
    private Integer cantidad;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    private Inventories inventories;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "item_Bills"})
    private Bill bill;



    private PaymentMethod paymentMethod;

    //@Positive(message = "Este campo tiene que ser positivo")
    private Double priceTotal;


}

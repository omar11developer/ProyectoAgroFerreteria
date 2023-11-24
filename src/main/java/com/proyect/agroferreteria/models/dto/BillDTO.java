package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Client;
import com.proyect.agroferreteria.models.entity.ItemBill;
import com.proyect.agroferreteria.models.entity.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id_Factura;
    private String description;
    private String observation;
    private String date_create;
    @JsonIgnoreProperties({"hibernateLazyInitializer","bill"})
    private Client client;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "itemBills"})
    private Set<ItemBill> itemBills = new HashSet<>();
}

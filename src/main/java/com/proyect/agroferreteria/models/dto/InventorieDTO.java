package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Product;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventorieDTO {
    private Long id_Inventario;

    @Temporal(TemporalType.DATE)
    @NotEmpty(message = "Debe ingresar la fecha")
    private Date fecha_de_ingreso;

    @NotEmpty(message = "Debe ingresar el precio de venta")
    @Positive(message = "Debe ingresar numeros positivos")
    private Double precio_de_venta;

    @NotEmpty(message = "Debe ingresar cantidad en existencia")
    @Positive(message = "Debe de ser numero mayor a 0")
    private Integer stock;

    @JsonIgnoreProperties({"hibernateLazyInitializer","Inventories"})
    private Product product;

}

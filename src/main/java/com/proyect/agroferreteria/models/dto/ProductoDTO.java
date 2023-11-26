package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.models.entity.Supplier;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id_Producto;

    @NotNull
    @NotEmpty(message = "Debe ingresar un valor")
    @Size(min = 0, max = 80)
    private String name;

    @NotNull
    @Positive(message = "El precio no pueder ser negativo")
    @Min(0)
    private Double unit_Price;


    private String unit_Weight;

    @NotNull
    @Min(0)
    @Column(name = "sale_price")
    private Double sale_price;

    @NotNull
    @Min(value = 1, message = "El stok debe de ser al menos de 1")
    @Positive(message = "Este campo debe ser positivo")
    private Integer stock;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer","products"})
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer","city","address","phone","products"})
    private Supplier supplier;
}

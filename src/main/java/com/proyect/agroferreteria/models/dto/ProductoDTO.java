package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Category;
import com.proyect.agroferreteria.models.entity.Supplier;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    private Double unit_Price;


    private String unit_Weight;

    private Double sale_price;

    private Integer stock;

    @NotNull
    @JsonIgnoreProperties({"hibernateLazyInitializer","products"})
    private Category category;

    @JsonIgnoreProperties({"hibernateLazyInitializer","city","address","phone","products"})
    private Supplier supplier;
}

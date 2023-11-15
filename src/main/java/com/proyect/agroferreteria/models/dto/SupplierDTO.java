package com.proyect.agroferreteria.models.dto;

import com.proyect.agroferreteria.models.entity.Product;
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
public class SupplierDTO {
    private Long id_Supplier;
    @NotNull
    @NotEmpty(message = "Debe ingresar el nombre del proveedor")
    @Size(min = 0, max = 80)
    private String name;
    @Size(min = 0, max = 80)
    private String city;
    @Size(min = 0, max = 80)
    private String address;
    @NotNull
    @NotEmpty(message = "Debe ingresar el numero de telefono")
    @Positive(message = "No se permite numeros negativos")
    private Integer phone;



}

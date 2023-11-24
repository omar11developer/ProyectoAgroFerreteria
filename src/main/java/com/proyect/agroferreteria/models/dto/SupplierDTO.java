package com.proyect.agroferreteria.models.dto;

import com.proyect.agroferreteria.models.entity.Product;
import jakarta.validation.constraints.*;
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

    @NotEmpty(message = "El campo no debe ir vacio")
    @Size(min = 8, max = 8, message = "El minimo y maximo es de 8 numeros")
    private String phone;


}

package com.proyect.agroferreteria.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.proyect.agroferreteria.models.entity.Bill;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDTO {

    private Long id_Client;
    @NotEmpty(message = "El campo nombre no puede quedar vacio")
    @Size(min = 3, max = 50, message = "Debe contener al menos 3 letras")
    private String name;
    @NotEmpty(message = "El campo apellido no puede quedar vacio")
    @Size(min = 3, max = 50, message = "Debe contener al menos 3 letras")
    private String last_name;
    @NotEmpty(message = "El campo identificación no puede quedar vacio")
    @Size(min =10, max = 10, message = "Debe ")
    private String identification;

    private String adress;
    @NotEmpty(message = "El campo nombre no puede quedar vacio")
    @Email(message = "Por favor ingresa un correo valido!")
    private String email;
    @NotEmpty(message = "El campo nombre no puede quedar vacio")
    private String phone;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Bill> bill = new HashSet<>();

}

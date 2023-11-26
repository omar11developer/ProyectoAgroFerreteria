package com.proyect.agroferreteria.models.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.sound.midi.Soundbank;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentMethod {
    @NotEmpty(message = "Este campo no puede quedar vacio")
    private String namePaymentMethod;



}

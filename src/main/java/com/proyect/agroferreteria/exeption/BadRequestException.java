package com.proyect.agroferreteria.exeption;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;


public class BadRequestException extends RuntimeException {

   BadRequestException(String messague){super(messague);}



}

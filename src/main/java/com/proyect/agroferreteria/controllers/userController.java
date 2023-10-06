package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Inventories;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.repository.IInventories;
import com.proyect.agroferreteria.services.contracts.IInventoriesService;
import com.proyect.agroferreteria.services.contracts.ITypeProductService;
import com.proyect.agroferreteria.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class userController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/formUser")
    public String formSesion(Map<String, Object> model){
        model.put("titulo", "Sesion");
        model.put("encabezado","Iniciar Sesion");
        return "formUser";
    }





}

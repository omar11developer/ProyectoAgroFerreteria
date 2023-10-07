package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller// coment 1
@SessionAttributes("users")
public class userController {
    @Autowired
    private IUserService userService;

    @GetMapping(value = "/form")
    public String formSesion(Map<String, Object> model){
        Users users = new Users();
        model.put("user", users);
        model.put("titulo", "Sesion");
        model.put("encabezado","Iniciar Sesion");
        return "formuser";
    }


}

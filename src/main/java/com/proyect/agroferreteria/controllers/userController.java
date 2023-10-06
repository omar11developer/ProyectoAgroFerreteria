package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class userController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
   public List<Users> userAll(){
      return userService.findAll();
   }



}

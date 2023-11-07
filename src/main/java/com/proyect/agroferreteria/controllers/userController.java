package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Rols;
import com.proyect.agroferreteria.models.entity.UserRols;
import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Deprecated
@ConditionalOnProperty(prefix = "app", name = "controller.enable-dto", havingValue = "false")
@RestController
@RequestMapping("/login")
public class userController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public List<Users> getUserName(){
        return userService.AllUsers();
    }

    @GetMapping("/{username}")
    public Users getUsersName(@PathVariable("username") String username)
    {return userService.getByUserName(username);}

   @PostMapping("/users")
    public Users saveUser(@RequestBody Users user) throws Exception{
       List<UserRols> Usersrols = new ArrayList<>();
       Rols rol = new Rols();
       rol.setRolId(2L);
       rol.setNameRol("employe");

       UserRols userRol = new UserRols();
       userRol.setUsers(user);
       userRol.setRol(rol);
        Usersrols.add(userRol);
       return userService.saveUser(user,Usersrols);

   }
   @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId)
   {userService.deleteUser(userId);}


}

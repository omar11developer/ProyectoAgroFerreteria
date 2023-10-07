package com.proyect.agroferreteria.controllers;

import com.proyect.agroferreteria.models.entity.Rols;
import com.proyect.agroferreteria.models.entity.UserRols;
import com.proyect.agroferreteria.models.entity.Users;
import com.proyect.agroferreteria.services.contracts.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private IUserService userService;

   @PostMapping("/")
    public Users saveUser(@RequestBody Users user) throws Exception{
       Set<UserRols> rols = new HashSet<>();
       Rols rol = new Rols();
       rol.setRolId(2L);
       rol.setNameRol("employe");

       UserRols userRol = new UserRols();
       userRol.setUsers(user);
       userRol.setRol(rol);

       return userService.saveUser(user,rols);

   }

   @GetMapping("/")
    public List<Users> getUserName(){
       return userService.AllUsers();
   }
   @GetMapping("/{username}")
   public Users getUsersName(@PathVariable("username") String username){
       return userService.getByUserName(username);
   }
   @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
       userService.deleteUser(userId);
   }


}

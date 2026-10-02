package com.coresales.service.user.auth.controller;

import com.coresales.service.user.auth.model.User;
import com.coresales.service.user.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<User> listar(){
        return userService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> obtenerPorId(@PathVariable Long id){
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public User crear(@RequestBody User user){
        return userService.crear(user);
    }

    @PutMapping
    public User actualizar(@RequestBody User user){
        return userService.crear(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> eliminar(Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
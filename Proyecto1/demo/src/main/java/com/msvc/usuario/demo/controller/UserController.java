package com.msvc.usuario.demo.controller;

import com.msvc.usuario.demo.entity.User;
import com.msvc.usuario.demo.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User userRequest){
        User user = userService.saveUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    int cantidadIntentos = 1;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name= "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId ){
        log.info("Listar un solo usuario: UsuarioController");
        log.info("Cantidad de reintentos: " + cantidadIntentos);
        cantidadIntentos++;
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception){
        log.info("El respaldo se ejecuta porque el servicio esta inactivo", exception.getMessage());
        User user = User.builder()
                .email("roo1@gmail.com")
                .name("root")
                .info("Este usuario se crea por defecto cuando el servidor se cae")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

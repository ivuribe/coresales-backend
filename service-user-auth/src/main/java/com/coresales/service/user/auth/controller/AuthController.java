package com.coresales.service.user.auth.controller;

import com.coresales.service.user.auth.config.JwtTokenUtil;
import com.coresales.service.user.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder,
                          JwtTokenUtil jwtTokenUtil){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request){
        System.out.println("Inicio del Controlador auth/login");

        String username = request.get("username");
        String password = request.get("password");

        return userService.findByUsername(username)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(user -> {
                    String token = jwtTokenUtil.generateToken(username);
                    return ResponseEntity.ok(Map.of(
                            "token", token,
                            "expiresAt", jwtTokenUtil.getExpirationInstant(token).toString()
                    ));
                })
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Credenciales invalidas")));
    }
}
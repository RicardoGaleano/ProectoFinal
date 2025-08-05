package com.proyectoRichie.forohub.controller;

import com.proyectoRichie.forohub.dto.AuthDto;
import com.proyectoRichie.forohub.dto.TokenDto;
import com.proyectoRichie.forohub.entity.Usuario;
import com.proyectoRichie.forohub.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody AuthDto authDto) {
        return ResponseEntity.ok(authService.authenticateUser(
                authDto.email(),
                authDto.contrasena()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(authService.registerUser(usuario));
    }
}
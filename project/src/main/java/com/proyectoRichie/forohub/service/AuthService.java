package com.proyectoRichie.forohub.service;

import com.proyectoRichie.forohub.dto.TokenDto;
import com.proyectoRichie.forohub.entity.Usuario;
import com.proyectoRichie.forohub.repository.UsuarioRepository;
import com.proyectoRichie.forohub.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UsuarioRepository usuarioRepository;

    public TokenDto authenticateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new TokenDto(tokenProvider.generateToken(authentication));
    }

    public Usuario registerUser(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email ya registrado");
        }
        return usuarioRepository.save(usuario);
    }
}
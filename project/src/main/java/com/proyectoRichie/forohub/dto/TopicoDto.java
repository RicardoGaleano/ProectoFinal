package com.proyectoRichie.forohub.dto;

import com.proyectoRichie.forohub.entity.Topico.Curso;
import com.proyectoRichie.forohub.entity.Topico.StatusTopico;

import java.time.LocalDateTime;

public record TopicoDto(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        Curso curso
) {}
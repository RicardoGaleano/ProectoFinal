package com.proyectoRichie.forohub.service;

import com.proyectoRichie.forohub.dto.TopicoDto;
import com.proyectoRichie.forohub.entity.Topico;
import com.proyectoRichie.forohub.entity.Usuario;
import com.proyectoRichie.forohub.exceptions.DuplicateResourceException;
import com.proyectoRichie.forohub.exceptions.ResourceNotFoundException;
import com.proyectoRichie.forohub.repository.TopicoRepository;
import com.proyectoRichie.forohub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoDto saveTopico(Topico topico) {
        if (topicoRepository.existsByTitulo(topico.getTitulo())) {
            throw new DuplicateResourceException("El título ya existe");
        }

        if (topicoRepository.existsByMensaje(topico.getMensaje())) {
            throw new DuplicateResourceException("El mensaje ya existe");
        }

        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(Topico.StatusTopico.NO_RESPONDIDO);

        return toDto(topicoRepository.save(topico));
    }

    public List<TopicoDto> getTopicos() {
        return topicoRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public TopicoDto getTopicoById(Long id) {
        return topicoRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
    }

    public TopicoDto updateTopico(Long id, Topico topico) {
        return topicoRepository.findById(id)
                .map(existing -> {
                    existing.setTitulo(topico.getTitulo());
                    existing.setMensaje(topico.getMensaje());
                    existing.setCurso(topico.getCurso());
                    return toDto(topicoRepository.save(existing));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Tópico no encontrado"));
    }

    public void deleteTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }

    private TopicoDto toDto(Topico topico) {
        return new TopicoDto(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso()
        );
    }
}
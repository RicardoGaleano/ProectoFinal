package com.proyectoRichie.forohub.controller;

import com.proyectoRichie.forohub.dto.TopicoDto;
import com.proyectoRichie.forohub.entity.Topico;
import com.proyectoRichie.forohub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoDto> createTopico(
            @RequestBody @Valid Topico topico,
            UriComponentsBuilder uriBuilder) {

        TopicoDto saved = topicoService.saveTopico(topico);
        URI uri = uriBuilder.path("/api/topicos/{id}").buildAndExpand(saved.id()).toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDto>> getAllTopicos() {
        return ResponseEntity.ok(topicoService.getTopicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDto> getTopicoById(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.getTopicoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDto> updateTopico(
            @PathVariable Long id,
            @RequestBody @Valid Topico topico) {

        return ResponseEntity.ok(topicoService.updateTopico(id, topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopico(@PathVariable Long id) {
        topicoService.deleteTopico(id);
        return ResponseEntity.noContent().build();
    }
}
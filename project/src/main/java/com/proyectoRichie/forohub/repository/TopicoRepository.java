package com.proyectoRichie.forohub.repository;

import com.proyectoRichie.forohub.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitulo(String titulo);
    boolean existsByMensaje(String mensaje);
}
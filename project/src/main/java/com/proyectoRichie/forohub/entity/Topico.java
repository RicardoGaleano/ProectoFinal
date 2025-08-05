package com.proyectoRichie.forohub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Lob
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @Enumerated(EnumType.STRING)
    private Curso curso;

    public enum StatusTopico {
        NO_RESPONDIDO, NO_SOLUCIONADO, SOLUCIONADO, CERRADO
    }

    public enum Curso {
        FRONTEND, BACKEND, DEVOPS, DATA_SCIENCE
    }
}
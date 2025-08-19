package com.aluralatam_nils.Foro_hub.domain.Topico;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDTO {

    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private String autorNombre;
    private String curso;

    public TopicoDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensaje = topico.getMensaje();
        this.fechaCreacion = topico.getFechaCreacion();
        this.status = topico.getStatus();
        this.autorNombre = topico.getAutor().getNombre();
        this.curso = topico.getCurso();
    }
}
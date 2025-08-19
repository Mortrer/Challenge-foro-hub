package com.aluralatam_nils.Foro_hub.domain.Topico;

import com.aluralatam_nils.Foro_hub.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private String status; // "Abierto", "Cerrado", "Eliminado"

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String curso;


    // Constructor para DTO + Usuario

    public Topico(TopicoCreateDTO dto, Usuario autor) {
        this.titulo = dto.getTitulo();
        this.mensaje = dto.getMensaje();
        this.status = dto.getStatus();
        this.curso = dto.getCurso();
        this.autor = autor;
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizar(TopicoUpdateDTO dto) {
        this.titulo = dto.getTitulo();
        this.mensaje = dto.getMensaje();
        this.status = dto.getStatus();
        this.curso = dto.getCurso();
    }

    public void eliminarLogico() {
        this.status = "Eliminado";
    }
}




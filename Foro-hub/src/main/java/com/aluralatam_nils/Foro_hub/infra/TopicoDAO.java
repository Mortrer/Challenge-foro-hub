package com.aluralatam_nils.Foro_hub.infra;

import com.aluralatam_nils.Foro_hub.domain.Topico.Topico;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TopicoDAO {

    private final List<Topico> topicos = new ArrayList<>();
    private final AtomicLong contadorId = new AtomicLong(1);

    // Guardar un nuevo tópico
    public Topico guardar(Topico topico) {
        topico.setId(contadorId.getAndIncrement());
        topico.setFechaCreacion(LocalDateTime.now());
        topicos.add(topico);
        return topico;
    }

    // Listar todos los tópicos
    public List<Topico> listarTodos() {
        return new ArrayList<>(topicos);
    }

    // Buscar un tópico por ID
    public Optional<Topico> buscarPorId(Long id) {
        return topicos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }
}

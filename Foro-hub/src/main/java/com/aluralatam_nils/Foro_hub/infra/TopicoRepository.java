package com.aluralatam_nils.Foro_hub.infra;

import com.aluralatam_nils.Foro_hub.domain.Topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByAutorId(Long autorId);
}

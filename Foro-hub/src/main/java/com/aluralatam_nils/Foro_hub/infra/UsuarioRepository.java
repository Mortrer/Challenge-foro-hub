package com.aluralatam_nils.Foro_hub.infra;

import com.aluralatam_nils.Foro_hub.domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
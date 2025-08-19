package com.aluralatam_nils.Foro_hub.controller;

import com.aluralatam_nils.Foro_hub.domain.Rol.Rol;
import com.aluralatam_nils.Foro_hub.domain.Topico.Topico;
import com.aluralatam_nils.Foro_hub.domain.Topico.TopicoCreateDTO;
import com.aluralatam_nils.Foro_hub.domain.Topico.TopicoDTO;
import com.aluralatam_nils.Foro_hub.domain.Topico.TopicoUpdateDTO;
import com.aluralatam_nils.Foro_hub.domain.Usuario.Usuario;
import com.aluralatam_nils.Foro_hub.infra.TopicoRepository;
import com.aluralatam_nils.Foro_hub.infra.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<TopicoDTO> crear(@RequestBody TopicoCreateDTO dto, UriComponentsBuilder uriBuilder) {
        Usuario autor = usuarioRepository.findById(dto.getAutorId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id " + dto.getAutorId()));

        Topico topico = new Topico(dto, autor);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listarTodos() {
        List<TopicoDTO> dtos = topicoRepository.findAll().stream()
                .map(TopicoDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> listarPorId(@PathVariable Long id) {
        return topicoRepository.findById(id)
                .map(TopicoDTO::new)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<TopicoDTO>> listarPorAutor(@PathVariable Long autorId) {
        List<TopicoDTO> dtos = topicoRepository.findByAutorId(autorId).stream()
                .map(TopicoDTO::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping
    public ResponseEntity<TopicoDTO> actualizar(@RequestBody @Valid TopicoUpdateDTO dto) {
        return topicoRepository.findById(dto.getId())
                .map(topico -> {
                    topico.actualizar(dto);
                    topicoRepository.save(topico);
                    return ResponseEntity.ok(new TopicoDTO(topico));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE lógico/físico según rol
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id, @RequestParam Long usuarioId) {
        Optional<Topico> topicoOpt = topicoRepository.findById(id);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (topicoOpt.isEmpty() || usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuario = usuarioOpt.get();
        Topico topico = topicoOpt.get();

        if (usuario.getRol() == Rol.ADMIN) {
            topicoRepository.delete(topico); // eliminación física
        } else {
            topico.eliminarLogico();         // ahora usamos el método en Topico
            topicoRepository.save(topico);
        }

        return ResponseEntity.noContent().build();
    }
}

package com.aluralatam_nils.Foro_hub.controller;


import com.aluralatam_nils.Foro_hub.domain.Rol.Rol;
import com.aluralatam_nils.Foro_hub.domain.Usuario.Usuario;
import com.aluralatam_nils.Foro_hub.dto.LoginRequest;
import com.aluralatam_nils.Foro_hub.infra.UsuarioRepository;
import com.aluralatam_nils.Foro_hub.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtil jwtUtil;

    // Registro de usuario
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Usuario usuario) {
        // Hashear la contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), Rol.valueOf(usuario.getRol().name()));
        return ResponseEntity.ok(token);
    }
}
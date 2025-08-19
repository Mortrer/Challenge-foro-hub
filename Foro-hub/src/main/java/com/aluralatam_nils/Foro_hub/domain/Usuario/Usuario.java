package com.aluralatam_nils.Foro_hub.domain.Usuario;
import com.aluralatam_nils.Foro_hub.domain.Rol.Rol;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "usuarios") // <-- Apunta a la tabla correcta
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;

}


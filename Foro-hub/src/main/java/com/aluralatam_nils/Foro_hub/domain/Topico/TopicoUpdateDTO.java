package com.aluralatam_nils.Foro_hub.domain.Topico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicoUpdateDTO {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 3, max = 200)
    private String titulo;

    @NotNull
    private String mensaje;

    @NotNull
    private String status;

    @NotNull
    private String curso;
}
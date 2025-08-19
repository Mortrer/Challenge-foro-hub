package com.aluralatam_nils.Foro_hub.domain.Topico;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoCreateDTO {

    @NotNull
    private String titulo;

    @NotNull
    private String mensaje;

    @NotNull
    private String status;

    @NotNull
    private Long autorId;

    @NotNull
    private String curso;
}

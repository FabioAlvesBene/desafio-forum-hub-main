package com.fabioalvesbene.api.forumhub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DadosCursoInput(
        @NotBlank String nome
) {}

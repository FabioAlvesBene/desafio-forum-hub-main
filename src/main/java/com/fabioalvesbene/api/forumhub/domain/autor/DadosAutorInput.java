package com.fabioalvesbene.api.forumhub.domain.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAutorInput(
        @NotBlank String nome,
        @NotBlank @Email String email
) {}

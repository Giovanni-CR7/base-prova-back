package br.com.insper.base.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ItemRequest(
    @NotBlank(message = "Título obrigatório") @Size(max = 160) String titulo,
    @NotBlank(message = "Conteúdo obrigatório") @Size(max = 5000) String conteudo,
    @NotBlank(message = "Categoria obrigatória") @Size(max = 80) String categoria
) {}

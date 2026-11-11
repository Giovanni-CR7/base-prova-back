package br.com.insper.base.item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ItemRequest(
    @NotBlank(message = "Título obrigatório") @Size(max = 160) String titulo,
    @NotBlank(message = "Descricao obrigatoria") @Size(max = 5000) String descricao,
    @NotBlank(message = "Prioridade obrigatória") @Size(max = 80) String prioridade
) {}

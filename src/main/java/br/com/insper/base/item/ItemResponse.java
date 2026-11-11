package br.com.insper.base.item;

import java.time.LocalDateTime;

public record ItemResponse(Long id, String titulo, String conteudo, String categoria,
                           LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(item.getId(), item.getTitulo(), item.getConteudo(),
            item.getCategoria(), item.getCriadoEm(), item.getAtualizadoEm());
    }
}

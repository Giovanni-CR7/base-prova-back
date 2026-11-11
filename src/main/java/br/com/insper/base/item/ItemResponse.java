package br.com.insper.base.item;

import java.time.LocalDateTime;

public record ItemResponse(Long id, String titulo, String descricao,String Email, String prioridade,
                           LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
    public static ItemResponse from(Item item) {
        return new ItemResponse(item.getId(), item.getTitulo(), item.getDescricao(),item.getEmail(),
            item.getPrioridade(), item.getCriadoEm(), item.getAtualizadoEm());
    }
}

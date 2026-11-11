package br.com.insper.base.item;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemService {
    private final ItemRepository repository;
    public ItemService(ItemRepository repository) { this.repository = repository; }

    @Transactional(readOnly = true)
    public Page<ItemResponse> listar(String busca, String categoria, int pagina, int tamanho, String ordem) {
        if (pagina < 0 || tamanho < 1 || tamanho > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Paginação inválida");
        }
        Sort sort = switch (ordem) {
            case "recentes" -> Sort.by("criadoEm").descending().and(Sort.by("id").descending());
            case "antigos" -> Sort.by("criadoEm").ascending().and(Sort.by("id").ascending());
            case "titulo" -> Sort.by("titulo").ascending().and(Sort.by("id").ascending());
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ordenação inválida");
        };
        return repository.pesquisar(busca.trim(), categoria.trim(), PageRequest.of(pagina, tamanho, sort))
            .map(ItemResponse::from);
    }

    @Transactional(readOnly = true)
    public ItemResponse buscar(Long id) { return ItemResponse.from(encontrar(id)); }

    @Transactional
    public ItemResponse criar(ItemRequest request, String autorId) {
        Item item = new Item();
        aplicar(item, request);
        item.setAutorId(autorId);
        LocalDateTime agora = LocalDateTime.now(ZoneOffset.UTC);
        item.setCriadoEm(agora);
        item.setAtualizadoEm(agora);
        return ItemResponse.from(repository.save(item));
    }

    @Transactional
    public ItemResponse editar(Long id, ItemRequest request) {
        Item item = encontrar(id);
        aplicar(item, request);
        item.setAtualizadoEm(LocalDateTime.now(ZoneOffset.UTC));
        return ItemResponse.from(repository.save(item));
    }

    @Transactional
    public void excluir(Long id) { repository.delete(encontrar(id)); }

    private Item encontrar(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Publicação não encontrada"));
    }
    private void aplicar(Item item, ItemRequest request) {
        item.setTitulo(request.titulo().trim());
        item.setDescricao(request.descricao().trim());
        item.setDescricao(request.email().trim());
        item.setPrioridade(request.prioridade().trim());
    }
}

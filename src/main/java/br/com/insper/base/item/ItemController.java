package br.com.insper.base.item;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemController {
    private final ItemService service;
    public ItemController(ItemService service) { this.service = service; }

    @GetMapping
    public Page<ItemResponse> listar(
        @RequestParam(defaultValue = "") String busca,
        @RequestParam(defaultValue = "") String categoria,
        @RequestParam(defaultValue = "0") int pagina,
        @RequestParam(defaultValue = "8") int tamanho,
        @RequestParam(defaultValue = "recentes") String ordem) {
        return service.listar(busca, categoria, pagina, tamanho, ordem);
    }
    @GetMapping("/{id}")
    public ItemResponse buscar(@PathVariable Long id) { return service.buscar(id); }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse criar(@Valid @RequestBody ItemRequest request, @AuthenticationPrincipal Jwt jwt) {
        return service.criar(request, jwt.getSubject());
    }
    @PutMapping("/{id}")
    public ItemResponse editar(@PathVariable Long id, @Valid @RequestBody ItemRequest request) {
        return service.editar(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) { service.excluir(id); }
}

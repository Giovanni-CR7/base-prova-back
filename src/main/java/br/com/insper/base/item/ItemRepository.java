package br.com.insper.base.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("""
        select i from Item i
        where (:busca = '' or locate(lower(:busca), lower(i.titulo)) > 0
            or locate(lower(:busca), lower(i.conteudo)) > 0)
          and (:categoria = '' or lower(i.categoria) = lower(:categoria))
        """)
    Page<Item> pesquisar(@Param("busca") String busca, @Param("categoria") String categoria,
                         Pageable pageable);
}

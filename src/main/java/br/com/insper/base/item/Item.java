package br.com.insper.base.item;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "base_prova_itens")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 160)
    private String titulo;
    @Column(nullable = false, length = 5000)
    private String descricao;
    @Column(nullable = false, length = 80)
    private String prioridade;
    @Column(nullable = false, length = 255)
    private String autorId;
    @Column(nullable = false)
    private LocalDateTime criadoEm;
    @Column(nullable = false)
    private LocalDateTime atualizadoEm;

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPrioridade() { return prioridade; }
    public void setPrioridade(String prioridade) { this.prioridade = prioridade; }
    public String getAutorId() { return autorId; }
    public void setAutorId(String autorId) { this.autorId = autorId; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}

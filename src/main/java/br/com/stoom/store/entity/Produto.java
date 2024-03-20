package br.com.stoom.store.entity;

import br.com.stoom.store.DTO.MarcaDTO;
import br.com.stoom.store.DTO.ProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "ativo")
    private boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marca")
    private Marca marca;

    public static Produto buildProduto(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }
        return Produto.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .quantidade(dto.getQuantidade())
                .preco(dto.getPreco())
                .ativo(dto.isAtivo())
                .marca(Marca.buildMarca(dto.getMarca()))
                .categoria(Categoria.buildCategoria(dto.getCategoria()))
                .build();
    }


}
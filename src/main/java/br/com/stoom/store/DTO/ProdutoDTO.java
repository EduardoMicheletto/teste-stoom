package br.com.stoom.store.DTO;

import br.com.stoom.store.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class ProdutoDTO {
    private Long id;

    private String descricao;

    private int quantidade;

    private BigDecimal preco;

    private boolean ativo;

    private CategoriaDTO categoria;

    private MarcaDTO marca;


    public static ProdutoDTO buildDTO(Produto produto) {
        if (produto == null) {
            return null;
        }
        CategoriaDTO categoriaDTO = produto.getCategoria() != null ? CategoriaDTO.buildDTO(produto.getCategoria()) : null;
        MarcaDTO marcaDTO = produto.getMarca() != null ? MarcaDTO.buildDTO(produto.getMarca()) : null;
        return ProdutoDTO.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .quantidade(produto.getQuantidade())
                .preco(produto.getPreco())
                .ativo(produto.isAtivo())
                .categoria(categoriaDTO)
                .marca(marcaDTO)
                .build();
    }


}
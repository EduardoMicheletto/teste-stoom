package br.com.stoom.store.DTO;


import br.com.stoom.store.entity.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class CategoriaDTO {
    private Long id;

    private String descricao;

    private boolean ativo;

    private List<ProdutoDTO> produtos;

    public static CategoriaDTO buildDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return CategoriaDTO.builder()
                .id(categoria.getId())
                .descricao(categoria.getDescricao())
                .ativo(categoria.isAtivo())
                .build();
    }



}

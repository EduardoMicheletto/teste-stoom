package br.com.stoom.store.entity;


import br.com.stoom.store.DTO.CategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "categoria_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private boolean ativo;

    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<Produto> produtos;

    public static Categoria buildCategoria(CategoriaDTO dto) {
        if (dto == null) {
            return null;
        }
        return Categoria.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .ativo(dto.isAtivo())
                .build();
    }

}

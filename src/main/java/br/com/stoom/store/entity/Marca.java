package br.com.stoom.store.entity;

import br.com.stoom.store.DTO.MarcaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "marca_sequence")
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ativo")
    private boolean ativo;

    @OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
    private List<Produto> produtos;

    public static Marca buildMarca(MarcaDTO dto) {
        if (dto == null) {
            return null;
        }
        return Marca.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .ativo(dto.isAtivo())
                .build();
    }

}

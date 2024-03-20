package br.com.stoom.store.DTO;

import br.com.stoom.store.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor @Builder @Data @NoArgsConstructor
public class MarcaDTO {

    private Long id;

    private String nome;

    private boolean ativo;

    private List<ProdutoDTO> produtos;

    public static MarcaDTO buildDTO(Marca marca) {
        if (marca == null) {
            return null;
        }
        // Calcular quantidade de produtos associados (opcional)
        return MarcaDTO.builder()
                .id(marca.getId())
                .nome(marca.getNome())
                .ativo(marca.isAtivo())
                .build();
    }

}

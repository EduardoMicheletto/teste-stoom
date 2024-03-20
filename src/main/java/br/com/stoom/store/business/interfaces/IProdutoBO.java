package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.DTO.ProdutoDTO;
import br.com.stoom.store.entity.Produto;

import java.util.List;

public interface IProdutoBO extends ICrudBO<ProdutoDTO>{

    List<ProdutoDTO> findAllByCategoria(Long idCategoria);

    List<ProdutoDTO> findAllByMarca(Long idMarca);

    void deletarPorMarca(Long idMarca);

    void deletarPorCategoria(Long idCategoria);

}

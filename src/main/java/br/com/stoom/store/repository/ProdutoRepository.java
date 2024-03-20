package br.com.stoom.store.repository;

import br.com.stoom.store.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT  p FROM Produto p WHERE p.marca.ativo is true and p.categoria.ativo is true and p.ativo is true")
    public List<Produto> findAllByAtivoIsTrue();
    @Query("SELECT p FROM Produto p WHERE p.marca.ativo is false or p.categoria.ativo is false or p.ativo is false")
    public List<Produto> findAllByAtivoIsFalse();

    @Query("Select p from Produto p WHERE p.categoria.id = :idCategoria and p.ativo is true ")
    public List<Produto> findAllByCategoria(Long idCategoria);

    @Modifying
    @Transactional
    @Query("Select p from Produto p WHERE p.marca.id = :idMarca and p.ativo is true ")
    public List<Produto> findAllByMarca(Long idMarca);

    @Modifying
    @Transactional
    @Query("UPDATE Produto p SET p.ativo = :status WHERE p.id = :id")
    public void alterarStatus(Long id, boolean status);


    @Query("DELETE Produto p  WHERE p.marca.id = :id")
    public void deletarPorIdMarca(Long id);

    @Query("DELETE Produto p  WHERE p.categoria.id = :id")
    public void deletarPorIdCategoria(Long id);

}
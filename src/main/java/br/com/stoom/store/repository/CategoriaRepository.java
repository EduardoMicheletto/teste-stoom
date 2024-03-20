package br.com.stoom.store.repository;

import br.com.stoom.store.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByAtivoIsTrue();

    List<Categoria> findAllByAtivoIsFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.ativo = :status WHERE c.id = :id")
    public void alterarStatus(Long id, boolean status);


}

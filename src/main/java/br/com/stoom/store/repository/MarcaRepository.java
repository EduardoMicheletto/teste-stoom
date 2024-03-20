package br.com.stoom.store.repository;

import br.com.stoom.store.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    public List<Marca> findAllByAtivoIsTrue();
    public List<Marca> findAllByAtivoIsFalse();

    @Modifying
    @Transactional
    @Query("UPDATE Marca m SET m.ativo = :status WHERE m.id = :id")
    public void alterarStatus(Long id, boolean status);

}

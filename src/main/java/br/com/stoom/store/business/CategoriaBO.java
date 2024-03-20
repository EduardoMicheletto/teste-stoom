package br.com.stoom.store.business;

import br.com.stoom.store.DTO.CategoriaDTO;
import br.com.stoom.store.business.interfaces.ICategoriaBO;
import br.com.stoom.store.entity.Categoria;
import br.com.stoom.store.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaBO implements ICategoriaBO {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<CategoriaDTO> findAll() {
        return repository.findAllByAtivoIsTrue().stream().map(CategoriaDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public List<CategoriaDTO> findAllInativos() {
        return repository.findAllByAtivoIsFalse().stream().map(CategoriaDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO findById(Long id) {
        return CategoriaDTO.buildDTO(repository.findById(id).orElse(null));
    }

    @Override
    public CategoriaDTO criar(CategoriaDTO categoria) {
        return CategoriaDTO.buildDTO(repository.save(Categoria.buildCategoria(categoria)));
    }

    @Override
    public CategoriaDTO alterar(CategoriaDTO categoria) throws Exception {
        if(!repository.existsById(categoria.getId())){
            throw new Exception("Id não encontrado.");
        }
        return CategoriaDTO.buildDTO(repository.save(Categoria.buildCategoria(categoria)));
    }

    @Override
    public void deletar(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        repository.deleteById(id);
    }

    @Override
    public void inativar(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
    repository.alterarStatus(id, false);
    }

    @Override
    public void ativar(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        repository.alterarStatus(id, true);
    }
}

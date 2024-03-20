package br.com.stoom.store.business;

import br.com.stoom.store.DTO.MarcaDTO;
import br.com.stoom.store.DTO.ProdutoDTO;
import br.com.stoom.store.business.interfaces.IMarcaBO;
import br.com.stoom.store.entity.Marca;
import br.com.stoom.store.repository.MarcaRepository;
import br.com.stoom.store.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarcaBO implements IMarcaBO {

    @Autowired
    private MarcaRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<MarcaDTO> findAll() {
        return repository.findAllByAtivoIsTrue().stream().map(MarcaDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public List<MarcaDTO> findAllInativos() {
        return repository.findAllByAtivoIsFalse().stream().map(MarcaDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public MarcaDTO findById(Long id) {
        return MarcaDTO.buildDTO(repository.findById(id).orElse(null));
    }

    @Override
    public MarcaDTO criar(MarcaDTO marca) {
        return MarcaDTO.buildDTO(repository.save(Marca.buildMarca(marca)));
    }

    @Override
    public MarcaDTO alterar(MarcaDTO marca) throws Exception {
        if(!repository.existsById(marca.getId())){
            throw new Exception("Id não encontrado.");
        }
        return  MarcaDTO.buildDTO(repository.save(Marca.buildMarca(marca)));
    }

    @Override
    public void deletar(Long id) throws Exception {
        if(!repository.existsById(id)){
            throw new Exception("Id não encontrado.");
        }
        produtoRepository.deletarPorIdMarca(id);
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

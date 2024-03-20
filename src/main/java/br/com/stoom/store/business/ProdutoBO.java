package br.com.stoom.store.business;

import br.com.stoom.store.DTO.ProdutoDTO;
import br.com.stoom.store.business.interfaces.IProdutoBO;
import br.com.stoom.store.entity.Produto;
import br.com.stoom.store.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoBO implements IProdutoBO {

    @Autowired
    private ProdutoRepository repository;


    @Autowired
    private MarcaBO marcaService;

    @Autowired
    private CategoriaBO categoriaService;

    @Override
    public List<ProdutoDTO> findAll(){
        return repository.findAllByAtivoIsTrue().stream().map(ProdutoDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDTO> findAllInativos() {
        return repository.findAllByAtivoIsFalse().stream().map(ProdutoDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO findById(Long id) {
        return ProdutoDTO.buildDTO(repository.findById(id).orElse(null));
    }

    @Override
    public List<ProdutoDTO> findAllByCategoria(Long idCategoria) {
        return repository.findAllByCategoria(idCategoria).stream().map(ProdutoDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDTO> findAllByMarca(Long idMarca) {
        return repository.findAllByMarca(idMarca).stream().map(ProdutoDTO::buildDTO).collect(Collectors.toList());
    }

    @Override
    public ProdutoDTO criar(ProdutoDTO dto) throws Exception {
        if((dto.getCategoria() == null || dto.getCategoria().getId() == null) || (dto.getMarca() == null || dto.getMarca().getId() == null)){
            throw new Exception("Categoria/Marca não declarados.");
        }
        if(categoriaService.findById(dto.getCategoria().getId()) == null || marcaService.findById(dto.getMarca().getId()) == null){
            throw new Exception("Categoria/Marca não localizados.");
        }

        return ProdutoDTO.buildDTO(repository.save(Produto.buildProduto(dto)));
    }

    @Override
    public ProdutoDTO alterar(ProdutoDTO dto) throws Exception {
        if(!repository.existsById(dto.getId())){
            throw new Exception("Id não encontrado.");
        }

        if((dto.getCategoria() == null || dto.getCategoria().getId() == null) || (dto.getMarca() == null || dto.getMarca().getId() == null)){
            throw new Exception("Categoria/Marca não declarados.");
        }
        if(categoriaService.findById(dto.getCategoria().getId()) == null || marcaService.findById(dto.getMarca().getId()) == null){
            throw new Exception("Categoria/Marca não localizados.");
        }

        return ProdutoDTO.buildDTO(repository.save(Produto.buildProduto(dto)));
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

    @Override
    public void deletarPorMarca(Long idMarca) {
        repository.deletarPorIdMarca(idMarca);
    }

    @Override
    public void deletarPorCategoria(Long idCategoria) {
        repository.deletarPorIdCategoria(idCategoria);
    }

}

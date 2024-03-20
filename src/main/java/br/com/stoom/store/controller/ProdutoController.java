package br.com.stoom.store.controller;

import br.com.stoom.store.DTO.ProdutoDTO;
import br.com.stoom.store.business.ProdutoBO;
import br.com.stoom.store.business.interfaces.ICrudBO;
import br.com.stoom.store.controller.abstracts.CrudController;
import br.com.stoom.store.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController extends CrudController<ProdutoDTO> {

    @Autowired
    private ProdutoBO productService;

    @Override
    public ICrudBO<ProdutoDTO> getService() {
        return productService;
    }

    @GetMapping(value = "/categoria/{idCategoria}")
    public ResponseEntity<List<ProdutoDTO>> findAllByCategoria(@PathVariable Long idCategoria) {
        List<ProdutoDTO> p = productService.findAllByCategoria(idCategoria);
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/marca/{idMarca}")
    public ResponseEntity<List<ProdutoDTO>> findAllByMarca(@PathVariable Long idMarca) {
        List<ProdutoDTO> p = productService.findAllByMarca(idMarca);
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

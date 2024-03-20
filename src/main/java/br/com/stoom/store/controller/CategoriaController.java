package br.com.stoom.store.controller;

import br.com.stoom.store.DTO.CategoriaDTO;
import br.com.stoom.store.business.CategoriaBO;
import br.com.stoom.store.business.interfaces.ICrudBO;
import br.com.stoom.store.controller.abstracts.CrudController;
import br.com.stoom.store.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController extends CrudController<CategoriaDTO> {

    @Autowired
    private CategoriaBO categoriaBO;


    @Override
    public ICrudBO<CategoriaDTO> getService() {
        return categoriaBO;
    }
}

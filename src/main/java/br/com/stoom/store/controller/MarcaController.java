package br.com.stoom.store.controller;

import br.com.stoom.store.DTO.MarcaDTO;
import br.com.stoom.store.business.MarcaBO;
import br.com.stoom.store.business.interfaces.ICrudBO;
import br.com.stoom.store.controller.abstracts.CrudController;
import br.com.stoom.store.entity.Marca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/marca")
public class MarcaController extends CrudController<MarcaDTO> {

    @Autowired
    private MarcaBO marcaService;

    @Override
    public ICrudBO<MarcaDTO> getService() {
        return marcaService;
    }
}


package br.com.stoom.store.controller.abstracts;

import br.com.stoom.store.business.interfaces.ICrudBO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<T> {

    public abstract ICrudBO<T> getService();

    @GetMapping(value = "/")
    public ResponseEntity<List<T>> findAll() {
        List<T> p = getService().findAll();
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/inativos")
    public ResponseEntity<List<T>> findAllInativos() {
        List<T> t = getService().findAllInativos();
        if(!t.isEmpty())
            return new ResponseEntity<>(t, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        T t = getService().findById(id);
        if(t != null)
            return new ResponseEntity<>(t, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/")
    public ResponseEntity<?> criar(@RequestBody T t) {
        if(t == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>( getService().criar(t), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value = "/")
    public ResponseEntity<?> alterar(@RequestBody T t){
        if(t == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(getService().alterar(t), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            getService().deletar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/inativar/{id}")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            getService().inativar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping(value = "/ativar/{id}")
    public ResponseEntity<?> ativar(@PathVariable Long id) {
        try {
            getService().ativar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

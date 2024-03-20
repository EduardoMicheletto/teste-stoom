package br.com.stoom.store.business.interfaces;

import java.util.List;

public interface ICrudBO<T> {

    List<T> findAll();

    List<T> findAllInativos();

    T findById(Long id);

     T criar(T t) throws Exception;

    T alterar(T t) throws Exception;

    void deletar(Long id) throws Exception;

    void ativar(Long id) throws Exception;

    void inativar(Long id) throws Exception;
}

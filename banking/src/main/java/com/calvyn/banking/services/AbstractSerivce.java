package com.calvyn.banking.services;

import java.util.List;

public interface AbstractSerivce<T> { //on communique avec le dto ici

    Integer save(T dto);

    List<T> findAll();

    T findById(Integer id);

    void delete(Integer id);
}

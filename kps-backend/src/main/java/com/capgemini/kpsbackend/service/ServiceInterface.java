package com.capgemini.kpsbackend.service;

import java.util.List;

public interface ServiceInterface<T,K> {
    Object add(T object);
    List<K> getAll();
    Object getById(int id);
    Object update(K object);
    void deleteById(int id);
}

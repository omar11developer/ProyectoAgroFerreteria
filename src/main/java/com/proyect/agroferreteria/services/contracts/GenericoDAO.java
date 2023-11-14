package com.proyect.agroferreteria.services.contracts;

import java.util.Optional;

public interface GenericoDAO <E>{
    Optional<E> findById(Long id);
    E save(E entidad);
    Iterable<E> findAll();
    void deleteById(Long id);

    void delete(E entity);
}

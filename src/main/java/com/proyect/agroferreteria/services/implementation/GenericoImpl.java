package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.services.contracts.GenericoDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenericoImpl <E, R extends CrudRepository<E, Long>> implements GenericoDAO<E> {

    protected final R repository;

    public GenericoImpl(R repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<E> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public E save(E entidad) {
        return repository.save(entidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

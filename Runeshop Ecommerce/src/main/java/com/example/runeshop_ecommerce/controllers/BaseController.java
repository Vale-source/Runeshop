package com.example.runeshop_ecommerce.controllers;

import com.example.runeshop_ecommerce.entities.Base;
import com.example.runeshop_ecommerce.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<E extends Base, ID extends Serializable> {

    protected BaseService<E, ID> service;

    public BaseController(BaseService<E, ID> service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<E>> findAll() throws Exception {
        List<E> entities = service.findAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public Optional<E> findByID(@PathVariable ID id) throws Exception {
        return service.findByID(id);
    }

    @PostMapping()
    public ResponseEntity<E> create(@RequestBody E entity) throws Exception {
        E newEntity = service.create(entity);
        return ResponseEntity.ok(newEntity);
    }

    @PutMapping()
    public ResponseEntity<E> update(@RequestBody E entity) throws Exception {
        E updateEntity = service.update(entity);
        return ResponseEntity.ok(updateEntity);
    }

    //Borrado Logico
    @PutMapping("/{id}")
    public void delete(@PathVariable ID id) throws Exception {
        service.logicDeletion(id);
    }

}

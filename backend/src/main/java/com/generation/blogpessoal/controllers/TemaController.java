package com.generation.blogpessoal.controllers;

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repositories.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "", allowedHeaders = "")
public class TemaController {

    @Autowired
    TemaRepository repository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Long id) {
        return repository.findById(id).map(res -> ResponseEntity.ok(res))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/descricoes/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao) {
        return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
    }

    @PutMapping
    public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema) {
        return repository.findById(tema.getId())
                .map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(tema)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        Optional<Tema> tema = repository.findById(id);

        if (tema.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}
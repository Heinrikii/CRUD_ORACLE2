package com.crud.oracle.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.crud.oracle.model.Pessoa;
import com.crud.oracle.repository.PessoaRepository;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {


    private final PessoaRepository repository;

    public PessoaController(PessoaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Pessoa> findAll(){
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findByID(@PathVariable long id){
        return repository.findById(id)
        .map(record -> {
            return ResponseEntity.ok().body(record);
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa save(@RequestBody Pessoa pessoa){
        return repository.save(pessoa);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pessoa> update(@PathVariable Long id, @RequestBody Pessoa pessoa){
        return repository.findById(id)
                .map(record -> {
            record.setNome(pessoa.getNome());
            record.setSobreNome(pessoa.getSobreNome());
            record.setTelefone(pessoa.getTelefone());
            record.setEmail(pessoa.getEmail());
            Pessoa updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
                    
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        return repository.findById(id)
            .map(record -> {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            }).orElse(ResponseEntity.notFound().build());
    }
}

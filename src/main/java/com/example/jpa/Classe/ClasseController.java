package com.example.jpa.Classe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/classe")
public class ClasseController {

    @Autowired
    private ClasseRepository repository;

    @GetMapping()
    List<Classe> getAllClasses(){
        return this.repository.findAll() ;
    }

    @GetMapping("/{id}")
    Optional<Classe> getClasseByID(@PathVariable("id") Long ID){
        return this.repository.findById(ID);
    }

    @PostMapping
    Classe addClasse(@RequestBody Classe classe){
        return this.repository.save(classe);
    }

}

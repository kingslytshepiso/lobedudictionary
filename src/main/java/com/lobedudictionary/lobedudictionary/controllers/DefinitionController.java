package com.lobedudictionary.lobedudictionary.controllers;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lobedudictionary.lobedudictionary.data.DefinitionRepository;
import com.lobedudictionary.lobedudictionary.models.Definition;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/definitions")
public class DefinitionController {
    @Autowired
    private DefinitionRepository repo;

    /**
     * A method or an http endpoint that returns a definition specified in as a path
     * variable
     * in the address
     * 
     * @param def a definition value to search through the database
     * @return a definition that matches the value specified in the path variable
     */
    @GetMapping("/{definition}")
    public ResponseEntity<Definition> getDefinition(@PathVariable("definition") String def) {
        Optional<Definition> definition = repo.findByValue(def);
        if (definition.isPresent()) {
            return ResponseEntity.ok(definition.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addDefinition(@RequestBody Definition model, UriComponentsBuilder ucb) {
        var definitionExists = repo.existsByValue(model.getValue());
        if (!definitionExists) {
            Definition newDefinition = repo.save(model);
            URI location = ucb.path("/definitions/{definition}").buildAndExpand(newDefinition.getValue()).toUri();
            return ResponseEntity.created(location).build();

        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public void deleteDefinition(HttpServletResponse res, @RequestParam long id) {
        var definitionExists = repo.existsById(id);
        if (definitionExists) {
            repo.deleteById(id);
            res.setStatus(HttpServletResponse.SC_OK);
        } else
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @PutMapping
    public void updateDefinition(HttpServletResponse res, @RequestBody Definition model) {
        var definitionExists = repo.existsById(model.getId());
        if (definitionExists) {
            repo.save(model);
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/u")
    public void deleteUsage(HttpServletResponse res, @PathVariable("id") long id) {
        var definitionExists = repo.existsById(id);
        if (definitionExists) {
            Definition definition = repo.findById(id).get();
            definition.setUsage(null);
            repo.save(definition);
            res.setStatus(HttpServletResponse.SC_OK);
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

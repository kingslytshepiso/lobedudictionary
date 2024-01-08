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

import com.lobedudictionary.lobedudictionary.data.WordRepository;
import com.lobedudictionary.lobedudictionary.models.Word;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordRepository repo;

    @GetMapping("/{word}")
    public ResponseEntity<Word> getWord(HttpServletResponse res, @PathVariable("word") String word) {
        Optional<Word> foundWord = repo.findByValue(word);
        if (foundWord.isPresent()) {
            return ResponseEntity.ok(foundWord.get());
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addWord(@RequestBody Word model, UriComponentsBuilder ucb) {
        var wordExists = repo.existsByValue(model.getValue());
        if (!wordExists) {
            Word newWord = repo.save(model);
            URI wordLocation = ucb.path("words/{word}").buildAndExpand(newWord.getValue()).toUri();
            return ResponseEntity.created(wordLocation).build();

        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public void DeleteWord(HttpServletResponse res, @RequestParam("id") long id) {
        var wordExists = repo.existsById(id);
        if (wordExists) {
            repo.deleteById(id);
            res.setStatus(HttpServletResponse.SC_OK);

        } else
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @PutMapping
    public void updateWord(HttpServletResponse res, @RequestBody Word model) {
        var word = repo.findById(model.getId());
        if (word.isPresent()) {
            repo.save(model);
            res.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

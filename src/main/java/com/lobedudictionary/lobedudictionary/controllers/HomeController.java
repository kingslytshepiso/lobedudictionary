package com.lobedudictionary.lobedudictionary.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lobedudictionary.lobedudictionary.data.WordRepository;
import com.lobedudictionary.lobedudictionary.models.Word;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class HomeController {
    @Autowired
    private WordRepository wordRepo;

    @GetMapping({ "/", "/home" })
    public ResponseEntity<List<Word>> home() {
        return ResponseEntity.ok(wordRepo.findAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Word>> search(@RequestParam("keyword") String keyword) {
        var words = wordRepo.findByValueStartingWith(keyword);
        if (!words.get().isEmpty()) {
            return ResponseEntity.ok(words.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{keyword}")
    public Word getWord(HttpServletResponse res, @PathVariable("keyword") String keyword) {
        Optional<Word> returnWord = wordRepo.findByValue(keyword);
        if (returnWord.isPresent()) {
            res.setStatus(HttpServletResponse.SC_OK);
            return returnWord.get();
        } else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new Word();

        }
    }
}

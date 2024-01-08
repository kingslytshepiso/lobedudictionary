package com.lobedudictionary.lobedudictionary.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lobedudictionary.lobedudictionary.models.Word;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<List<Word>> findByValueStartingWith(String infix);

    Optional<Word> findByValue(String value);

    Boolean existsByValue(String value);
}

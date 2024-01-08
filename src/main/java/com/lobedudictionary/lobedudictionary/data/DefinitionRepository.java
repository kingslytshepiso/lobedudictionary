package com.lobedudictionary.lobedudictionary.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lobedudictionary.lobedudictionary.models.Definition;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Long> {
    Optional<Definition> findByValue(String value);

    Boolean existsByValue(String value);
}

package com.lobedudictionary.lobedudictionary.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TableGenerator;

@Entity
public class Word {
    @Id
    @TableGenerator(name = "wordGenerator", initialValue = 101)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "wordGenerator")
    private long id;
    private String value;
    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Definition> definitions;

    public Word() {

    }

    public Word(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addDefinition(Definition definition) {
        this.definitions.add(definition);
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
        if (!definitions.isEmpty()) {
            for (Definition def : this.definitions) {
                def.setWord(this);
                def.setUsage(def.getUsage());
            }
        }
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Word [id=" + id + ", value=" + value + ", definitions=" + definitions + "]";
    }

    public String getValue() {
        return value;
    }
}

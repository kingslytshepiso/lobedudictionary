package com.lobedudictionary.lobedudictionary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;

@Entity
public class DefinitionUsage {
    @Id
    @TableGenerator(name = "usageGenerator", initialValue = 10001)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "usageGenerator")
    private long id;
    private String value;
    @JsonIgnore
    @OneToOne(mappedBy = "")
    @MapsId
    private Definition definition;

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }

    public Definition getDefinition() {
        return definition;
    }

    public DefinitionUsage() {

    }

    public DefinitionUsage(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DefinitionUsage [id=" + id + ", value=" + value + "]";
    }

    public void setValue(String value) {
        this.value = value;
    }
}

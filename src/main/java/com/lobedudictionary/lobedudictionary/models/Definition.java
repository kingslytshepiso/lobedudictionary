package com.lobedudictionary.lobedudictionary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.TableGenerator;

@Entity
public class Definition {
	@Id
	@TableGenerator(name = "definitionGenerator", initialValue = 1001)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "definitionGenerator")
	private long id;
	private String value;
	@JsonIgnore
	@ManyToOne
	private Word word;
	@OneToOne(mappedBy = "definition", cascade = CascadeType.ALL, orphanRemoval = true)
	private DefinitionUsage usage;

	public DefinitionUsage getUsage() {
		return usage;
	}

	public Word getWord() {
		return word;
	}

	public Definition() {

	}

	public Definition(long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public void setUsage(DefinitionUsage usage) {
		this.usage = usage;
		if (usage != null) {
			this.usage.setDefinition(this);
		}
	}

	@Override
	public String toString() {
		return "Definition [id=" + id + ", value=" + value + "]";
	}
}

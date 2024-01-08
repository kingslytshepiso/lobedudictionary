package com.lobedudictionary.lobedudictionary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import com.lobedudictionary.lobedudictionary.models.Word;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LobedudictionaryApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void wordNotFoundTest() {
		ResponseEntity<Word> response = restTemplate
				.getForEntity("/words/thenotfoundword", Word.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}

package com.example.daarprojectcazessoumahoro.repository;

import java.util.List;
import java.util.Optional;

import com.example.daarprojectcazessoumahoro.entity.Document;

public interface DocumentService {
	Document save(Document book);

	    void delete(Document book);

	    Document findOne(String id);

	    Iterable<Document> findAll();

	   // Page<Document> findByAuthor(String author, PageRequest pageRequest);

	    List<Document> findByTitle(String title);

		Optional<Document> findOne(Integer id);
		
		

}

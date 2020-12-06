package com.example.daarprojectcazessoumahoro.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.daarprojectcazessoumahoro.entity.Document;

@Repository
public interface DocumentRepository extends ElasticsearchRepository<Document, Integer>{//CrudRepository<Document, Integer>{

	List<Document> findAllUserDetailsFromElastic();

	List<Document> findAllUserDataByNameFromElastic(String userName);

	List<Document> findAllUserDataByNameAndAddressFromElstic(String userName, String address);

	List<Document> findByTitle(String title);


	
}

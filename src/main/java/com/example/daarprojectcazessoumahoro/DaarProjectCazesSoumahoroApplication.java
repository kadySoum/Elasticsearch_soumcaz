package com.example.daarprojectcazessoumahoro;

import javax.annotation.Resource;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.daarprojectcazessoumahoro.entity.Document;
import com.example.daarprojectcazessoumahoro.repository.DocumentRepository;
import com.example.daarprojectcazessoumahoro.repository.DocumentStorageSercvice;
//@EnableJpaRepositories
@SpringBootApplication
public class DaarProjectCazesSoumahoroApplication implements CommandLineRunner {
	
	@Resource
	DocumentStorageSercvice storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(DaarProjectCazesSoumahoroApplication.class, args);
	}
	 @Bean
	    public CommandLineRunner run(DocumentRepository documentRepository) throws Exception {
	        return (String[] args) -> {
	        	Document doc1 = new Document("Pour la science!", "pdf",  new byte[] {});
	        	Document doc2 = new Document("DAARprojet", "md", new byte[] {});
	        	documentRepository.save(doc1);
	        	documentRepository.save(doc2);
	           // documentRepository.findAll().forEach(document -> System.out.println(document);
	        };
	    }

	  @Override
	  public void run(String... arg) throws Exception {
	    storageService.deleteAll();
	    storageService.init();
	  }

}

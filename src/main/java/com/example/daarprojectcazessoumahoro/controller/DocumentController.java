package com.example.daarprojectcazessoumahoro.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.daarprojectcazessoumahoro.entity.Document;
import com.example.daarprojectcazessoumahoro.messages.ResponseMessage;
import com.example.daarprojectcazessoumahoro.repository.DocumentStorageSercvice;
import com.example.daarprojectcazessoumahoro.service.DocumentServiceImpl;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "http://localhost:8080")/////////

public class DocumentController {
	
	@Autowired
	private DocumentServiceImpl documentService;

	@GetMapping(value ="/alldocument", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Document> getAllDocument(){
		return documentService.getAllDocumentInfo();
	}
	
	@GetMapping(value ="/alldocument/{docTitle}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Document> getDocumentByName(@PathVariable String documentName){
		return documentService.getDocumentDataByName(documentName);
	}
	@RequestMapping(value = { "/alldocument/{docType}" }, method = RequestMethod.GET)
	@GetMapping(value ="/alldocument/{docType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Document> getDocumentByType(@PathVariable String documentType){
		return documentService.getDocumentDataByName(documentType);
	}
	
	/*@GetMapping(value ="/alldocument/{documentName}/{documentType}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Document> getDocumentByNameAndAddress(@PathVariable String documentName, @PathVariable String documentType){
		return documentService.getDocumentDataByNameAndAddress(documentName, documentType);
	}*/
	
	@RequestMapping(value = "/", 
			  produces = "application/json", 
			  method=RequestMethod.GET)
	@GetMapping("/") //ok
	public String get(Model model) {
		List<Document> docs = documentService.getAllDocumentInfo();
		model.addAttribute("docs", docs);
		return "index"; // oh veut passer en mode no REST just controller et utiliser /template/document.html
	}
	
	@Autowired
	DocumentStorageSercvice storageService;

	  @PostMapping("/upload") //ok
	  public ResponseEntity<ResponseMessage> uploadFiles(@RequestParam("files") MultipartFile[] files) {
	    String message = "";
	    try {
	      List<String> fileNames = new ArrayList<>();

	      Arrays.asList(files).stream().forEach(file -> {
	        storageService.save(file);
	        fileNames.add(file.getOriginalFilename());
	      });

	      message = "Uploaded the files successfully: " + fileNames;
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      message = "Fail to upload files!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	
	
	@RequestMapping(value = { "/uploadFiles" }, method = RequestMethod.POST)
	@PostMapping(value ="/uploadFiles", produces = MediaType.APPLICATION_JSON_VALUE)
	public String uploadMultipleFiles(@RequestParam("files") MultipartFile[] files){
		for (MultipartFile file: files) {
			documentService.saveFile(file);
			//documentService.save(new Document(file.getName(), file.getContentType(), file.getBytes()));
		}
		return "redirect:/alldocument";
	}
	
	@GetMapping("/downloadFile/{fileId}") //ok
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId){
		Document doc = documentService.getFile(fileId).get();
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(doc.getDocType()))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
				.body(new ByteArrayResource(doc.getData()));
	}
	   
	/*@GetMapping("/final")
	public String trail() throws IOException
	{
		String filePath="C:\\Users\\w8ben.pdf";
		String encodedfile = null;
		
		File file = new File(filePath);
		try {
		    FileInputStream fileInputStreamReader = new FileInputStream(file);
		    byte[] bytes = new byte[(int) file.length()];
		    fileInputStreamReader.read(bytes);
		    encodedfile = new String(Base64.getEncoder().encodeToString(bytes));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		System.out.println(encodedfile);
		//creating index
		CreateIndexRequest request = new CreateIndexRequest("twitter");
		CreateIndexResponse createIndexResponse = client().indices().create(request, RequestOptions.DEFAULT);
	    System.out.print(createIndexResponse);
	    
		
	  
	    //mapping properties
	    PutMappingRequest request2 = new PutMappingRequest("twitter");
	    
	    request2.source(
		        "{\n" +
		        "  \"properties\": {\n" +
		        "    \"message\": {\n" +
		        "      \"type\": \"binary\"\n" +
		        "    }\n" +
		        "  }\n" +
		        "}", 
		        XContentType.JSON);
	    AcknowledgedResponse putMappingResponse = client().indices().putMapping(request2, RequestOptions.DEFAULT);
	   

		IndexRequest request3 = new IndexRequest("twitter","_doc","56"); 
		 request3.source("{\n" + "  \"message\": " +"\""+ encodedfile +"\""+ "}", XContentType.JSON);
		
		IndexResponse createIndexResponse1= client().index(request3, RequestOptions.DEFAULT);
	    
		return "done"; 
		
	}*/
}

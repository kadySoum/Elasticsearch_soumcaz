package com.example.daarprojectcazessoumahoro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.daarprojectcazessoumahoro.entity.Document;
import com.example.daarprojectcazessoumahoro.repository.DocumentRepository;
import com.example.daarprojectcazessoumahoro.repository.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService{

	@Autowired
	@Qualifier("documentRepository")
	private DocumentRepository dRepository;
	
	/*@Autowired
	//@Qualifier("documentService")
	private DocumentRepository documentRepository;
	*/
	public List<Document> getAllDocumentInfo() {
		
		return dRepository.findAllUserDetailsFromElastic();
	}

	public List<Document> getDocumentDataByName(String userName) {
		return dRepository.findAllUserDataByNameFromElastic(userName);
	}

	public List<Document> getDocumentDataByNameAndAddress(String userName, String address) {
		return dRepository.findAllUserDataByNameAndAddressFromElstic(userName,address);
	}

	@Override
	public Document save(Document doc) {
		// TODO Auto-generated method stub
		return dRepository.save(doc);
	}

	@Override
	public void delete(Document doc) {
		// TODO Auto-generated method stub
		dRepository.delete(doc);
	}


	@Override
	public Optional<Document> findOne(Integer id) {
		
		return dRepository.findById(id);
	}

	@Override
	public Iterable<Document> findAll() {
		// TODO Auto-generated method stub
		return dRepository.findAll();
	}

	@Override
	public List<Document> findByTitle(String title) {
		// TODO Auto-generated method stub
		return dRepository.findByTitle(title);
	}

	@Override
	public Document findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Document> getFile(Integer fileId) {
		return dRepository.findById(fileId);
	}

	public Document saveFile(MultipartFile file) {
		String docname = file.getOriginalFilename();
		try {
			Document document = new Document(docname,file.getContentType(),file.getBytes());
			return dRepository.save(document);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/*public Document store(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Document FileDB = new Document(fileName, file.getContentType(), file.getBytes());
	
		return userRepository.save(FileDB);
	  }
	
	  public Document getFile(String id) {
		return userRepository.findById(id).get();
	  }
	  
	  public Stream<Document> getAllFiles() {
		return userRepository.findAll().stream();
	  }
*/
}

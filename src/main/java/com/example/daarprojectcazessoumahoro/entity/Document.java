package com.example.daarprojectcazessoumahoro.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
//import javax.persistence.Table; //utile pour creer une table dans h2

@Entity
@org.springframework.data.elasticsearch.annotations.Document(indexName = "scdocindex")
public class Document {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String docTitle;
	private String docType;
	
	@Lob
	private byte[] data;
	
	public Document() {}

	public Document(String docName, String docType, byte[] data) {
		super();
		this.docTitle = docName;
		this.docType = docType;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocName() {
		return docTitle;
	}

	public void setDocName(String docName) {
		this.docTitle = docName;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
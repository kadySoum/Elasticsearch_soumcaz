package com.example.daarprojectcazessoumahoro.repository;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.daarprojectcazessoumahoro.entity.Document;

@Component
public class DocumentRepositoryImpl implements DocumentRepository {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	RestHighLevelClient client = new RestHighLevelClient(
			RestClient.builder(new HttpHost("localhost", 9200, "http")));

	@Override
	public List<Document> findAllUserDetailsFromElastic() {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("scdocindex");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchRequest.source(searchSourceBuilder);
		List<Document> documentList = new ArrayList<>();
		SearchResponse searchResponse = null;
		try {
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					documentList.add(objectMapper.convertValue(map, Document.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentList;
	}

	@Override
	public List<Document> findAllUserDataByNameFromElastic(String userName) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("scdocindex");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName.keyword", userName)));
		searchRequest.source(searchSourceBuilder);
		List<Document> documentList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					documentList.add(objectMapper.convertValue(map, Document.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentList;
	}

	@Override
	public List<Document> findAllUserDataByNameAndAddressFromElstic(String userName, String address) {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("scdocindex");
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("userName.keyword", userName))
				                                           .must(QueryBuilders.matchQuery("address", address)));
		searchRequest.source(searchSourceBuilder);
		List<Document> documentList = new ArrayList<>();
		
		try {
			SearchResponse searchResponse = null;
			searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (SearchHit hit : searchHit) {
					Map<String, Object> map = hit.getSourceAsMap();
					documentList.add(objectMapper.convertValue(map, Document.class));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentList;
	}

	@Override
	public <S extends Document> S indexWithoutRefresh(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Document> search(QueryBuilder query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Document> search(QueryBuilder query, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Document> search(Query searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Document> searchSimilar(Document entity, String[] fields, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Document> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Document> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Document> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Document> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Document entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Document> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Document> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

/*
	@Override
	public List<Document> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Document> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Document> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Document> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Document getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Document> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Document> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Document entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Document> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Document> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Document> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Document> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}*/

}

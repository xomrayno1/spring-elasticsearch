package com.app.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;

@Configuration
public class GetESClient {
	
	@Bean
	public ElasticsearchClient getElasticsearchClient() {
		RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback = new HttpClientConfigImpl();
		
		RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
		restClientBuilder.setHttpClientConfigCallback(httpClientConfigCallback);
		
		RestClient restClient = restClientBuilder.build();
		 
		return new ElasticsearchClient(new RestClientTransport(restClient, new JacksonJsonpMapper()));
	}
	
	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper()
				.registerModule(new JavaTimeModule())
				.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS); // return {} thay vi` throw error 
	}

}

package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Mapping(mappingPath = "sec02/index-mapping.json")
@Document(indexName = "customer")
public class Customer {
	
	@Id
	private Integer id;
	private String name;
	private Integer age;

}

package com.app.repository.impl;

import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Repository;

import com.app.model.Employee;
import com.app.repository.EmployeeCustomRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmployeeCustomRepositoryImpl implements EmployeeCustomRepository{
	
	final ElasticsearchOperations elasticsearchOperations;

	@Override
	public SearchHits<Employee> findByAgeLessThan(Integer age) {
		
		var ageLessThan = Criteria.where("age").lessThan(age);
		
		var query = CriteriaQuery.builder(ageLessThan).build();
		
		return elasticsearchOperations.search(query, Employee.class);
	}

}

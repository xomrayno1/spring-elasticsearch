package com.app.repository;

import org.springframework.data.elasticsearch.core.SearchHits;

import com.app.model.Employee;

public interface EmployeeCustomRepository {
	
	SearchHits<Employee> findByAgeLessThan(Integer age);

}

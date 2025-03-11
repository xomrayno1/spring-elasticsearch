package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;

import com.app.model.Employee;

public interface EmployeeService {
	
	Employee createEmployee(Employee employee);
	
	Employee updateEmployee(Long id, Employee  employee);

	List<Employee> findAll();
	
	Employee findById(String id);
	
	void deleteById(String id);
	
	Page<Employee> findAll(int page, int size);
	
	List<Employee> findByNameMatchQuery(String name);
	
	SearchHits<Employee> findByDepartment(String department);
	
	SearchHits<Employee> findByDepartmentIn(List<String> departments);
	
	SearchHits<Employee> findByAgeBetween(Integer from, Integer to, Sort  sort);
	
	SearchPage<Employee> findByDepartment(String department, Pageable pageable);
	
	SearchHits<Employee> findByName(String name);
	
	
}

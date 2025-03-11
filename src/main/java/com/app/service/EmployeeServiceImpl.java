package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.stereotype.Service;

import com.app.model.Employee;
import com.app.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
	
	private final EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(String id) {
		return employeeRepository.findById(id)
				.orElseThrow( () -> new RuntimeException("Employee not found"));
	}

	@Override
	public void deleteById(String id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Page<Employee> findAll(int page, int size) {
		return employeeRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findByNameMatchQuery(String name) {
		return employeeRepository.findByNameMatchQuery(name);
	}

	@Override
	public SearchHits<Employee> findByDepartment(String department) {
		return employeeRepository.findByDepartment(department);
	}

	@Override
	public SearchHits<Employee> findByDepartmentIn(List<String> departments) {
		var searchHits = employeeRepository.findByDepartmentIn(departments);
		log.info(" Hit total: {} ", searchHits.getTotalHits());
		return searchHits;
	}

	@Override
	public SearchHits<Employee> findByAgeBetween(Integer from, Integer to, Sort sort) {
		return employeeRepository.findByAgeBetween(from, to, sort);
	}

	@Override
	public SearchPage<Employee> findByDepartment(String department, Pageable pageable) {
		return employeeRepository.findByDepartment(department, pageable);
	}

	@Override
	public SearchHits<Employee> findByName(String name) {
		return employeeRepository.findByName(name);
	}

}

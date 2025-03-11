package com.app.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Employee;
import com.app.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.createEmployee(employee));
	}
	
	@PutMapping
	public ResponseEntity<Employee> updateEmployy(@RequestParam Long employeeId, @RequestBody Employee employee) {
		return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employee));
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAll() {
		return ResponseEntity.ok(employeeService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> findById(@PathVariable("id") String id) {
		return ResponseEntity.ok(employeeService.findById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") String id) {
		employeeService.deleteById(id);
		return ResponseEntity.ok("delete ok");
	}
	
	@PostMapping("/search")
	public ResponseEntity<Object> search(@RequestParam int page, @RequestParam int size) {
		return ResponseEntity.ok(employeeService.findAll(page, size));
	}
	
	@GetMapping("/search_match")
	public ResponseEntity<Object> searchMatch(@RequestParam("name") String name) {
		return ResponseEntity.ok(employeeService.findByNameMatchQuery(name));
	}
	
	@GetMapping("/search_hit_by_department")
	public ResponseEntity<Object> searchHitByDepartment(@RequestParam("department") String department) {
		return ResponseEntity.ok(employeeService.findByDepartment(department));
	}
	
	@GetMapping("/search_hit_by_departments")
	public ResponseEntity<Object> searchHitByDepartments(@RequestParam("department") List<String> departments) {
		return ResponseEntity.ok(employeeService.findByDepartmentIn(departments));
	}
	
	/**
	 * Tìm kiếm theo điều kiện age between from - to <br>
	 * sort tăng dần theo giá <br>
	 * **/
	@GetMapping("/search_hit_by_age_between")
	public ResponseEntity<Object> searchHitByAgeBetween(@RequestParam("from") Integer from, @RequestParam("to") Integer to) {
		return ResponseEntity.ok(employeeService.findByAgeBetween(from, to, Sort.by(Direction.ASC, "salary")));
	}
	
	/**
	 * Tìm kiếm theo điều kiện department <br>
	 * Phân trang <br>
	 * example url: /api/employees/search_page_by_department?department=IT&page=0&size=1
	 * **/
	@GetMapping("/search_page_by_department")
	public ResponseEntity<Object> searchPageByDepartment(@RequestParam("department") String department, Pageable pageable) {
		return ResponseEntity.ok(employeeService.findByDepartment(department, pageable));
	}
	
	@GetMapping("/search_by_name")
	public ResponseEntity<Object> searchByName(@RequestParam("name") String name) {
		return ResponseEntity.ok(employeeService.findByName(name));
	}
	
	
}

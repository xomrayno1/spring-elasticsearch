package com.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.annotations.HighlightParameters;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchPage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.app.model.Employee;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String>{
	
	List<Employee> findAll();
	
	@Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
	List<Employee> findByNameMatchQuery(String name);
	
	@Query(value = "\"bool\": { \"must\": [ { \"match\": { \"name\": \"?0\" } }, { \"range\": { \"salary\": { \"gte\": ?1 } } } ] }")
	List<Employee> findByMatchNameAndSalaryRange(String name, double salary);
	
	@Query(value = "\"range\": { \"salary\": { \"gte\": ?0, \"lte\": ?1 } }")
	List<Employee> findBySlaryRange(double salary);
	
	SearchHits<Employee> findByDepartment(String department);
	
	/**
	 * Trả về cả score <br>
	 * số lượng hit <br>
	 * **/
	SearchHits<Employee> findByDepartmentIn(List<String> departments);
	
	SearchHits<Employee> findByAgeBetween(Integer from, Integer to, Sort  sort);
	
	/**
	 * Cấu trúc bao gồm cả SearchHits và Page
	 * **/
	SearchPage<Employee> findByDepartment(String department, Pageable pageable);
	
//	
//	@Query("""
//            {
//              "bool":{
//                "must":[
//                  {
//                    "terms":{
//                      "name": #{#parameters.![value]}
//                    }
//                  }
//                ]
//              }
//            }
//            """)
//    Page<Book> findByName(Collection<QueryParameter> parameters, Pageable pageable);
	
	
	/**
	 * Hightlight tìm kiếm kết quả match với nhau.
	 * **/
	@Query("{ \"bool\": { \"must\": [ { \"match\": { \"name\": \"?0\" } } ] } }")
	@Highlight(
		fields = @HighlightField(
					name = "name",
					parameters = @HighlightParameters(
								preTags = "<br>",
								postTags = "</br>"
							)
				)
			)
	SearchHits<Employee> findByName(String name);

}

package com.app.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.app.model.Customer;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String>{


}

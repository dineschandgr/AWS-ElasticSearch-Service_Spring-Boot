package io.elasticsearch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.elasticsearch.model.Customer;
import io.elasticsearch.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	public List<Customer> findByFirstName(String firstName) {
		return customerRepository.findByFirstName(firstName);
	}

	public List<Customer> findByLastName(String lastName) {
		return customerRepository.findByLastName(lastName);
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	public Iterable<Customer> saveAll(List<Customer> customers) {
		return customerRepository.saveAll(customers);
	}
	
}

package io.elasticsearch.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.elasticsearch.model.Customer;
import io.elasticsearch.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@RequestMapping("/findAll")
	private Iterable<Customer> findAllCustomers(){
		logger.info("inside CRUD controller findAllCustomers "+new Date());
		return customerService.findAll();
	}
	
	@RequestMapping("/findByFirstName/{firstName}")
	private Iterable<Customer> findByFirstName(@PathVariable String firstName){
		logger.info("inside CRUD controller findByFirstName "+new Date()+ " firstName is "+firstName);
		return customerService.findByFirstName(firstName);
	}
	
	@RequestMapping("/findByLastName/{lastName}")
	private Iterable<Customer> findByLastName(@PathVariable String lastName){
		logger.info("inside CRUD controller findByLastName "+new Date()+ " lastName is "+lastName);
		return customerService.findByLastName(lastName);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/saveCustomer")
	private String saveCustomer(@RequestBody Customer customer){
		logger.info("inside CRUD controller saveCustomer "+new Date()+ " customer is "+customer.getFirstName());
		customerService.save(customer);
		return customer.getId();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/saveCustomers")
	private int saveCustomers(@RequestBody List<Customer> customers){
		logger.info("inside CRUD controller saveCustomers "+new Date()+ " size is "+customers.size());
		customerService.saveAll(customers);
		return customers.size();
	}
	
}

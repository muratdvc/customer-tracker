package com.luv2code.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	public CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {

		customerDAO.saveCustomer(customer);
		
	}

	@Override
	@Transactional
	public Customer findCustomer(int id) {

		Customer theCustomer = customerDAO.findCustomer(id);
		
		return theCustomer;
	}

	@Override
	@Transactional
	public void updateCustomer(Customer customer) {

		customerDAO.updateCustomer(customer);
		
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		
		customerDAO.deleteCustomer(id);
		
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		
		return customerDAO.searchCustomers(theSearchName);
		
    }

	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		
		return customerDAO.getCustomers(theSortField);
	}
	
	

}











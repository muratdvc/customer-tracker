package com.luv2code.springdemo.dao;


import com.luv2code.springdemo.entity.Customer;
import java.util.List;


public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer findCustomer(int id);

	public void updateCustomer(Customer customer);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String theSearchName);

}

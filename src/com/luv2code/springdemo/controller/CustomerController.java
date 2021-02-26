package com.luv2code.springdemo.controller;



import java.util.List;
import com.luv2code.springdemo.util.SortUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {	
	
	/*
	// need to inject the customer dao
	@Autowired // spring's dependency injection
	private CustomerDAO customerDAO;
	*/
	
	// need to inject the customer service
	@Autowired // spring's dependency injection
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customers from the dao-service
		List<Customer> theCustomers; //= customerService.getCustomers();
		
		// check for sort field
		if(sort != null) {
			int theSortedField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortedField);
		}
		else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.getCustomers(SortUtils.FIRST_NAME);
		}

		// add to customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	// save or update
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		// save the customer using our service 
		customerService.saveCustomer(customer);
		
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		
		//customerService.updateCustomer()
		Customer updatedCustomer = customerService.findCustomer(id);
		
		model.addAttribute("customer", updatedCustomer);
		
		
		return "customer-form";
	}
	
	/* --> bu gereksiz
	@PostMapping("/updateCustomer")
	public String updateCustomer(@ModelAttribute("customer")Customer customer) {
		
		customerService.updateCustomer(customer);
		
		return "list-customer";
	}
	*/
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		customerService.deleteCustomer(id);
		
		return "redirect:/customer/list";
		
	}
	
	@GetMapping("/search")
	public String searchCustoemrs(@RequestParam("theSearchName") String theSearchName, Model theModel) {
		
		// search customer from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
		
		
	}
	
	
}











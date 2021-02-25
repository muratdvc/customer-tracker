package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	/*
	 * we move this functionality up to the service layer --> 
	 * @Transactional 
	 * automagically begin and end(session.beginTransaction() or session.getTransaction().commit())
	 * a transaction for your Hibernate code 
	 * no need for you to explicitly do this in your code  
	 */
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the result
		return customers;
	}


	@Override
	//@Transactional
	public void saveCustomer(Customer customer) {
		
		// get current hibernate session 
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer 
		currentSession.saveOrUpdate(customer);
		
	}


	@Override
	//@Transactional
	public Customer findCustomer(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Customer> query = currentSession.createQuery("from Customer c where c.id=:id", Customer.class);
		query.setParameter("id", id);
		
		Customer customer = query.getSingleResult();
		
		return customer;
	}

	// yukarida save metodunu saveorupdate yapinca bu metod gereksiz oldu
	@Override
	//@Transactional
	public void updateCustomer(Customer customer) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer theCustomer = currentSession.get(Customer.class, customer.getId());
		
		System.out.println(theCustomer);
		
		theCustomer.setFirstName(customer.getFirstName());
		theCustomer.setFirstName(customer.getLastName());
		theCustomer.setFirstName(customer.getEmail());
		
	/*
		Query<Customer> query = currentSession.createQuery("update Customer set Customer.first_name=:first_name, Customer.last_name=:last_name, "
				+ "														Customer.email=:email where Customer.id=:id", Customer.class);
		
		query.setParameter("first_name", customer.getFirstName());
		query.setParameter("last_name", customer.getLastName());
		query.setParameter("email", customer.getEmail());
		query.setParameter("id", theCustomer.getId());
		
		Customer updatedCustomer = query.getSingleResult();
		
		//currentSession.update(updatedCustomer);
		
		/*
		List<Customer> customers= query.getResultList();
		
		for (Customer updatedCustomer : customers) {
			currentSession.update(updatedCustomer);
		}
		*/
		
		
		
	}


	@Override
	///@Transactional
	public void deleteCustomer(int id) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Customer customer = currentSession.get(Customer.class, id);
		
		currentSession.delete(customer);
		
		/*bu sekilde de olabilir
		Query query = currentSession.createQuery("delete  from Customer c where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
		*/
		
	}


	@Override
	//@Transactional
	public List<Customer> searchCustomers(String theSearchName) {

	    // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;

	}

}

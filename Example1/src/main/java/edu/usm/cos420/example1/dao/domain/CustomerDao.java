package edu.usm.cos420.example1.dao.domain;

import java.util.List;

import edu.usm.cos420.example1.dao.GenericDao;
import edu.usm.cos420.example1.dao.ObjectStreamDao;
import edu.usm.cos420.example1.domain.Customer;
/**
 * Customer DAO for saving customer objects
 * @author Jeremy
 *
 */
public class CustomerDao {
	
	private GenericDao<Long, Customer> customerDao;
	
	/**
	 * Default constructor that creats a ObjectStream file fo saving
	 */
	public CustomerDao(){
		customerDao = new ObjectStreamDao<Long, Customer>("customer.ser");
	}
	/**
	 * Constructor when the filename is specified
	 */
	public CustomerDao(String filename){
		customerDao = new ObjectStreamDao<Long, Customer>(filename);
	}
	/**
	 * Support for other DAOs is provided
	 * @param dao a Data Access Object class that implements GenericDao<Long,CItem> 
	 */
	public CustomerDao(GenericDao<Long,Customer> dao)
	{
		customerDao = dao;
	}
	/**
	 * Returns the DAO used in the class
	 * @return a DAO that implements GenericDao<Long,Customer> 
	 */
	public GenericDao<Long,Customer> getCustomerDao() {
		return customerDao;
	}
	/**
	 * Add a Customer to the DAO repository
	 * @param entity any Customer object
	 */
	public void add(Customer entity)
	{
		customerDao.add(entity.getId(), entity);
	}
	/**
	 * Update a Customer in the DAO repository
	 * @param entity any Customer object
	 */
	public void update(Customer entity) 
	{
		customerDao.update(entity.getId(), entity);
	}
	/**
	 * Remove a Customer in the DAO repository
	 * @param id of the Customer object to remove
	 */
	public void remove(Long id)
	{
		customerDao.remove(id);
	}
	/**
	 * Find a Customer in the DAO repository
	 * @param id of the Customer object to locate
	 * @return the Customer with id field equal to key
	 */
	public Customer find(Long key)
	{
		return customerDao.find(key);
	}
    
	/**
	 * Generate a list of Customer in the DAO repository
	 * @return List of Customer 
	 */

	public List<Customer> list() {
		return customerDao.list();
	}
}

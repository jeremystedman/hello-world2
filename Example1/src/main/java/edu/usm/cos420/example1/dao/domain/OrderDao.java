package edu.usm.cos420.example1.dao.domain;

import java.util.List;

import edu.usm.cos420.example1.dao.GenericDao;
import edu.usm.cos420.example1.dao.ObjectStreamDao;
import edu.usm.cos420.example1.domain.Orders;
/**
 * 
 * @author Jeremy
 *
 */
public class OrderDao {
	private GenericDao<Long, Orders> orderDao;

	/**
	 * Default constructor that creats a ObjectStream file fo saving
	 */
	public OrderDao(){
		orderDao = new ObjectStreamDao<Long, Orders>("orders.ser");
	}
	/**
	 * Constructor when the filename is specified
	 */
	public OrderDao(String filename){
		orderDao = new ObjectStreamDao<Long, Orders>(filename);
	}
	/**
	 * Support for other DAOs is provided
	 * @param dao a Data Access Object class that implements GenericDao<Long,CItem> 
	 */
	public OrderDao(GenericDao<Long,Orders> dao)
	{
		orderDao = dao;
	}
	/**
	 * Returns the DAO used in the class
	 * @return a DAO that implements GenericDao<Long,Orders> 
	 */
	public GenericDao<Long,Orders> getOrderDao() {
		return orderDao;
	}
	/**
	 * Add a Orders to the DAO repository
	 * @param entity any Orders object
	 */
	public void add(Orders entity)
	{
		orderDao.add(entity.getId(), entity);
	}
	/**
	 * Update a Orders in the DAO repository
	 * @param entity any Orders object
	 */
	public void update(Orders entity) 
	{
		orderDao.update(entity.getId(), entity);
	}
	/**
	 * Remove a Orders in the DAO repository
	 * @param id of the Orders object to remove
	 */
	public void remove(Long id)
	{
		orderDao.remove(id);
	}
	/**
	 * Find a Orders in the DAO repository
	 * @param id of the Orders object to locate
	 * @return the Orders with id field equal to key
	 */
	public Orders find(Long key)
	{
		return orderDao.find(key);
	}

	/**
	 * Generate a list of Orders in the DAO repository
	 * @return List of Orders 
	 */

	public List<Orders> list() {
		return orderDao.list();
	}
}

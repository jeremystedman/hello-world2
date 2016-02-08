package edu.usm.cos420.example1.dao.domain;

import java.util.List;

import edu.usm.cos420.example1.dao.GenericDao;
import edu.usm.cos420.example1.dao.ObjectStreamDao;
import edu.usm.cos420.example1.domain.InventoryItem;
/**
 * 
 * @author Jeremy
 *
 */
public class InventoryDao  {
	private GenericDao<Long, InventoryItem> inventoryDao;

	/**
	 * Default constructor that creats a ObjectStream file fo saving
	 */
	public InventoryDao(){
		inventoryDao = new ObjectStreamDao<Long, InventoryItem>("inventoryItem.ser");
	}
	/**
	 * Constructor when the filename is specified
	 */
	public InventoryDao(String filename){
		inventoryDao = new ObjectStreamDao<Long, InventoryItem>(filename);
	}
	/**
	 * Support for other DAOs is provided
	 * @param dao a Data Access Object class that implements GenericDao<Long,CItem> 
	 */
	public InventoryDao(GenericDao<Long,InventoryItem> dao)
	{
		inventoryDao = dao;
	}
	/**
	 * Returns the DAO used in the class
	 * @return a DAO that implements GenericDao<Long,Customer> 
	 */
	public GenericDao<Long,InventoryItem> getInventoryDao() {
		return inventoryDao;
	}
	/**
	 * Add a Customer to the DAO repository
	 * @param entity any Customer object
	 */
	public void add(InventoryItem entity)
	{
		inventoryDao.add(entity.getId(), entity);
	}
	/**
	 * Update a Customer in the DAO repository
	 * @param entity any Customer object
	 */
	public void update(InventoryItem entity) 
	{
		inventoryDao.update(entity.getId(), entity);
	}
	/**
	 * Remove a Customer in the DAO repository
	 * @param id of the Customer object to remove
	 */
	public void remove(Long id)
	{
		inventoryDao.remove(id);
	}
	/**
	 * Find a Customer in the DAO repository
	 * @param id of the Customer object to locate
	 * @return the Customer with id field equal to key
	 */
	public InventoryItem find(Long key)
	{
		return inventoryDao.find(key);
	}

	/**
	 * Generate a list of Customer in the DAO repository
	 * @return List of Customer 
	 */

	public List<InventoryItem> list() {
		return inventoryDao.list();
	}
}
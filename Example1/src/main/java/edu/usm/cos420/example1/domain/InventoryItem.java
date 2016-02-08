package edu.usm.cos420.example1.domain;

import java.io.Serializable;
/**
 * 
 * @author Jeremy
 *
 */
public class InventoryItem implements Serializable {

	private static final long serialVersionUID = 4152225538592352767L;
	private long itemNum;
	private int currentStock;
	private String descirption;
	
	
	/**
	 * Constructor for the inventory item
	 * @param itemNum the unique id number
	 * @param description the items description
	 */
	public InventoryItem(int itemNum, String description, int currentStock){
		this.itemNum = itemNum;
		this.descirption = description;
		this.currentStock = currentStock;
	}
	/**
	 * To string method for the object
	 * @return Item # and Description
	 */
	public String toString(){
		return String.format("Inventory Number: %-6d Stock: %-6d Description: %s\n", itemNum, currentStock, descirption);
	}
	/**
	 * Add to the current Stock.
	 * @param amount The number of stock to be added
	 */
	public void addToStock(int amount){
		currentStock += amount;
	}
	/**
	 * Get the inventory ID
	 * @return the inventory ID
	 */
	public Long getId() {
		return itemNum;
	}
	/**
	 * Get the current stock
	 * @return current stock
	 */
	public int getStock(){
		return currentStock;
	}
	/**
	 * Removes the stock from the current stock
	 * @param orderAmount Amount to be removed
	 */
	public void removeStock(int orderAmount) {
		currentStock -= orderAmount;
	}
}


package edu.usm.cos420.example1.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
/**
 * 
 * @author Jeremy
 *
 */
public class Orders  implements Serializable{

	
	private static final long serialVersionUID = 7228708022423606286L;
	private long orderNum;
	private Date date;
	private int custID;
	private ArrayList<Integer> itemNums;
	private ArrayList<Integer> stockAmounts;
	
	/**
	 * Constructor for Orders
	 * @param orderNum ID for the order
	 * @param date time and date of placed order
	 * @param custID ID of the customer who placed order
	 */
	
	public Orders(int orderNum, Date date, int custID){
		this.orderNum = orderNum;
		this.date = date;
		this.custID = custID;
		itemNums = new ArrayList<Integer>();
		stockAmounts = new ArrayList<Integer>();
		
	}
	/**
	 * Add items to the orders object
	 * @param itemNum ID of the inventory number
	 * @param stockAmount Amount placed in the order
	 */
	public void addItem(int itemNum, int stockAmount){
		itemNums.add(itemNum);
		stockAmounts.add(stockAmount);
	}
	/**
	 * Returns the customer ID associated with the order
	 * @return custID
	 */
	public int getCustID(){
		return custID;
	}

	/**
	 * toString to return the visual representation of the Order object
	 * @return 
	 */
	public String toString(){
		return returnBuilder();
	}
	/**
	 * Method to build the complex String representation of Orders
	 * @return
	 */
	private String returnBuilder(){
		String temp = String.format("Order Number: %-10d Customer ID: %-13d Date Placed: %s",orderNum,custID,date.toString());
		temp += String.format("\n%-10s %-20s %s\n","#","Item Number"," Quantity");
		for(int x = 1; x <= itemNums.size(); x++){
			String z = String.format("%-10d %-24d %d\n",x,itemNums.get(x-1),stockAmounts.get(x-1));
			temp+=z;
		}
		return temp+"\n";
	}
	/**
	 * Get the ID of the order
	 * @return orderNum
	 */
	public Long getId() {
		return orderNum;
	}
	/**
	 * Gets the date associated with the order
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}
}

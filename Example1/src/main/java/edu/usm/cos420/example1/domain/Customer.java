package edu.usm.cos420.example1.domain;

import java.io.Serializable;
/**
 * 
 * @author Jeremy
 *
 */
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8480691902146569880L;
	private Long id;
	private String name;
	private String address;
	/**
	 * Constructor to build the customer object
	 * @param id Customers 6 digit ID number
	 * @param name Customers name
	 * @param address Customers address
	 */
	public Customer(Long id, String name, String address){
		this.id = id;
		this.name = name;
		this.address = address;
	}
	/**
	 * toString for the Customer object
	 */
	public String toString(){
		return String.format("ID: %-10d Name: %-20s Address: %-15s\n", id, name, address);
	}
	/**
	 * Get the ID for the customer 
	 * @return the Long ID
	 */
	public long getId(){
		return id;
	}
}

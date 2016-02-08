package edu.usm.cos420.example1.controller;

import java.util.List;

import edu.usm.cos420.example1.dao.domain.CustomerDao;
import edu.usm.cos420.example1.domain.Customer;
import edu.usm.cos420.example1.view.impl.CustAndIven_View;
/**
 * 
 * @author Jeremy
 *
 */
public class Customer_Controller {
	private CustAndIven_View view;
	private CustomerDao custDao;

	/**
	 * Constructor for the controller to add a customer
	 * @param view: The customer/inventory view used with this controller
	 * @param custDao: The customer DAO system used to save the data
	 */
	public Customer_Controller(CustAndIven_View view, CustomerDao custDao){
		this.view = view;
		this.custDao = custDao;
	}
	/**
	 * Displays the menu and handles the input
	 * @Errors ID: already in use or ID not 6 digits
	 */
	public void provideCustomerAccess(){
		view.displayMenu("Enter the new Customers info:");
		int ID = view.readIntWithPrompt("Enter ID(6 digit): ");
		//check if 6 digits
		if(String.valueOf(ID).length() != 6){
			System.err.println("ERROR: not 6 digits");
			System.out.flush();
			provideCustomerAccess();
			return;
		}
		//check if ID exsits
		if(custDao.find((long) ID) != null){
			System.err.println("ERROR: ID already added.");
			System.out.flush();
			provideCustomerAccess();
			return;
		}
		String name = view.readStringWithPrompt("Enter name: ");
		String address = view.readStringWithPrompt("Enter address: ");
		executeChoice(ID, name, address);
	}
	/**
	 * Creates the new customer object and once complete will add it
	 * to permanant memory.
	 * @param ID The customers unique ID number
	 * @param name The customers name
	 * @param address the customers address
	 */
	public void executeChoice (int ID, String name, String address) {
		System.out.println();
		Customer add = new Customer((long) ID, name, address);
		custDao.add(add);

	}
	/**
	 * Displays current list of customers
	 */
	public void displayCustomers(){
		System.out.println("Customer List");
		List<Customer> temp = custDao.list();
		for(int x = 0; x < temp.size(); x++){
			System.out.print(temp.get(x));
		}
		
		view.readStringWithPrompt("Press ENTER to continue:");
	}
}

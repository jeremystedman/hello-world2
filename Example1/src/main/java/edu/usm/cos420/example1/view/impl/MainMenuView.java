package edu.usm.cos420.example1.view.impl;

import java.util.Scanner;
/**
 * 
 * @author Jeremy
 *
 */
public class MainMenuView {
	/** {@value}  : no choice selected by user */
	public static final int NO_CHOICE = 0;
	/** {@value #ADDONE}  : Add customer to the collection of items */
	public static final int ADD_CUST = 1;
	/** {@value #ADD_INVE}  : Add new inventory item*/
	public static final int ADD_INVE = 2;
	/** {@value #ADD_STOCK} : Add new stock */
	public static final int ADD_STOCK = 3;
	/** {@value #ADD_ORDER} : Add new order */
	public static final int ADD_ORDER = 4;
	/** {@value #DISPLAY_INVENTORY}  : Display the current inventory */
	public static final int DISPLAY_INVENTORY = 5;
	/** {@value #DISPLAY_CUSTOMERS}  : display customer list*/
	public static final int DISPLAY_CUSTOMERS = 6;
	/** {@value #DISPLAY_ORDERS}  : Display the orders */
	public static final int DISPLAY_ORDERS = 7;
	/** {@value #EXIT}  : Exit the program */
	public static final int EXIT = 8;

	private Scanner in = new Scanner(System.in);
	/**
	 * Blank constructor
	 */
	public  MainMenuView(){

	}
	/**
	 * Displays the main menu options
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("Enter the number denoting the action");
		System.out.println("ADD new Customer...................."+ADD_CUST);
		System.out.println("ADD new Inventory..................."+ADD_INVE);
		System.out.println("ADD/REMOVE stock...................."+ADD_STOCK);
		System.out.println("ADD new order......................."+ADD_ORDER);
		System.out.println("Display current inventory..........."+DISPLAY_INVENTORY);
		System.out.println("Display Customer list..............."+DISPLAY_CUSTOMERS);
		System.out.println("Display current Orders.............."+DISPLAY_ORDERS);
		System.out.println("Exit................................"+EXIT);
	}
	/**
	 * 
	 * @param prompt
	 * @return
	 * <ul>
	 *    <li>{@value #ADD_CUST}  : Add a new customer
	 *    <li>{@value #EXIT}  : Exit the program 
	 * </ul>
	 */
	public int readIntWithPrompt (String prompt) {
		System.out.print(prompt); 
		System.out.flush();
        while(!in.hasNextInt()){
        	in.nextLine();
        }
		int choice = in.nextInt();
		return choice;
	}
}

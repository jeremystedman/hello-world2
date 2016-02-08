package edu.usm.cos420.example1.controller;

import java.util.List;

import edu.usm.cos420.example1.dao.domain.InventoryDao;
import edu.usm.cos420.example1.domain.InventoryItem;
import edu.usm.cos420.example1.view.impl.CustAndIven_View;
/**
 * 
 * @author Jeremy
 *
 */
public class InventoryController {
	private static CustAndIven_View view;
	private static InventoryDao invenDao;

	/**
	 * Constructor for the controller to add inventory
	 * @param view: The customer/inventory view
	 * @param invenDao: The inventory DAO used for saving
	 */
	public InventoryController(CustAndIven_View view, InventoryDao invenDao){
		InventoryController.view = view;
		InventoryController.invenDao = invenDao;
	}
	/**
	 * Display menu and prompt user for input
	 */
	public void provideCustomerAccess(){
		view.displayMenu("Enter the new inventory item info:");
		int itemNum = view.readIntWithPrompt("Enter Item #: ");
		//check if 6 digits
		if(String.valueOf(itemNum).length() != 6){
			System.err.println("ERROR: not 6 digits");
			System.out.flush();
			provideCustomerAccess();
			return;
		}
		//check if ID exsits
		if(invenDao.find((long) itemNum) != null){
			System.err.println("ERROR: item # already added.");
			System.out.flush();
			provideCustomerAccess();
			return;
		}
		String description = view.readStringWithPrompt("Enter item discription: ");
		int currStock = view.readIntWithPrompt("Enter the current Stock: ");
		executeChoice(itemNum, description, currStock);
	}
	/**
	 * Adds the new item object to the data
	 * @param itemNum the unique item number
	 * @param description the item description
	 * @param currStock the amount of stock being added
	 */
	private void executeChoice(int itemNum, String description, int currStock) {
		System.out.println();
		InventoryItem add = new InventoryItem(itemNum, description, currStock);
		invenDao.add(add);
	}
	/**
	 * Add or remove stock from the inventory
	 */
	public void addNewStock(){
		view.displayMenu("Add or Remove Stock:");
		displayCurrentInventory();
		int itemNum = view.readIntWithPrompt("Enter Item #: ");
		InventoryItem temp = invenDao.find((long)itemNum);
		//if item number is not vaild
		while(temp == null){
			System.err.println("ERROR: Item number not recongnized.");
			System.out.flush();
			itemNum = view.readIntWithPrompt("Enter Item #: ");
			temp = invenDao.find((long)itemNum);
		}
		int stockToAdd = view.readIntWithPrompt("Amount to be added or removed(preced with '-'): ");
		temp.addToStock(stockToAdd);
		invenDao.update(temp);
		//getID from memory and update with new stock number added
		//InventoryItem curr = dao.find(itemNum);
		//curr.addToStock(stockToAdd);
		displayCurrentInventory();
	}
	/**
	 * Display the current inventory for the user when placing an order.
	 */
	public static void displayCurrentInventory(){
		//get list of inventory items.
		System.out.println("Current Inventroy");
		
		
		List<InventoryItem> temp = invenDao.list();
		for(int x = 0; x < temp.size(); x++){
			System.out.print(temp.get(x));
		}
		view.readStringWithPrompt("Press ENTER to continue:");
		System.out.println();
	}
}


package edu.usm.cos420.example1.controller;

import edu.usm.cos420.example1.dao.domain.InventoryDao;
import edu.usm.cos420.example1.dao.domain.OrderDao;
import edu.usm.cos420.example1.dao.domain.CustomerDao;
import edu.usm.cos420.example1.view.impl.CustAndIven_View;
import edu.usm.cos420.example1.view.impl.MainMenuView;
/**
 * 
 * @author Jeremy
 *
 */
public class MainMenuController {

	private MainMenuView view;
	private CustomerDao custDao;
	private InventoryDao invenDao;
	private OrderDao orderDao;
	
	/**
	 * Constructor to build the main menu view
	 * @param view: the view object used to display information
	 */
	public MainMenuController(MainMenuView view){
		this.view = view;
		this.custDao = new CustomerDao();
		this.invenDao = new InventoryDao();
		this.orderDao = new OrderDao();
	}
	/**
	 * Displays the menu and waits for user input
	 */
	public void provideMMAccess()
	{
		int choice = MainMenuView.NO_CHOICE;
		while (choice != MainMenuView.EXIT) {
			view.displayMenu();
			choice = view.readIntWithPrompt("Enter choice: ");
			executeChoice(choice);
		}  	
	}
	/**
	 * The handler for the main menu navigation
	 * @param choice: The user inputed submenu selection
	 */
	public void executeChoice (int choice) {
		System.out.println();
		CustAndIven_View csView = new CustAndIven_View();
		InventoryController inCont = new InventoryController(csView, invenDao);
		OrderController orCont = new OrderController(csView, orderDao, custDao, invenDao);
		Customer_Controller csCont = new Customer_Controller(csView, custDao);
		
		switch(choice){
		case MainMenuView.ADD_CUST:
			csCont.provideCustomerAccess();
			break;
		case MainMenuView.ADD_INVE:
			inCont.provideCustomerAccess();
			break;
		case MainMenuView.ADD_STOCK:
			inCont.addNewStock();
			break;
		case MainMenuView.ADD_ORDER:
			orCont.provideCustomerAccess();
			break;
		case MainMenuView.DISPLAY_INVENTORY:
			InventoryController.displayCurrentInventory();
			break;
		case MainMenuView.DISPLAY_CUSTOMERS:
			csCont.displayCustomers();
			break;
		case MainMenuView.DISPLAY_ORDERS:
			orCont.displayOrders();
			break;
		case MainMenuView.EXIT:
			System.out.println("Goodbye.");
			break;
			
		default: 
			break;
		}
	}
}

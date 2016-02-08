package edu.usm.cos420.example1.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.usm.cos420.example1.dao.domain.CustomerDao;
import edu.usm.cos420.example1.dao.domain.InventoryDao;
import edu.usm.cos420.example1.dao.domain.OrderDao;
import edu.usm.cos420.example1.domain.Customer;
import edu.usm.cos420.example1.domain.InventoryItem;
import edu.usm.cos420.example1.domain.Orders;
import edu.usm.cos420.example1.view.impl.CustAndIven_View;
/**
 * 
 * @author Jeremy
 *
 */
public class OrderController {
	private CustAndIven_View view;
	private OrderDao orderDao;
	private CustomerDao custDao;
	private InventoryDao invenDao;

	/**
	 * Constructor for the controller for adding orders
	 * @param view The customer/inventory view which is used for the orders
	 */
	public OrderController(CustAndIven_View view, OrderDao orderDao, CustomerDao custDao, InventoryDao invenDao){
		this.view = view;
		this.orderDao = orderDao;
		this.custDao = custDao;
		this.invenDao = invenDao;
	}
	/**
	 * Displays the Order submenu and prompts the user for input
	 */
	public void provideCustomerAccess(){
		view.displayMenu("Enter the order info:");
		int orderNum = view.readIntWithPrompt("Enter the order number(6 digit): ");
		//check if 6 digits
		if(String.valueOf(orderNum).length() != 6){
			System.err.println("ERROR: not 6 digits");
			System.out.flush();
			provideCustomerAccess();
			return;
		}
		//check if ordernumber has been used before
		if(orderDao.find((long) orderNum) != null){
			System.err.println("ERROR: Order number has already been used.");
			System.out.flush();
			provideCustomerAccess();
			return;
		}
		Date date = new Date();

		int custID = view.readIntWithPrompt("Enter customer ID number: ");
		//check if this is a vaild customer ID
		Customer temp = custDao.find((long)custID);
		while(temp == null){
			System.err.println("ERROR: Invalid customer ID number.");
			System.out.flush();
			custID = view.readIntWithPrompt("Enter customer ID number: ");
			temp = custDao.find((long)custID);
		}
		Orders orderTobeAdded = new Orders(orderNum, date, custID);


		int addMore = 1;
		//loop for adding as many items as you want
		while(addMore == 1){
			InventoryController.displayCurrentInventory();
			int InvenIDnum = view.readIntWithPrompt("Enter inventory ID number: ");
			if(InvenIDnum == -1){
				System.out.println("Exit");
				return;
			}
			//make sure its a valid instock inventory item
			InvenIDnum = validateInvenNum(InvenIDnum);

			int orderAmount = view.readIntWithPrompt("Enter quantitiy of items: ");
			//make sure its a vaild quantitiy
			InventoryItem validateStock = invenDao.find((long)InvenIDnum);
			while(validateStock.getStock() < orderAmount || orderAmount <=0){
				if(orderAmount<=0){
					System.err.println("ERROR: O or less was entered.");
				}else{
					System.err.println("ERROR: Not enough stock.");
				}
				System.out.flush();
				orderAmount = view.readIntWithPrompt("Enter quantitiy of items: ");
			}
			orderTobeAdded.addItem(InvenIDnum, orderAmount);
			validateStock.removeStock(orderAmount);
			invenDao.update(validateStock);
			addMore = view.readIntWithPrompt("Add more Items? (1 = yes) (<1 = no): ");
		}
		orderDao.add(orderTobeAdded);
	}
	/**
	 * Display all orders associated with the customer
	 */
	public void displayOrders(){
		view.displayMenu("View all orders for a specific customer:");
		int custID = view.readIntWithPrompt("Enter customer ID number: ");
		//check if this is a vaild customer ID
		Customer temp = custDao.find((long)custID);
		while(temp == null){
			System.err.println("ERROR: Invalid customer ID number.");
			System.out.flush();
			custID = view.readIntWithPrompt("Enter customer ID number: ");
			temp = custDao.find((long)custID);
		}
		subMenu(temp, custID);
	}
	/**
	 * Displays and handles the submenu for orders
	 * @param temp the customer orders are being viewed on
	 * @param custID the customers id
	 */
	@SuppressWarnings("deprecation")
	private void subMenu(Customer temp, int custID) {
		Date beg_Date;
		Date end_Date;
		view.displayOrderMenu();
		List<Orders> printAll = orderDao.list();
		int choice = view.readIntWithPrompt("Enter choice: ");
		while(choice != CustAndIven_View.TIME && choice != CustAndIven_View.DEFAULT){
			System.err.println("ERROR: Invalid choice.");
			System.out.flush();
			choice = view.readIntWithPrompt("Enter choice: ");
		}
		if(choice == CustAndIven_View.TIME){
			System.out.println();
			String begDate = view.readStringWithPrompt("Enter begin date(MM/DD/YYYY): ");
			String endDate = view.readStringWithPrompt("Enter end date(MM/DD/YYYY): ");
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			while(true){
				try {

					beg_Date = formatter.parse(begDate);
					end_Date = formatter.parse(endDate);
					beg_Date.setHours(1);
					beg_Date.setMinutes(0);
					beg_Date.setSeconds(0);
					end_Date.setHours(23);
					end_Date.setMinutes(59);
					end_Date.setSeconds(59);
					break;

				} catch (ParseException e) {
					System.err.println("ERROR: Incorrect format for dates.\n");
					begDate = view.readStringWithPrompt("Enter begin date(MM/DD/YYYY): ");
					endDate = view.readStringWithPrompt("Enter end date(MM/DD/YYYY): ");
				}
			}
		}else{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			beg_Date = cal.getTime();
			end_Date = new Date();
		}
		System.out.println();
		System.out.println(temp);
		for(int x = 0; x<printAll.size(); x++){
			if(printAll.get(x).getCustID() == custID){
				Date itemDate = printAll.get(x).getDate();
				if(itemDate.compareTo(beg_Date) > 0 && itemDate.compareTo(end_Date) < 0 ){
					System.out.println(printAll.get(x));
				}
			}
		}

	}
	/**
	 * Takes a inventory id number and validates that it is a true inventory item
	 * and that it is in stock.
	 * @param InvenIDnum ID number to be verified
	 * @return The validated inventory number
	 */
	public int validateInvenNum(int InvenIDnum){
		//make sure the number is valid
		InventoryItem temp = invenDao.find((long)InvenIDnum);
		while(temp == null || temp.getStock() <= 0){
			if(temp == null){
				System.err.println("ERROR: Invalid inventory ID.");
			}else{
				System.err.println("ERROR: This item is out of stock.");
			}
			System.out.flush();
			InvenIDnum = view.readIntWithPrompt("Enter inventory ID number: ");
			temp = invenDao.find((long)InvenIDnum);
		}
		return InvenIDnum;

	}
}

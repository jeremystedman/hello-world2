package edu.usm.cos420.example1.view.impl;

import java.util.Scanner;
/**
 * 
 * @author Jeremy
 *
 */
public class CustAndIven_View {
	
    private Scanner in = new Scanner(System.in);
    public static final int TIME = 1;
    public static final int DEFAULT = 2;
    public CustAndIven_View(){
    	
    }
    /**
     * Displays the menu
     */
    public void displayMenu(String message){
    	System.out.println();
        System.out.println(message);
    }
    /**
     * Read int choice for menus
     * @param prompt: message to be displayed
     * @return
     */
    public int readIntWithPrompt (String prompt) {
        System.out.print(prompt); 
        System.out.flush();
        while(!in.hasNextInt()){
        	in.nextLine();
        }
        int choice = in.nextInt();
        in.nextLine();
        return choice;
      }
    /**
     * Read string for menus
     * @param prompt: the message to be displayed
     * @return
     */
    public String readStringWithPrompt(String prompt){
    	System.out.print(prompt); 
        System.out.flush();
        String choice = in.nextLine();
        return choice;
    }
    /**
     * Displays the orders submenu
     */
    public void displayOrderMenu(){
    	System.out.println();
    	System.out.println("Enter the number denoting the action");
		System.out.println("Choose time period.................."+TIME);
		System.out.println("Default(1 month)...................."+DEFAULT);
    }
}

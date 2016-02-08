package edu.usm.cos420.example1;


import edu.usm.cos420.example1.controller.MainMenuController;
import edu.usm.cos420.example1.view.impl.MainMenuView;

/**
 * Top level application class that coordinates the calls to view and Controller
 * @author Jeremy
 */
public class App
{
    /**
     * Entry point for application : calls {@link #provideCItemAccess()}
     * @param args  main program arguments, currently not used
     */
	public static void main( String[] args ){
		
		MainMenuView appView = new MainMenuView();
		MainMenuController mmCont = new MainMenuController(appView);
		mmCont.provideMMAccess();
    }
}

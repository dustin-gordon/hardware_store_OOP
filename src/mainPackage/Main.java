/**
 * @author Dustin Gordon
 * @version 1.0
 * @since   1.0
 * @see Inventory, Item
 */
package mainPackage;
import java.util.Scanner; // To capture user input 

/** 
 * Objects from the Main class model the user's command line interaction with the program, 
 * passing user inputs to operations within program. 
 * @author Dustin Gordon
 *
 */
public class Main {
	public static Inventory currentInventory = new Inventory(); 
	public static Scanner input = new Scanner(System.in);
	public static boolean repeatMenu = true;
	
	/**
	 * Infinite while-loop repeats Main Menu after user completes each task, 
	 * program only to be terminated by user.
	 */
	public static void main(String[] args) throws Exception {
		currentInventory.readFile();
		while ( repeatMenu ) {
			mainMenu();	
		}
		input.close();
	
	}
	
	/** 
	 * Method Menu shows the users their options to add, remove, or modify inventory items.
	 * @throws Exception 
	 */
	public static void mainMenu() throws Exception {
		System.out.println("		******************************");
		System.out.println("		* Hardware Inventory Manager *");
		System.out.println("		******************************");
		System.out.println("			* MAIN MENU * ");
		System.out.println(" - 1. Show all existing items in stock and their quantities.");
		System.out.println(" - 2. Add a new quantity of a specific item to the stock.");
		System.out.println(" - 3. Remove a certain quantity of a specific item type.");
		System.out.println(" - 4. Search for an item (given its name or part of its name).");
		System.out.println(" - 5. Show a list of all items below a certain quantity.");
		System.out.println(" - 6. Exit program. \n");
		System.out.print("Type the number for your choice: ");
		
		while(!input.hasNextInt()) {
			System.out.println("*Invalid menu choice. Enter a number 1-6: ");
			input.nextLine();
		}
		
		int menuChoice = Integer.parseInt(input.nextLine());
		switch(menuChoice) {
			case 1:	currentInventory.listExistingItems();
					break;	
			case 2:	currentInventory.addItem();
					break;	
			case 3:	currentInventory.removeItems();
					break;
			case 4: currentInventory.searchForItem();
					break;
			case 5: currentInventory.belowQuantity(); 
					break;
			case 6: input.close(); 
					currentInventory.saveToFile();
					repeatMenu = false; //Terminate program.
					break;
			default:System.out.println("\nInvalid Menu Choice. Enter choice between 1-6.\n"); //Ensures only integers 1-6 are entered. 
					input.close();
					break;
		}
		
	}

	
}
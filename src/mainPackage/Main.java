package hardwarestore;
import java.util.Scanner;

/** 
 * This is the Main class of the Hardware Inventory Manager. 
 * It facilitates the user's command line interaction with the program.
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   1.0
 */
public class Main {
	public static Inventory currentInventory = new Inventory();
	private static final Scanner INPUT = new Scanner(System.in);
	public static boolean repeatMenu = true;
	
	/**
	 * This method displays a dashed line, to be added at end of formatted table.
	 */
	public static final void tableLine() {
		System.out.println("---------------------------------------------------------------------------");
	}
	
	/**
	 * This method displays a neatly formatted table for each attribute of an inventory item.
	 */
	public static final void tableHeader() {
		String head1 = "ID #";
		String head2 = "NAME";
		String head3 = "QUANTITY";
		String head4 = "PRICE";
		String head5 = "CATEGORY/TYPE";
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(String.format("| %-6s | %20s | %8s | %8s | %17s |", head1, head2, head3, head4, head5));
		System.out.println("---------------------------------------------------------------------------");
	}
	
	/** 
	 * This method shows the user a console menu of options to add, remove, or modify inventory items.
	 * 
	 * @throws Exception 
	 */
	public static void mainMenu() throws Exception {
		System.out.println("   --------------------------------");
		System.out.println("   -- Hardware Inventory Manager --");
		System.out.println("--------------------------------------");
		System.out.println(" [1]. List items in stock (sort by ID).");
		System.out.println(" [2]. Add new item/increase quantity.");
		System.out.println(" [3]. Delete item/decrease quantity.");
		System.out.println(" [4]. Search for items by name.");
		System.out.println(" [5]. List all users in database.");
		System.out.println(" [6]. Add new user to database.");
		System.out.println(" [7]. Update user info (via their ID).");
		System.out.println(" [8]. Process sale transaction.");
		System.out.println(" [9]. List all completed transactions.");
		System.out.println(" [10]. Save database to file and Exit.");
		System.out.println("--------------------------------------");
		System.out.print("Type the number for your choice: ");
		
		if(!INPUT.hasNextInt()) {
			System.out.println("*Invalid menu choice. Enter a number 1-10* ");
			INPUT.nextLine();
			return;
		}
		int menuChoice = Integer.parseInt(INPUT.nextLine());
		if (menuChoice <= 0 || menuChoice > 10) {
			System.out.println("*Invalid menu choice. Enter a number 1-10: ");
			return;
		}
		switch(menuChoice) {
			case 1:	
				currentInventory.listExistingItems();
				break;	
			case 2:	
				System.out.println("- What type of item are you adding?");
				System.out.println(" [1]. Small Hardware Item.");
				System.out.println(" [2]. Appliance.");
				System.out.println("\n(Enter choice): ");
				if( !INPUT.hasNextInt()) { //Input validation
					System.out.println("*Invalid menu choice.* ");
					return;
				}
				int menuChoice2 = Integer.parseInt(INPUT.nextLine());
				if( menuChoice2 != 1 && menuChoice2 != 2) {
					System.out.println("*Invalid menu choice.* ");
					return;
				}
				switch(menuChoice2) {
				case 1:
					currentInventory.addSmallItemFromUser();
					break;
				case 2:
					currentInventory.addApplianceFromUser();
					break;
				}
				break;	
			case 3:
				currentInventory.removeQuantity();
				break;
			case 4:
				System.out.println("\n- Enter item name or part of name to search for: ");
				currentInventory.searchForItemByName(INPUT.nextLine());
				break;
			case 5:
				User.listExistingUsers();
				break;
			case 6: 
				User.addNewUser(); 
				break;
			case 7:
				User.updateInfo();
				break;
			case 8:
				currentInventory.sale();
				break;
			case 9:
				
				break;
			case 10: 
				INPUT.close(); 
				currentInventory.saveToFile();
				User.saveToFile();
				repeatMenu = false; //Terminate program.
				break;
			default:
				System.out.println("\nInvalid Menu Choice. Enter choice between 1-10.\n"); //Ensures only integers 1-6 are entered. 
				break;
		}
	}

	/**
	 * Main method contains infinite while-loop to repeat Main Menu after user completes/fails a task. 
	 * Program will terminate only user chooses to exit.
	 */
	public static void main(String[] args) throws Exception {
		currentInventory.readFile();
		User.readFile();
		while ( repeatMenu ) {
			mainMenu();	
		}
		INPUT.close();
	}
}
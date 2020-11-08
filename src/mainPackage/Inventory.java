/**
 * @author Dustin Gordon
 * @version 1.0
 * @since   1.0
 * @see Main, Item
 */
package mainPackage;
import java.util.ArrayList;
import java.io.*;

/**
 * Inventory class loads data into ArrayList, modifies it from user input sent by Main class, 
 * and saves ArrayList to text file before program terminates.
 * @author Dustin Gordon
 */
public class Inventory { 
	/**
	 * Constructor
	 */
	public Inventory() {
		
	}

	//Create array list to temporarily store active inventory until data is written to file at program's end. 
	private ArrayList<Item> inStockList = new ArrayList<Item>();
	
	/**
	 * The addItem method takes 5 fields (obtained from user by Main class) which Inventory will pass to Item.
	 * @param idInput
	 * @param nameInput
	 * @param catInput
	 * @param qtyInput
	 * @param priceInput
	 */
	public void addItem() {
		String idInput = "";
		String nameInput = "";
		String catInput = "";
		int qtyInput = 0;
		float priceInput = 0f;
		
		System.out.println("\n- Enter a name (less than 20 characters) for the new item being added to inventory:\n");
		nameInput = Main.input.nextLine();
		if (nameInput.length() > 20 ) {
			System.out.println("Invalid name. Cannot exceed 20 characters.\nReturning to Main Menu... \n");
			return; //Pass control flow back to main menu.
		}
		
		//Search ArrayList to see if item name already exists, if so prompts user to increase its quantity:
		String query = nameInput;
		for (int i = 0; i < inStockList.size(); i++) {
			String checkedName = inStockList.get(i).getName().toLowerCase();
			if (checkedName.contains(query)) { 
				System.out.println("\n- Pre-existing item found: ");
				System.out.println("  - " +inStockList.get(i).getName()+ " currently has a current quantity of " +inStockList.get(i).getQuantity()+ ".");
				System.out.println("- Enter number of items to add to current quantity: ");
				
				//Input validation:
				while(!Main.input.hasNextInt()) { 
					System.out.println("*Invalid input. Enter a number greater than 0: ");
					Main.input.nextLine();
				}
				int itemsToAdd = Integer.parseInt(Main.input.nextLine());
				
				int newQty = inStockList.get(i).getQuantity() + itemsToAdd;
				inStockList.get(i).setQuantity(newQty);
					
				System.out.println("- Successfully added " +itemsToAdd+ " " +inStockList.get(i).getName()+ " to inventory.");
				System.out.println("  - " +inStockList.get(i).getName()+ " now has a quantity of " +newQty+ ".");
				System.out.println("\nReturning to Main Menu.... \n");
				
				i = inStockList.size(); //Stops search function from finding multiple items containing queried substring.
			}
		}
		
		//Validate ID input
		boolean isValidID = false;
		while (isValidID == false) {
			System.out.println("\n- Enter an alphanumeric Item ID that is 5 characters long:\n");
			idInput = Main.input.nextLine();
			if (idInput.length() != 5) {
				isValidID = false;
			}
			else {
				isValidID = true;
			}
		}
		
		System.out.println("- Choose the category of the new item by typing one of the numbers below:\n");
		System.out.println("1. Door and Window");
		System.out.println("2. Cabinet and Furniture");
		System.out.println("3. Fasteners"); 
		System.out.println("4. Structural");
		System.out.println("5. Other");
		
		//Input validation:
		while(!Main.input.hasNextInt()) {
			System.out.println("*Invalid menu choice. Enter a number 1-5: ");
			Main.input.nextLine();
		}
		int catMenuChoice = Integer.parseInt(Main.input.nextLine());
		switch(catMenuChoice) { 
			case 1: catInput = "Door&Window";
					break;
			case 2: catInput = "Cabinet&Furniture";
					break;
			case 3: catInput = "Fasteners";
					break;
			case 4: catInput = "Structural";
					break;
			case 5: catInput = "Other"; 
					break;
			default: System.out.println("\nInvalid Menu Choice. 1-5 only.\nReturning to Main Menu... \n"); //Ensures only integers 1-6 are entered. 
					return;
		}
		
		System.out.println("Enter the quantity of the new item to be added:");
		while(!Main.input.hasNextInt()) {
			System.out.println("*Invalid input. Enter a number greater than 0: ");
			Main.input.nextLine();
		}
		qtyInput = Integer.parseInt(Main.input.nextLine()); 
		
		System.out.println("Enter the price of the new item in format 9.99:");
		
		while(!Main.input.hasNextFloat()) {
			System.out.println("*Invalid menu choice. Enter a number 1-5: ");
			Main.input.nextLine();
		}
		
		//Sets decimal precision to 2 points for price. Not very elegant but gets the job done:
		priceInput = Float.parseFloat(String.format("%.2f", Float.parseFloat(Main.input.nextLine()))); 
		
		//Take variables containing user input and pass to new Item object instantiated into array list:
		inStockList.add(new Item(idInput, nameInput, catInput, qtyInput, priceInput));
		System.out.println("\n(Successfully added x" + qtyInput + " of item \"" + nameInput + "\" to inventory.)\n");
	}
	
	/**
	 * The addFromFile method is the same as addItem except parameters are passed from substrings read from database.txt.
	 * @param ID item ID
	 * @param name item name
	 * @param category item category
	 * @param quantity item quantity
	 * @param price item price
	 */
	public void addFromFile(String ID, String name, String category, int quantity, float price) {
		inStockList.add(new Item(ID, name, category, quantity, price));
	}
	
	/**
	 * This method calls references Inventory ArrayList to display items in stock and their quantities.
	 */
	public void listExistingItems() {
		if (inStockList.isEmpty()) {
			System.out.println("\n(There are currently no items in stock.)\n");
		}
		else {
			String head1 = "ID #";
			String head2 = "NAME";
			String head3 = "CATEGORY";
			String head4 = "QUANTITY";
			String head5 = "PRICE";
			System.out.println("- Items currently in stock:");
			System.out.println("---------------------------------------------------------------------------");
			System.out.println(String.format("| %-6s | %20s | %17s | %8s | %8s |", head1, head2, head3, head4, head5));
			System.out.println("---------------------------------------------------------------------------");
			for ( Item item : inStockList) {
				System.out.println(String.format("| %-6s | %20s | %17s | %8s | %8.2f |", item.getID(), item.getName(),
						item.getCategory(), item.getQuantity(), item.getPrice()));
			}
			System.out.println("---------------------------------------------------------------------------");
			if (inStockList.size() == 1) {
				System.out.println("- There's a total of " + inStockList.size() + " item in stock. \n");
			}
			else {
				System.out.println("- There's a total of " + inStockList.size() + " items in stock. \n");
			}
		}
	}
	
	/**
	 * This method return all items in inventory below a user specified amount.
	 */
	public void belowQuantity() {
		boolean noneBelow = true;
		int threshold = 0;
		
		System.out.print("\nEnter minimum item quantity to check for: ");
		String inputString = null;
		while(!Main.input.hasNextInt()) {
			System.out.println("*Invalid input. Enter a number greater than 0: ");
			inputString = Main.input.nextLine();
		}
		threshold = Integer.parseInt(inputString);
		
		for( int i = 0; i < inStockList.size(); i++) {
			if( inStockList.get(i).getQuantity() < threshold ) {
				noneBelow = false;
				System.out.println("- Less than " +threshold+ " " +inStockList.get(i).getName()+ " in stock. (x" +inStockList.get(i).getQuantity()+ " currently)");
			}
		}
		if(noneBelow) {
			System.out.println("\nThere are no items in stock below " + threshold + " in stock. \n");
		}
	}

	/**
	 * This method searches for an existing item based on name or part of name based on user input
	 */
	public void searchForItem() {
		boolean nameFound = false;
		String checkedName = "";
		System.out.println("\n- Enter an item or part of item name to search for: ");
		
		String query = Main.input.nextLine();
		query = query.toLowerCase(); //Convert parameter to lower case for simpler search
		for (int i = 0; i < inStockList.size(); i++) {
			checkedName = inStockList.get(i).getName().toLowerCase();
			if (checkedName.contains(query)) {
				System.out.println("\n- Match found: ");
				System.out.println("  - Name: " +inStockList.get(i).getName());
				System.out.println("  - ID: " +inStockList.get(i).getID());
				System.out.println("  - Quantity: " +inStockList.get(i).getQuantity());
				System.out.println("  - Category: " +inStockList.get(i).getCategory());
				System.out.println("  - Price: $" +inStockList.get(i).getPrice());
				nameFound = true;
			}
		}
		
		if (nameFound == false) {
			System.out.println("\n- No items found named " +query+ ".");
			System.out.println("\nReturning to Main Menu... \n");
		}
		else {
			System.out.println("\nReturning to Main Menu... \n");
		}
	}

	/**
	 * This method removes a user specified quantity of an item.
	 */
	public void removeItems() {
		boolean nameFound = false;
		int itemsToRemove = 0;
		
		System.out.println("\n- Enter the item name whose quantity you wish to modify: ");
		String query = Main.input.nextLine();
		
		query = query.toLowerCase(); //Convert parameter to lower case for simpler search
		for (int i = 0; i < inStockList.size(); i++) {
			String checkedName = inStockList.get(i).getName().toLowerCase();
			if (checkedName.contains(query)) { 
				nameFound = true;
				System.out.println("\n- Match found: ");
				System.out.println("  - " +inStockList.get(i).getName()+ " currently has a current quantity of " +inStockList.get(i).getQuantity()+ ".");
				System.out.println("- Enter number of items to subtract from current quantity: ");
				//Input validation:
				while(!Main.input.hasNextInt()) { 
					System.out.println("*Invalid input. Enter a number greater than 0: ");
					Main.input.nextLine();
				}
				itemsToRemove = Integer.parseInt(Main.input.nextLine());
				
				if (itemsToRemove >= inStockList.get(i).getQuantity()) {
					inStockList.get(i).setQuantity(0); //Manually set to zero to prevent negative quantities.
				}
				else {
					int newQty = inStockList.get(i).getQuantity() - itemsToRemove;
					inStockList.get(i).setQuantity(newQty);
				}
				System.out.println("- Successfully removed " +itemsToRemove+ " items from " +inStockList.get(i).getName()+ ".");
				System.out.println("  - " +inStockList.get(i).getName()+ " now has a quantity of " +inStockList.get(i).getQuantity()+ ".");
				System.out.println("\nReturning to Main Menu.... \n");
				
				i = inStockList.size(); //Stops search function from finding multiple items containing queried substring.
			}
		}
		if (nameFound == false) {
			System.out.println("\n- No items found named " +query+ ".");
			System.out.println("\nReturning to Main Menu... \n");
		}
	}

	/**
	 * The readFile method checks if database.txt exists, and if so, it will read in the lines in an expected format and adds Item objects into Inventory's ArrayList.
	 * @throws IOException 
	 */
	public void readFile() throws IOException {
		try 
		{
			FileInputStream fstream = new FileInputStream("database.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				System.out.println(strLine);
				String[] splittedFile = strLine.split("~");
				String ID = splittedFile[0];
				String name = splittedFile[1];
				String category = splittedFile[2];
				int quantity = Integer.parseInt(splittedFile[3]);
				float price = Float.parseFloat(splittedFile[4]);
				addFromFile(ID, name, category, quantity, price);
			}
			System.out.println("- Successfully imported inventory from text file. Returning to Main Menu... \n");
			br.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("*ERROR* \nFile database.txt does not exist. Exiting program.");
			Main.repeatMenu = false;
		}
	}
		
	/**
	 * This method saves active inventory in the ArrayList to a text file
	 * @throws Exception in case of file IO error
	 */
	public void saveToFile() throws FileNotFoundException {
		try
		{
			PrintWriter outFile = new PrintWriter("database.txt");
			for (int i = 0; i < inStockList.size(); i++) {
	            outFile.println(inStockList.get(i).getID()+ "~" +inStockList.get(i).getName()+ "~" +inStockList.get(i).getCategory()+
	            		"~" +inStockList.get(i).getQuantity()+ "~" +inStockList.get(i).getPrice());
	        }
			outFile.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("*ERROR*\nCannot save to file. (File not found.)");
		}
	}

}

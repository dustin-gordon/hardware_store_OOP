package hardwarestore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Date;
import java.io.*;

/**
 * Inventory class loads data into ArrayList, modifies it from user input sent by Main class, 
 * and saves ArrayList to text file before program terminates.
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   1.0
 */
public class Inventory { 
	private static final Scanner INPUT = new Scanner(System.in);
	
	//Create array list to temporarily store active inventory until data is written to file at program's end: 
	public ArrayList<Item> itemList = new ArrayList<Item>();
	
	/**
	 * Constructor
	 */
	public Inventory() {
		
	}
	
	/**
	 * The addItem method takes 5 fields (passed by any other method) to instantiate an Item object.
	 * @param idInput
	 * @param nameInput
	 * @param qtyInput
	 * @param priceInput
	 */
	public void addSmallItem(String id, String name, int quantity, float price, String category) {
		itemList.add(new SmallItem(id, name, quantity, price, category));
	}
	
	/**
	 * The addItemFromUser method takes 5 fields (obtained from user in Main class) to instantiate an Item object.
	 * @param idInput
	 * @param nameInput
	 * @param catInput
	 * @param qtyInput
	 * @param priceInput
	 */
	public void addSmallItemFromUser() {
		String idInput;
		String nameInput;
		String catInput;
		int qtyInput = 0;
		float priceInput = 0f;
		
		System.out.println("Enter the ID of item: (5 Characters, Numbers/Letters only)");
        idInput = INPUT.nextLine();
        if (!idInput.matches("[A-Za-z0-9]{5}")) {
            System.out.println("*Input Error. ID Number must be 5 alphanumeric characters.*\n");
            return;
        }
        int itemIndex = findItem(idInput);
        if (itemIndex != -1) { // If item exists in the database

            System.out.println("Item exists: " +itemList.get(itemIndex).getName()+ ". Enter quantity to add:");
            int quantity = INPUT.nextInt();
            if (quantity <= 0) {
                System.out.println("*Invalid quantity. The amount to be added must be larger than 0.*\n");
                return;
            }
            addQuantity(itemIndex, quantity);
            return;
        } 
        else {
        	//If it reaches here, the item does not exist. We need to add new one.
            System.out.println("(Item with ID " +idInput+ " does not exist)\n");
        }
		System.out.println("\n- Enter item's name: (20 characters or less)\n");
		nameInput = INPUT.nextLine();
		if (nameInput.length() > 20 ) {
			System.out.println("*Invalid name. Cannot exceed 20 characters.*\n");
			return; //Pass control flow back to main menu.
		}
		//Search ArrayList to see if item name already exists:
		String query = nameInput;
		for (int i = 0; i < itemList.size(); i++) {
			String checkedName = itemList.get(i).getName().toLowerCase();
			if (checkedName.contains(query)) { 
				System.out.println("*Error. Item name already exists.*");
				return;
			}
		}
		System.out.println("- Choose the category of the new item by typing one of the numbers below:\n");
		System.out.println(" [1]. Door and Window");
		System.out.println(" [2]. Cabinet and Furniture");
		System.out.println(" [3]. Fasteners"); 
		System.out.println(" [4]. Structural");
		System.out.println(" [5]. Other");
		//Input validation:
		while(!INPUT.hasNextInt()) {
			System.out.println("*Invalid menu choice. Enter a number 1-5: ");
			INPUT.nextLine();
		}
		int catMenuChoice = Integer.parseInt(INPUT.nextLine());
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
			default: System.out.println("\n*Invalid Menu Choice. 1-5 only.*\nReturning to Main Menu... \n"); //Ensures only integers 1-6 are entered. 
					return;
		}
		System.out.println("Enter the quantity of the new item to be added:");
		while(!INPUT.hasNextInt()) {
			System.out.println("*Invalid input. Enter a number greater than 0: ");
			return;
		}
		qtyInput = Integer.parseInt(INPUT.nextLine()); 
		if (qtyInput <= 0) {
            System.out.println("*Invalid quantity. The amount to be added must be larger than 0.*\n");
            return;
        }
		
		System.out.println("Enter the price of the new item in format 9.99:");
		while(!INPUT.hasNextFloat()) {
			System.out.println("*Invalid menu choice. Enter a number 1-5: ");
			INPUT.nextLine();
		}
		//Sets decimal precision to 2 points for price:
		priceInput = Float.parseFloat(String.format("%.2f", Float.parseFloat(INPUT.nextLine()))); 
		if (priceInput <= 0f) {
            System.out.println("*Invalid quantity. The price to be set must be larger than 0.*\n");
            return;
        }
		
		//Take variables containing user input and pass to new Item object instantiated into array list:
		itemList.add(new SmallItem(idInput, nameInput, qtyInput, priceInput, catInput));
		System.out.println("\n(Successfully added x" + qtyInput + " of item \"" + nameInput + "\" to inventory.)\n");
    }
	
	/**
	 * The addApplianceFromUser method takes 6 fields (obtained from user in Main class) to instantiate an appliance object.
	 * @param idInput
	 * @param nameInput
	 * @param qtyInput
	 * @param priceInput
	 * @param brandInput
	 * @param typeInput
	 */
	public void addApplianceFromUser() {
		String idInput;
		String nameInput;
		int qtyInput = 0;
		float priceInput = 0f;
		String brandInput;
		String typeInput;
		
		//Get ID number input:
		System.out.println("Enter the ID of item: (5 Characters, Numbers/Letters only)");
        idInput = INPUT.nextLine();
        if (!idInput.matches("[A-Za-z0-9]{5}")) {
            System.out.println("*Input Error. ID Number must be 5 alphanumeric characters.*\n");
            return;
        }
        int itemIndex = findItem(idInput);
        if (itemIndex != -1) { // If item exists in the database

            System.out.println("Item exists: " +itemList.get(itemIndex).getName()+ ". Enter quantity to add:");
            int quantity = INPUT.nextInt();
            if (quantity <= 0) {
                System.out.println("*Invalid quantity. The amount to be added must be larger than 0.*\n");
                return;
            }
            addQuantity(itemIndex, quantity);
            return;
        } 
        else {
        	//If it reaches here, the item does not exist. We need to add new one.
            System.out.println("(Item with ID " +idInput+ " does not exist)\n");
        }
		System.out.println("\n- Enter item's name: (20 characters or less)\n");
		nameInput = INPUT.nextLine();
		if (nameInput.length() > 20 ) {
			System.out.println("*Invalid name. Cannot exceed 20 characters.*\n");
			return; //Pass control flow back to main menu.
		}
		//Search ArrayList to see if item name already exists:
		String query = nameInput;
		for (int i = 0; i < itemList.size(); i++) {
			String checkedName = itemList.get(i).getName().toLowerCase();
			if (checkedName.contains(query)) { 
				System.out.println("*Error. Item name already exists.*");
				return;
			}
		}
		//Get appliance type input:
		System.out.println("- Choose the appliance type:\n");
		System.out.println(" [1]. Refrigerators");
		System.out.println(" [2]. Washers and Dryers");
		System.out.println(" [3]. Ranges and Ovens"); 
		System.out.println(" [4]. Small Appliances");
		System.out.println("\n(Enter your choice): ");
		//Input validation:
		while(!INPUT.hasNextInt()) {
			System.out.println("*Invalid menu choice. Enter a number 1-4: ");
			INPUT.nextLine();
		}
		int catMenuChoice = Integer.parseInt(INPUT.nextLine());
		switch(catMenuChoice) { 
			case 1: typeInput = "Refrigerators";
					break;
			case 2: typeInput = "Washers&Dryers";
					break;
			case 3: typeInput = "Ranges&Ovens";
					break;
			case 4: typeInput = "SmallAppliances";
					break;
			default: System.out.println("\n*Invalid Menu Choice. 1-4 only.*\nReturning to Main Menu... \n"); 
					return;
		}
		//Get brand input:
		System.out.println("- Enter the brand of the appliance: ");
		brandInput = INPUT.nextLine();
		//Get quantity input:
		System.out.println("Enter the quantity of the new item to be added:");
		while(!INPUT.hasNextInt()) {
			System.out.println("*Invalid input. Enter a number greater than 0: ");
			return;
		}
		qtyInput = Integer.parseInt(INPUT.nextLine()); 
		if (qtyInput <= 0) {
            System.out.println("*Invalid quantity. The amount to be added must be larger than 0.*\n");
            return;
        }
		//Get price input:
		System.out.println("Enter the price of the new item in format 9.99:");
		while(!INPUT.hasNextFloat()) {
			System.out.println("*Invalid menu choice. Enter a number 1-5: ");
			INPUT.nextLine();
		}
		//Sets decimal precision to 2 points for price:
		priceInput = Float.parseFloat(String.format("%.2f", Float.parseFloat(INPUT.nextLine()))); 
		if (priceInput <= 0f) {
            System.out.println("*Invalid quantity. The price to be set must be larger than 0.*\n");
            return;
        }
		
		//Take variables containing user input and pass to new Appliance object instantiated into array list:
		itemList.add(new Appliance(idInput, nameInput, qtyInput, priceInput, brandInput, typeInput));
		System.out.println("\n(Successfully added x" + qtyInput + " of item \"" + nameInput + "\" to inventory.)\n");
    }
	
	/**
	 * This method sorts Inventory ArrayList by ID number then displays them and their attributes.
	 */
	public void listExistingItems() {
		if( itemList.isEmpty() ) {
			System.out.println("\n(There are currently no items in stock.)\n");
		}
		else {
			int stockCounter = 0;
			//Sort ArrayList by ID in Alphabetical Order:
			Collections.sort(itemList);
			
			//List sorted ArrayList
			Main.tableHeader();
			for( Item temp: itemList ) {
				stockCounter += temp.getQuantity();
				System.out.println(temp.toString());
			}
			Main.tableLine();
			System.out.println("- There's are " +stockCounter+ " items in stock. \n");
		}
	}
	
	/**
	 * This method return all items in inventory below a user specified amount.
	 */
	public void listBelowQuantity() {
		boolean noneBelow = true;
		int threshold;
		System.out.print("\nEnter minimum item quantity to check for: ");
		//Validate input is an integer:
		if( !INPUT.hasNextInt() ) {
			System.out.println("*Input Error. Must be positive integer.* ");
			return;
		}
		threshold = Integer.parseInt(INPUT.nextLine());
		//Validate integer input is positive and greater than 0:
		if( threshold <= 0 ) {
			System.out.println("*Invalid input. Must be greater than zero.*");
		}
		for( int i = 0; i < itemList.size(); i++) {
			if( itemList.get(i).getQuantity() < threshold ) {
				noneBelow = false;
				System.out.println("- Less than " +threshold+ " " +itemList.get(i).getName()+ 
						" in stock. (x" +itemList.get(i).getQuantity()+ " currently)");
			}
		}
		if( noneBelow ) {
			System.out.println("\nThere are no items in stock below " +threshold+ " in quantity. \n");
		}
	} 
		
	/**
	 * This method searches for an existing item based on name or part of name based on user input
	 */
	public void searchForItemByName(String query) {
		boolean nameFound = false;
		query = query.toLowerCase(); //Convert parameter to lower case for simpler search
		for (int i = 0; i < itemList.size(); i++) {
			if (itemList.get(i).getName().toLowerCase().contains(query)) {
				System.out.println("\n- Match found: ");
				Main.tableHeader();
				System.out.println(String.format("| %-6s | %20s | %8s | %8.2f | %17s |", itemList.get(i).getID(), itemList.get(i).getName(),
						itemList.get(i).getQuantity(), itemList.get(i).getPrice(), itemList.get(i).getCategory()));
				Main.tableLine();
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
	 * This method searches for an existing item based on ID number.
	 */
	public void searchForItemByID(String ID) {
        ArrayList<Item> temp = new ArrayList<Item>();
        for (Item tempItem : itemList) {
            if (tempItem.getID().toLowerCase().contains(ID)) {
                temp.add(tempItem);
            }
        }
        
        if (temp.size() == 0) {
            return;
        } 
        else {
        	String head1 = "ID #";
			String head2 = "NAME";
			String head3 = "CATEGORY";
			String head4 = "QUANTITY";
			String head5 = "PRICE";
        	System.out.println("---------------------------------------------------------------------------");
			System.out.println(String.format("| %-6s | %20s | %17s | %8s | %8s |", head1, head2, head3, head4, head5));
			System.out.println("---------------------------------------------------------------------------");
			for ( Item tempItem : itemList) {
				System.out.println(String.format("| %-6s | %20s | %17s | %8s | %8.2f |", tempItem.getID(), tempItem.getName(),
						tempItem.getCategory(), tempItem.getQuantity(), tempItem.getPrice()));
			}
			System.out.println("---------------------------------------------------------------------------");
        }
	}
	
	/**
	 * This method adds a user specified quantity of an item.
	 */
	public void addQuantity(int itemIndex, int addition) {
		Item temp = itemList.get(itemIndex);
        temp.setQuantity(temp.getQuantity() + addition);
        System.out.println("Quantity updated.\n");
	}
	
	/**
	 * This method removes a user specified quantity of an item.
	 */
	public void removeQuantity() {
		boolean nameFound = false;
		int itemsToRemove = 0;
		String query = null;
		System.out.println("\n- Enter the item name you wish to decrease or delete: ");
		query = INPUT.nextLine();
		query = query.toLowerCase(); //Convert parameter to lower case for simpler search
		
		for (int i = 0; i < itemList.size(); i++) {
			String checkedName = itemList.get(i).getName().toLowerCase();
			if (checkedName.contains(query)) { 
				nameFound = true;
				System.out.println("\n- Match found: \"" +itemList.get(i).getName()+ "\".");
				System.out.println("- Do you want to delete this item permanently or reduce its quantity? ");
				System.out.println(" [1]. Delete from database.");
				System.out.println(" [2]. Reduce quantity.");
				System.out.println("\n(Enter choice): ");
				while(!INPUT.hasNextInt()) { //Input validation
					System.out.println("*Invalid menu choice.* ");
					return;
				}
				int menuChoice = Integer.parseInt(INPUT.nextLine());
				switch(menuChoice) { 
					case 1: 
						itemList.remove(i); //Deletes item from ArrayList.
						System.out.println("- Item deleted successfully. Returning to Main Menu.... \n");
						return;
					case 2: 
						System.out.println("- " +itemList.get(i).getName()+ " has a current quantity of " +itemList.get(i).getQuantity()+ ".");
						System.out.println("Enter amount to subtract from inventory: ");
						//Input validation:
						while(!INPUT.hasNextInt()) { 
							System.out.println("*Invalid input. Must enter an integer > 0.*");
							return;
						}
						itemsToRemove = Integer.parseInt(INPUT.nextLine());
						if( itemsToRemove <= 0) {
				            System.out.println("*Invalid amount. Must enter an integer > 0.*\n");
				            return;
				        }
						if( itemsToRemove > itemList.get(i).getQuantity()) {
							System.out.println("*Invalid amount. Cannot subtract " +itemsToRemove+ " from " 
									+itemList.get(i).getQuantity()+ ".*\n");
							return;
						}
						int newQty = itemList.get(i).getQuantity() - itemsToRemove;
						itemList.get(i).setQuantity(newQty);
						System.out.println("- Successfully removed " +itemsToRemove+ " items from " +itemList.get(i).getName()+ ".");
						System.out.println("  - " +itemList.get(i).getName()+ " now has a quantity of " +itemList.get(i).getQuantity()+ ".");
						System.out.println("\nReturning to Main Menu.... \n");
						return;
					default:
						System.out.println("*Invalid menu choice.* ");
						break;
				}
			}
		}
		if (nameFound == false) {
			System.out.println("\n- No items found named " +query+ ".");
			System.out.println("\nReturning to Main Menu... \n");
		}
	}

	 /**
     * This method finds an item's index if it exists.
     * 
     * @param idNumber A String that represents the ID number of the item that to be searched for.
     * @return the integer index of the items in the ArrayList of items, or -1 if the search failed.
     */
	public int findItem(String idNumber) {
        int index = -1;
        for (int i = 0; i < itemList.size(); i++) {
            String temp = itemList.get(i).getID();
            if (idNumber.equalsIgnoreCase(temp)) {
                index = i;
                break;
            }
        }
        return index;
    }
	
	 /**
	 * The readFile method checks if database.ser exists, and if so, it will read in the lines in an expected format 
	 * and adds Item objects into Inventory's ArrayList.
	 * @throws IOException 
	 */
	public void readFile() throws IOException {
		try 
		{
			FileInputStream fstream = new FileInputStream("itemDatabase.ser");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				/* Line below uses "split" function to create an array of strings,  reading substring until "~" is reached, 
				 * then adds substring as an element and begins reading next substring for next element. */
				String[] splittedFile = strLine.split("~");				
				String ID = splittedFile[0];
				String name = splittedFile[1];
				String category = splittedFile[2];
				int quantity = Integer.parseInt(splittedFile[3]);
				float price = Float.parseFloat(splittedFile[4]);
				addSmallItem(ID, name, quantity, price, category);
			}
			System.out.println("(Successfully imported inventory from serialized object file.)\n");
			br.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("*ERROR* \nFile database.ser does not exist. Exiting program.");
			Main.repeatMenu = false;
		}
	}
		
	/**
	 * This method saves active inventory in the ArrayList to a serializable object file
	 * @throws Exception in case of file IO error
	 */
	public void saveToFile() throws FileNotFoundException {
		try
		{
			PrintWriter outFile = new PrintWriter("itemDatabase.ser");
			for (int i = 0; i < itemList.size(); i++) {
	            outFile.println(itemList.get(i).getID()+ "~" +itemList.get(i).getName()+ "~" +itemList.get(i).getCategory()+
	            		"~" +itemList.get(i).getQuantity()+ "~" +itemList.get(i).getPrice());
	        }
			outFile.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("*ERROR*\nCannot save to file. (File not found.)");
		}
	}

	/**
	 * Processes sale transactions.
	 */
	public void sale() throws FileNotFoundException{
		Date date = new Date();
		int quantity;
		int customerID;
		int employeeID;
		int index;
		
		System.out.println("- Enter Employee ID: ");
		employeeID = INPUT.nextInt();
		int userIndex = User.findUser(employeeID);
		if (userIndex == -1) {
			System.out.println("*ERROR. Employee ID not found.*");
		    return;
		}
		
		System.out.println("- Enter Customer ID: ");
		customerID = INPUT.nextInt();
		userIndex = User.findUser(customerID);
		if (userIndex == -1) {
			System.out.println("*ERROR. Customer ID not found.*");
		    return;
		}
		
		System.out.println("- Enter the item ID to sell: ");
		String itemID = INPUT.next();
		
		if(findItem(itemID) == -1) {
			System.out.println("*ERROR. Item does not exist.*\n");
			return;
		}
		else {
			index = findItem(itemID);
		}
		if(itemList.get(index).getQuantity() == 0) {
			System.out.println("*ERROR. Item is out of stock.*\n");
			return;
		}
		
		Item saleItem = itemList.get(index);
		System.out.println("- Item found: \"" +saleItem.getName()+ "\". " +saleItem.getQuantity()+ " currently in stock.");
		System.out.println("- Enter quantity to sell (integer): ");
		quantity = INPUT.nextInt();
		if(quantity <= 0 || quantity > saleItem.getQuantity()) {
			System.out.println("*ERROR. Invalid quantity.\n");
			return;
		}
		
		int newQty = itemList.get(index).getQuantity() - quantity;
		itemList.get(index).setQuantity(newQty);
		
		System.out.println("- Total cost due is $" +(saleItem.getPrice() * quantity)+ ". Please have customer render payment.");
		System.out.println("- Transaction complete! " +quantity+ " items deducted from inventory.");
		
		try
		{
			PrintWriter outFile = new PrintWriter("transDatabase.ser");
			for (int i = 0; i < itemList.size(); i++) {
	            outFile.println(date+ "~" +itemID+ "~" +quantity+ "~" +customerID+ "~" +employeeID);
	        }
			outFile.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("*ERROR*\nCannot save to file. (File not found.)");
		}
		
	}
}

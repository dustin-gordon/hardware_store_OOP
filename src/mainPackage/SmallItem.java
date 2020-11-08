package hardwarestore;

/**
 * The SmallItem class inherits methods and fields from Item class, 
 * and adds features specific to small hardware items (ie: Bolts, Doors, etc).
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   2.1
 */
public class SmallItem extends Item {
	private String category;
	
	/**
	* Invokes default constructor from superclass Item.
	* 
	* @param idNumber A String representing the item's unique ID. Exactly 5 characters long, numbers and/or letters only.
	* @param itemName A String representing the item's unique name. 20 or less characters long.
	* @param quantity A positive integer representing the amount of the item currently in stock.
	* @param itemPrice A floating point representing the item's price
	* @param category One of 5 predefined Strings representing the item's category.
	*/
	public SmallItem(String idNumber, String itemName, int quantity, float itemPrice, String category) {
		super(idNumber, itemName, quantity, itemPrice);
		this.category = category;
	}

	/**
	 * This method gets the item's category.
	 * 
	 * @return Item's category as a String.
	 * @override
	 */
	public String getCategory() {
		return category;
	}
	
	@Override
    public String toString() {
		return String.format("| %-6s | %20s | %8s | %8.2f | %17s |", idNumber, itemName, quantity, itemPrice, category);
    }

}
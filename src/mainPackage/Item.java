package hardwarestore;

/**
 * The Item class represents hardware store inventory items. 
 * Input validation of parameters is not handled directly by this class.
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   1.0
 */
public class Item implements Comparable<Item> {
	protected String idNumber;
	protected String itemName;
	protected int quantity;
	protected float itemPrice;
	
	/**
	 * Constructor which initializes Item objects.
	 * 
	 * @param idNumber A String representing the item's unique ID. Exactly 5 characters long, numbers and/or letters only.
	 * @param itemName A String representing the item's unique name. 20 or less characters long.
	 * @param quantity A positive integer representing the amount of the item currently in stock.
	 * @param itemPrice A floating point representing the item's price
	 * 
	 */
	public Item(String idNumber, String itemName, int quantity, float itemPrice) {
		this.idNumber = idNumber;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemPrice = itemPrice;
	}
	
	/**
	 * This method gets the item's identification number.
	 * 
	 * @return Item's ID number as a String.
	 */
	public String getID() {
		return idNumber;
	}
	
	/**
	 * This method gets the item's name.
	 * 
	 * @return Item's name as a String.
	 */
	public String getName() {
		return itemName;
	}
	
	/**
	 * This method gets the item's quantity.
	 * 
	 * @return Item's quantity as an integer.
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * This method gets the item's price
	 * .
	 * @return Item's price as a float.
	 */
	public float getPrice() {
		return itemPrice;
	}
	
	/**
	 * Meant to be overridden by SmallItem class, so that getCategory()
	 * can be called on an Item in ArrayList that happens to be SmallItem.
	 * 
	 * @return Test String
	 */
	public String getCategory() {
		String test = "*ERROR*";
		return test;
	}
	
	/**
	 * This method sets the item's quantity.
	 */
	public void setQuantity(int i) {
		quantity = i;
	}

	
	/**
	 * This compares Item objects based on ID.
	 * 
	 * @return Comparison of two Item IDs.
	 * @override
	 */
	public int compareTo(Item otherItem) {
		String otherID = ((Item)otherItem).getID();
		return idNumber.toLowerCase().compareTo(otherID.toLowerCase());
	}
	
	/**
	 * This method returns fields in neatly formatted table.
	 * 
	 * @return Formatted String of fields
	 * @override
	 */
	public String toString() {
		return String.format("| %-6s | %20s | %8s | %8.2f | %17s |", idNumber, itemName, quantity, itemPrice);
    }

}

	
/**
 * The item classes creates inventory items with and ID, name, category, quantity, and price
 * @author Dustin Gordon
 * @version 1.0
 * @since   1.0
 * @see Main, Inventory
 */
package mainPackage;

public class Item {
	private String idNumber = null;
	private String itemName = null;
	private String category = null;
	private int quantity = 0;
	private float itemPrice = 0;
	
	/**
	 * Constructor for Item class creates 5 fields attributed to hardware store inventory items. 
	 */
	public Item(String ID, String name, String cat, int qty, float price) {
		idNumber = ID;
		itemName = name;
		category = cat;
		quantity = qty;
		itemPrice = price;
	}
	
	//Below methods return an individual attribute of the inventory item:
	/**
	 * ID number accessor
	 * @return ID number
	 */
	public String getID() {
		return idNumber;
	}
	/**
	 * item name accessor
	 * @return item name
	 */
	public String getName() {
		return itemName;
	}
	/**
	 * item category accessor
	 * @return item category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * item quantity accessor
	 * @return item quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * item price accessor 
	 * @return item price
	 */
	public float getPrice() {
		return itemPrice;
	}
	/**
	 * item price accessor
	 * @param item price
	 */
	public void setQuantity(int i) {
		quantity = i;
	}
}
package hardwarestore;

/**
 * The Appliance class inherits methods and fields from Item class, 
 * and adds features specific to large hardware items (ie: Washer, dryer, etc).
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   2.1
 */
public class Appliance extends Item {
	private String brand;
	private String type;
	
	/**
	 * Invokes default constructor from superclass Item.
	 */
	public Appliance(String idNumber, String itemName, int quantity, float itemPrice, String brand, String type) {
		super(idNumber, itemName, quantity, itemPrice);
		this.brand = brand; 
		this.type = type;
	}

	/**
	 * This method gets the appliance's brand.
	 * 
	 * @return Appliance's brand as a String.
	 */
	public String getBrand() {
		return brand;
	}
	
	/**
	 * This method gets the appliance's type.
	 * 
	 * @return Appliance's type as a String.
	 */
	public String getType() {
		return type;
	}

	
	/**
	 * This method returns fields in neatly formatted table.
	 * 
	 * @return Formatted String of fields
	 * @override
	 */
	public String toString() {
		return String.format("| %-6s | %20s | %8s | %8.2f | %17s |", idNumber, itemName, quantity, itemPrice, type);
    }
}

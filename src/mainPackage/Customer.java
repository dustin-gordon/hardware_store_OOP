package hardwarestore;
import java.util.Scanner;

/**
 * This class is a subclass of abstract User. 
 * Adds attributes unique to Customer Users.
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   2.1
 */
public class Customer extends User {
	private static final Scanner INPUT = new Scanner(System.in);
	private String phone;
	private String address;
	
	/** 
	 * Invokes default constructor from superclass, 
	 * adds phone number and address parameters.
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param address
	 */
	public Customer(int id, String firstName, String lastName, String phone, String address) {
		super(id, firstName, lastName);
		this.phone = phone;
		this.address = address;
	}

	/**
	 * This method instantiates a new User object of subclass Customer.
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param phone
	 * @param address
	 */
	public void addCustomer(int id, String firstName, String lastName, String phone, String address) {
		userList.add(new Customer(id, firstName, lastName, phone, address));
	}
	
	/**
	 * This method prompts user for input to be used to create Customer object.
	 */
	public static void addCustomerFromInput() {
		int idInput;
		String firstNameInput;
		String lastNameInput;
		String phoneInput; 
		String addressInput; 
		
		//Get Customer ID from user:
		System.out.println("Enter new customer's ID (as int): ");
		if(!INPUT.hasNextInt()) { //Input validation
			System.out.println("*Invalid input.* ");
			return;
		}
		idInput = Integer.parseInt(INPUT.nextLine());
		if( idInput <= 0 ) {//Input validation
			System.out.println("*Invalid input.* ");
			return;
		}
		//Check if user ID exists:
		int userIndex = findUser(idInput);
		if (userIndex != -1) {
			System.out.println("*ERROR. User ID already exists.*");
	    	return;
	    }
		//Get Customer first name from user:
		System.out.println("Enter customer's first name (10 letters or less): ");
		firstNameInput = INPUT.nextLine();
		if( firstNameInput.length() > 10 ) {
			System.out.println("*Invalid name. Cannot exceed 10 characters.*\n");
			return; 
		}
		//Get Customer last name from user:
		System.out.println("Enter customer's last name (10 letters or less): ");
		lastNameInput = INPUT.nextLine();
		if( lastNameInput.length() > 10 ) {
			System.out.println("*Invalid name. Cannot exceed 10 characters.*\n");
			return; 
		}
		//Get Customer Phone number from user:
		System.out.println("Enter customer's phone number (10 digits, no dashes): ");
		phoneInput = INPUT.nextLine();
		if( !phoneInput.matches("[0-9]{10}") ) {
			System.out.println("*Invalid input. Must be 10 positive digits.* ");
			return;
		}
		//Get customer address from user:
		System.out.println("Enter customer's address (20 characters or less): ");
		addressInput = INPUT.nextLine();
		if( addressInput.length() > 20 ) {
			System.out.println("*Invalid address. Cannot exceed 20 characters.*\n");
			return; 
		}
		//Inputs must be validate to reach this point. Instantiate new Employee now:
		System.out.println("\n- New Customer has been successfully registered into database.\n");
		userList.add(new Customer(idInput, firstNameInput, lastNameInput, phoneInput, addressInput));
	}
	
	/**
	 * This method gets the Customer's phone number. 
	 * 
	 * @return Phone number, as a String
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * This method gets the Customer's address.
	 * 
	 * @return Address, as a String
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * This method changes the Customer's phone number.
	 */
	public void setPhone() {
		System.out.println("Enter customer's phone number (10 digits, no dashes): ");
		String phoneInput = INPUT.nextLine();
		if( !phoneInput.matches("[0-9]{10}") ) {
			System.out.println("*Invalid input. Must be 10 positive digits.* ");
			return;
		}
		this.phone = phoneInput;
		System.out.println("\n- Customer's phone number has been updated successfully.\n");

	}
	
	/**
	 * This method changes the customer's address.
	 */
	public void setAddress() {
		System.out.println("Enter customer's address (20 characters or less): ");
		String addressInput = INPUT.nextLine();
		if( addressInput.length() > 20 ) {
			System.out.println("*Invalid address. Cannot exceed 20 characters.*\n");
			return; 
		}
		this.address = addressInput;
		System.out.println("\n- Customer's address has been updated successfully.\n");

	}
	
	/**
	 * This method displays the beginning table header for neatly formatted Customer list.
	 */
	public static void tableHeader() {
		String head1 = "CUST. ID";
		String head2 = "NAME";
		String head3 = "PHONE #";
		String head4 = "ADDRESS";
		System.out.println("------------------------------------------------------------------------");
		System.out.println(String.format("| %4s | %20s | %11s | %20s |", head1, head2, head3, head4));
		System.out.println("------------------------------------------------------------------------");
	}
	
	/**
	 * This method returns fields of Customer object in neat formatted table.
	 * 
	 * @param index
	 */
	public void getFormatted() {
		System.out.println(String.format("| %8d | %10s %9s | %10s | %20s |", getID(), getFirstName(), 
				getLastName(), getPhone(), getAddress() ));
	}
	
	/**
	 * This method formats fields of Customer object for saving to file
	 * @return String
	 */
	public String formatForArchive() {
		String temp = getID()+ "~" +getFirstName()+ "~" +getLastName()+ "~" +getPhone()+ "~" +getAddress();
		return temp;
	}
	
}

package hardwarestore;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The User class provides base attributes of a user account
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   2.1
 */
public class User {
	private static final Scanner INPUT = new Scanner(System.in);
	protected int id;
	protected String firstName;
	protected String lastName;
	public static ArrayList<User> userList = new ArrayList<User>();
	
	/**
	 * Constructor to be invoked by subclasses.
	 * 
	 * @param ID User's identification number as an integer, assumed to be > 0.
	 * @param firstName User's first name as a String
	 * @param lastName User's last name as a String
	 */
	public User(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * This method sorts User ArrayList to find and display all users.
	 */
	public static void listExistingUsers() {
		if( userList.isEmpty() ) {
			System.out.println("\n(No registered users found.)\n");
			return;
		}
		else {
			boolean employeesExist = false;
			boolean customersExist = false;
			//Find if Employee users exist:
			for( int i = 0; i < userList.size(); i++ ) {
				if( userList.get(i) instanceof Employee) {
					employeesExist = true;
					break;
				}
			}
			//Find if Customer users exist:
			for( int i = 0; i < userList.size(); i++) {
				if( userList.get(i) instanceof Customer) {
					customersExist = true;
					break;
				}
			}
			//List existing Employees:
			if( employeesExist == true ) {
				System.out.println("- Current Employees: ");
				Employee.tableHeader();
				for( int i = 0; i < userList.size(); i++ ) {
					if( userList.get(i) instanceof Employee) {
						userList.get(i).getFormatted();
					}
				}
				System.out.println("------------------------------------------------------------");
			}
			//List existing Customers:
			if( customersExist == true ) {
				System.out.println("- Current Customers:");
				Customer.tableHeader();
				for( int i = 0; i < userList.size(); i++ ) {
					if( userList.get(i) instanceof Customer) {
						userList.get(i).getFormatted();
					}
				}
				System.out.println("------------------------------------------------------------------------");
			}
		}
	}
	
	/**
	 * This method determines what type of user is being added, then 
	 * passes control to appropriate method to instantiate correct user.
	 */
	public static void addNewUser() {
		System.out.println("- What type of user are you registering?");
		System.out.println(" [1]. New Employee.");
		System.out.println(" [2]. New Customer.");
		System.out.println("\n(Enter choice): ");
		if( !INPUT.hasNextInt()) { //Input validation
			System.out.println("*Invalid menu choice.* ");
			return;
		}
		int menuChoice = Integer.parseInt(INPUT.nextLine());
		if( menuChoice != 1 && menuChoice != 2) {
			System.out.println("*Invalid menu choice.* ");
			return;
		}
		switch(menuChoice) {
		case 1:
			Employee.addEmployeeFromInput();
			break;
		case 2:
			Customer.addCustomerFromInput();
			break;
		}
	}
	
	/**
	 * This method returns the User's ID
	 * 
	 * @return ID, as integer
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * This method returns the User's first name
	 * 
	 * @return First Name, as String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * This method returns the User's last name
	 * 
	 * @return Last Name, as String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This method is to be overridden in Employee and Customer subclasses.
	 */
	public void getFormatted() {
		//Abstract method
	}

	
	/**
	 * This method changes the user's first name.
	 */
	public void setFirstName() {
		System.out.println("Enter user's first name (10 letters or less): ");
		String firstNameInput = INPUT.nextLine();
		if( firstNameInput.length() > 10 ) {
			System.out.println("*Invalid name. Cannot exceed 10 characters.*\n");
			return; 
		}
		this.firstName = firstNameInput;
		System.out.println("\n- User's first name has been updated successfully.\n");
	}
	
	/**
	 * This method changes the user's last name.
	 */
	public void setLastName() {
		System.out.println("Enter user's last name (10 letters or less): ");
		String lastNameInput = INPUT.nextLine();
		if( lastNameInput.length() > 10 ) {
			System.out.println("*Invalid name. Cannot exceed 10 characters.*\n");
			return; 
		}
		this.lastName = lastNameInput;
		System.out.println("\n- User's last name has been updated successfully.\n");

	}
	
	/**
	 * This method modifies existing customer or employee info.
	 */
	public static void updateInfo() {
		System.out.println("- Enter user ID (integer): ");
		int idInput = INPUT.nextInt();
		int userIndex;
		if(findUser(idInput) == -1) {
        	System.out.println("*No user found with ID " +idInput+ ".");
        	return;
		}
		else {
			userIndex = findUser(idInput);
		}
		
		if(userList.get(userIndex) instanceof Employee) {
			Employee tempEmp = (Employee) userList.get(userIndex);
			
			System.out.println("- Selected user is an employee.");
			System.out.println("- Choose field to modify: ");
			System.out.println(" [1]. First Name");
			System.out.println(" [2]. Last Name");
			System.out.println(" [3]. Social Security Number");
			System.out.println(" [4]. Monthly Salary");
			System.out.println("\n(Enter your choice): ");
			if(!INPUT.hasNextInt()) {
				System.out.println("*Invalid menu choice.* ");
				return;
			}
			int menuChoice = INPUT.nextInt();
			if (menuChoice <= 0 || menuChoice > 10) {
				System.out.println("*Invalid menu choice.* ");
				return;
			}
			switch(menuChoice) {
			case 1:
				tempEmp.setFirstName();
				break;
			case 2:
				tempEmp.setLastName();
				break;
			case 3: 
				tempEmp.setSSN();
				break;
			case 4:
				tempEmp.setSalary();
				break;
			default:
				System.out.println("*Invalid menu choice.* ");
				return;
			}
			
			if(userList.get(userIndex) instanceof Customer) {
				Customer tempCust = (Customer) userList.get(userIndex);
				
				System.out.println("- Selected user is an Customer.");
				System.out.println("- Choose field to modify: ");
				System.out.println(" [1]. First Name");
				System.out.println(" [2]. Last Name");
				System.out.println(" [3]. Phone Number");
				System.out.println(" [4]. Address");
				System.out.println("\n(Enter your choice): ");
				if(!INPUT.hasNextInt()) {
					System.out.println("*Invalid menu choice.* ");
					return;
				}
				int menuChoice2 = Integer.parseInt(INPUT.nextLine());
				if (menuChoice2 <= 0 || menuChoice2 > 10) {
					System.out.println("*Invalid menu choice.* ");
					return;
				}
				switch(menuChoice2) {
				case 1:
					tempCust.setFirstName();
					break;
				case 2:
					tempCust.setLastName();
					break;
				case 3: 
					tempCust.setPhone();
					break;
				case 4:
					tempCust.setAddress();
					break;
				default:
					System.out.println("*Invalid menu choice.* ");
					return;
				}
			}
		}
	}
	
	 /** This method finds an user's index if they exists.
     * 
     * @param idNumber A String that represents the ID number of the user to be searched for.
     * @return the integer index of the user in the ArrayList of items, or -1 if the search failed.
     */
	public static int findUser(int idNumber) {
        int index = -1;
        for (int i = 0; i < userList.size(); i++) {
            int temp = userList.get(i).getID();
            if (idNumber == temp) {
                index = i;
                break;
            }
        }
        return index;
    }
	
	/**
	 * The readFile method checks if userDatabase.ser exists, and if so, it will read in the lines in an expected format 
	 * and adds User objects into ArrayList.
	 * @throws IOException 
	 */
	public static void readFile() throws IOException {
		try 
		{
			FileInputStream fstream = new FileInputStream("userDatabase.ser");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				String[] splittedFile = strLine.split("~");
				String id = splittedFile[0];
				String firstName = splittedFile[1];
				String lastName = splittedFile[2];
				String ssnOrPhone = splittedFile[3];
				String salaryOrAddress = splittedFile[4];
				boolean isEmployee;
				try
				{
				  Float.parseFloat(salaryOrAddress);
				  isEmployee = true;
				}
				catch(NumberFormatException notFloat)
				{
				  isEmployee = false;
				}
				
				if(isEmployee) {
					userList.add(new Employee(Integer.parseInt(id), firstName, lastName, Integer.parseInt(ssnOrPhone), Float.parseFloat(salaryOrAddress)));
				}
				else {
					userList.add(new Customer(Integer.parseInt(id), firstName, lastName, ssnOrPhone, salaryOrAddress));
				}
			}
			System.out.println("(Successfully imported users database.)\n");
			br.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("*ERROR* \nFile userDatabase.ser does not exist. Exiting program.");
			Main.repeatMenu = false;
			return;
		}
	}

	/**
	 * This method saves active users in the ArrayList to a serializable object file
	 * @throws Exception in case of file IO error
	 */
	public static void saveToFile() throws FileNotFoundException {
		try
		{
			PrintWriter outFile = new PrintWriter("userDatabase.ser");
			for (int i = 0; i < userList.size(); i++) {
				if(userList.get(i) instanceof Employee) {
					//save employee to file
					Employee temp = (Employee) userList.get(i);
					outFile.println(temp.formatForArchive());
				}
				if(userList.get(i) instanceof Customer) {
					//save customer to file
					Customer temp = (Customer) userList.get(i);
					outFile.println(temp.formatForArchive());
				}
	        }
			outFile.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("*ERROR*\nCannot save to file. (File not found.)");
		}
	}

	
}

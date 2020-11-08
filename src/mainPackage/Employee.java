package hardwarestore;
import java.util.Scanner;

/**
 * This class is a subclass of abstract User. 
 * Adds attributes unique to Employee Users.
 * 
 * @author Dustin Gordon
 * @version 2.1
 * @since   2.1
 */
public class Employee extends User{
	private static final Scanner INPUT = new Scanner(System.in);
	private int ssn;
	private float monthlySalary;
	
	/**
	 * Invokes default constructor from superclass, 
	 * adds SSN and salary parameters.
	 * 
	 * @param ID
	 * @param firstName
	 * @param lastName
	 * @param ssn
	 * @param monthlySalary
	 */
	public Employee(int id, String firstName, String lastName, int ssn, float monthlySalary) {
		super(id, firstName, lastName);
		this.ssn = ssn;
		this.monthlySalary = monthlySalary;
	}

	/**
	 * This method prompts user for input to be used to create Employee object.
	 */
	public static void addEmployeeFromInput() {
		int idInput;
		String firstNameInput;
		String lastNameInput;
		String ssnInput; //To be type-casted to integer.
		float salaryInput; //To be type-casted to float.
		
		//Get Employee ID from user:
		System.out.println("Enter new employee's ID (as int): ");
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
		//Get Employee first name from user:
		System.out.println("Enter employee's first name (10 letters or less): ");
		firstNameInput = INPUT.nextLine();
		if( firstNameInput.length() > 10 ) {
			System.out.println("*Invalid name. Cannot exceed 10 characters.*\n");
			return; 
		}
		//Get Employee last name from user:
		System.out.println("Enter employee's last name (10 letters or less): ");
		lastNameInput = INPUT.nextLine();
		if( lastNameInput.length() > 10 ) {
			System.out.println("*Invalid name. Cannot exceed 10 characters.*\n");
			return; 
		}
		//Get Employee SSN from user:
		System.out.println("Enter employee's Social Security Number (9 digits, no dashes): ");
		ssnInput = INPUT.nextLine();
		if( ssnInput.toString().length() != 9 || Integer.parseInt(ssnInput) <= 0 ) {
			System.out.println("*Invalid input. Must be 9 positive digits.* ");
			return;
		}
		//Get Employee monthly salary from user:
		System.out.println("Enter employee's monthly salary (float, ie 1999.99): ");
		
		//NEED TO validate input
		salaryInput = INPUT.nextFloat();
		if( salaryInput <= 0f ) {
			System.out.println("*Invalid input. Salary must be positive, otherwise no one would work here!!* ");
			return;
		}
		
		//Inputs must be validate to reach this point. Instantiate new Employee now:
		System.out.println("\n- New Employee has been successfully registered into database.\n");
		userList.add(new Employee(idInput, firstNameInput, lastNameInput, Integer.parseInt(ssnInput), salaryInput));
	}
	
	/**
	 * This method returns the Employee's SSN.
	 * 
	 * @return SSN, as integer
	 */
	public int getSSN() {
		return ssn;
	}
	
	/**
	 * This method returns the Employee's monthly salary.
	 * 
	 * @return Monthly Salary, as a float. 
	 */
	public float getMonthlySalary() {
		return monthlySalary;
	}

	/**
	 * This method changes the employee's SSN.
	 */
	public void setSSN() {
		System.out.println("Enter employee's Social Security Number (9 digits, no dashes): ");
		String ssnInput = INPUT.nextLine();
		if( ssnInput.toString().length() != 9 || Integer.parseInt(ssnInput) <= 0 ) {
			System.out.println("*Invalid input. Must be 9 positive digits.* ");
			return;
		}
		this.ssn = Integer.parseInt(ssnInput);
		System.out.println("\n- Employee's SSN has been updated successfully.\n");

	}
	
	/**
	 * This method changes the employee's salary.
	 */
	public void setSalary() {
		System.out.println("Enter employee's monthly salary (float, ie 1999.99): ");
		float salaryInput = INPUT.nextFloat();
		if( salaryInput <= 0f ) {
			System.out.println("*Invalid input. Salary must be positive, otherwise no one would work here!!* ");
			return;
		}
		this.monthlySalary = salaryInput;
		System.out.println("\n- Employee's monthly salary been updated successfully.\n");

	}
	
	/**
	 * This method displays the beginning table header for neatly formatted Employee list.
	 */
	public static void tableHeader() {
		String head1 = "EMPL. ID";
		String head2 = "NAME";
		String head3 = "SSN";
		String head4 = "SALARY";
		System.out.println("------------------------------------------------------------");
		System.out.println(String.format("| %4s | %20s | %9s | %10s |", head1, head2, head3, head4));
		System.out.println("------------------------------------------------------------");
	}
	
	/**
	 * This method returns fields of Employee object in neat formatted table.
	 * 
	 * @param index
	 */
	public void getFormatted() {
		System.out.println(String.format("| %8d | %10s %9s | %8d | %10.2f |", getID(), getFirstName(), 
				getLastName(), getSSN(), getMonthlySalary() ));
	}
	
	
	/**
	 * This method formats fields of Employee object for saving to file
	 * @return String
	 */
	public String formatForArchive() {
		String temp = getID()+ "~" +getFirstName()+ "~" +getLastName()+ "~" +getSSN()+ "~" +getMonthlySalary();
		return temp;
	}
}

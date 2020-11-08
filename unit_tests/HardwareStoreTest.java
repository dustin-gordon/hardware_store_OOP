package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hardwarestore.HardwareStore;
import hardwarestore.Item;

public class HardwareStoreTest {
	
	public static HardwareStore hardwareStore = null;

	@BeforeClass
	public static void createEnvironment() throws IOException {
		//HardwareStore hardwareStore = new HardwareStore();
		System.out.println("Set Up Environment.");
	}
	
	@AfterClass
	public static void clearEnvironment() {
		hardwareStore = null;
		System.out.println("Cleared Environment.");
	}
	
	@Before
	public void createMethodEnvironment() {
		try {
			hardwareStore = new HardwareStore();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Set Up Method Environment.");  
	}
	
	@After
	public void clearMethodEnvironment() {
		hardwareStore = null;
		System.out.println("Cleared Method Environment."); 
	}
	
	@Test
	public void testGetAllItemsFormatted() {
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 100, 24.99f); //Instantiate test object.
		
		//Test result is not empty:
		assertNotNull("Outputted String table must not be empty.", hardwareStore.getAllItemsFormatted());
		
		//Test simple find by name query:
		String expectedOutput =
				" ------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-25s| %-20s| %-10s| %-10s|%n", "ID Number", "Name", "Category", "Quantity", "Price") +
                " ------------------------------------------------------------------------------------\n"
                + String.format("| %-10s| %-25s| %-20s| %-10s| %-10s|%n",
				 "AB123",
				 "Generic Item",
				 "Door&Windows",
                 Integer.toString(100),
                 String.format("%.2f", 24.99f))
                + " ------------------------------------------------------------------------------------\n";
		String testOutput1 = hardwareStore.getAllItemsFormatted();
		assertEquals("Outputted String table is not formatted correctly.", expectedOutput, testOutput1);
	}

	@Test
	public void testAddNewItem() throws IOException {
		HardwareStore testArray = new HardwareStore();
		testArray.addNewItem("AB123", "Generic Item", "Door&Windows", 99, 24.99f);
		
		//Test simple add item:
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 99, 24.99f);
		if (hardwareStore.findItem("AB123") == -1) {
			fail("Could not add object to array.");
		}
		
		//Test added item is not empty:
		assertNotNull("Failed to pass proper fields to instantiated object.", hardwareStore.getItem(0));
		
		//Test added item for correct ID Number:
		assertEquals("Failed to instantiate object with correct ID Number.", testArray.getItem(0).getIdNumber(),  hardwareStore.getItem(0).getIdNumber());
		
		//Test added item for correct Name:
		assertEquals("Failed to instantiate object with correct Name.", testArray.getItem(0).getName(),  hardwareStore.getItem(0).getName());
		
		//Test added item for correct Category:
		assertEquals("Failed to instantiate object with correct Category.", testArray.getItem(0).getCategory(), hardwareStore.getItem(0).getCategory());
		
		//Test added item for correct Quantity:
		assertEquals("Failed to instantiate object with correct Quantity.", testArray.getItem(0).getQuantity(), hardwareStore.getItem(0).getQuantity());
		
		//Test added item for correct Price, to a decimal precision of 2 places:
		assertEquals("Failed to instantiate object with correct Price.", testArray.getItem(0).getPrice(), hardwareStore.getItem(0).getPrice(), 2);
		
	}

	@Test
	public void testAddQuantity() {
		//Test simple add quantity:
		hardwareStore.addNewItem("AB123", "Generic Item 1", "Door&Windows", 100, 24.99f); // Initial quantity is 100, array index is [0].
		hardwareStore.addQuantity(0, 100);
		assertEquals("Quantity of 100 plus 100 must be 200.", 200, hardwareStore.getItem(0).getQuantity());
		
		//Test large add quantity:
		hardwareStore.addNewItem("54321", "Generic Item 2", "Structural", 0, 19.95f); // Initial quantity is 0, array index is [1].
		hardwareStore.addQuantity(1, 999999999);
		assertEquals("Quantity of 0 plus 999,999,999 must be 999,999,999.", 999999999, hardwareStore.getItem(1).getQuantity());
		
		//Test negative add quantity:
		hardwareStore.addNewItem("Zxy00", "Generic Item 2", "Other", 5, 9.99f); // Initial quantity is 5, array index is [2].
		hardwareStore.addQuantity(2, -10);
		assertEquals("Adding negative quantities should not be allowed.", 0, hardwareStore.getItem(2).getQuantity());
	}

	@Test
	public void testRemoveQuantity() {
		//Test simple remove quantity:
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 100, 24.99f); // Initial quantity is 100, array index is [0].
		hardwareStore.removeQuantity(0, 100);
		assertEquals("Quantity of 100 minus 100 must be 0.", 0, hardwareStore.getItem(0).getQuantity());
		
		//Test quantity removal past zero:
		hardwareStore.addNewItem("54321", "Generic Item 2", "Structural", 0, 19.95f); // Initial quantity is 0, array index is [1].
		hardwareStore.removeQuantity(1, 1); // Testing removal of quantity from empty quantity. 
		assertEquals("Empty quantity minus 1 must remain 0, not go negative.", 0,  hardwareStore.getItem(1).getQuantity());
	}

	@Test
	public void testGetMatchingItemsByName() {
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 100, 24.99f); //Instantiate test object.
		
		//Test result is not empty:
		assertNotNull("Outputted String table must not be empty.", hardwareStore.getMatchingItemsByName("AB123"));
		
		//Test simple find by name query:
		String expectedOutput =
				" ------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-25s| %-20s| %-10s| %-10s|%n", "ID Number", "Name", "Category", "Quantity", "Price") +
                " ------------------------------------------------------------------------------------\n"
                + String.format("| %-10s| %-25s| %-20s| %-10s| %-10s|%n",
				 "AB123",
				 "Generic Item",
				 "Door&Windows",
                 Integer.toString(100),
                 String.format("%.2f", 24.99f))
                + " ------------------------------------------------------------------------------------\n";
		String testOutput1 = hardwareStore.getMatchingItemsByName("AB123");
		assertEquals("Outputted String table is not formatted correctly.", expectedOutput, testOutput1);
		
		//Test for nonexistent quantity:
		String testOutput2 = hardwareStore.getMatchingItemsByName("Tortellini");
		assertNull("Nonexistent quantity should return null.", testOutput2);
		
		//Test for partial string search:
		String testOutput3 = hardwareStore.getMatchingItemsByName("AB");
		assertEquals("Partial string should still return item.", expectedOutput, testOutput3);
	}

	@Test
	public void testGetMatchingItemsByQuantity() {
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 100, 24.99f); //Instantiate test object.
		//Test result is not empty:
		assertNotNull("Outputted String table must not be empty.", hardwareStore.getMatchingItemsByQuantity(100));
		
		//Test simple find by quantity query:
		String expectedOutput =
				" ------------------------------------------------------------------------------------\n" +
                String.format("| %-10s| %-25s| %-20s| %-10s| %-10s|%n", "ID Number", "Name", "Category", "Quantity", "Price") +
                " ------------------------------------------------------------------------------------\n"
                + String.format("| %-10s| %-25s| %-20s| %-10s| %-10s|%n",
				 "AB123",
				 "Generic Item",
				 "Door&Windows",
                 Integer.toString(100),
                 String.format("%.2f", 24.99f))
                + " ------------------------------------------------------------------------------------\n";
		String testOutput1 = hardwareStore.getMatchingItemsByQuantity(100);
		assertEquals("Outputted String table is not formatted correctly.", expectedOutput, testOutput1);
		
		//Test for nonexistent quantity:
		String testOutput2 = hardwareStore.getMatchingItemsByQuantity(0);
		assertNull("Nonexistent quantity should return null.", testOutput2);
	}

	//The findItem() method returns index of item found in array based off ID Number, or returns -1 if no item is found:
	@Test 
	public void testFindItem() { 
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 100, 24.99f); //Instantiate test object.
		
		//Simple find item test:
		int testOutput1 = hardwareStore.findItem("AB123");
		assertEquals("Expected to find item at index [0].", 0, testOutput1);
		
		//Test when item should NOT be found:
		int testOutput2 = hardwareStore.findItem("Tortellini");
		assertEquals("Deliberately nonexistent ID Number query should not be found in array.", -1, testOutput2);
		
		//Test empty search query:
		int testOutput3 = hardwareStore.findItem("");
		assertEquals("Deliberately empty ID Number query should not be found in array.", -1, testOutput3);
	}

	@Test
	public void testGetItem() {
		hardwareStore.addNewItem("AB123", "Generic Item", "Door&Windows", 100, 24.99f); //Instantiate test object.
		
		//Simple get item by index test (when item return is expected):
		Item testOutput1 = hardwareStore.getItem(0);
		assertNotNull("No returned value.", testOutput1);
		
		//Test if returned item is correct object:
		assertTrue("Return value is wrong object type.", (testOutput1 instanceof Item));
		
		//Test with negative index query:
		Item testOutput2 = hardwareStore.getItem(-1);
		assertNull("Negative index should return null.", testOutput2);
	}

}

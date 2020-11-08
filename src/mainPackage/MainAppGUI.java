package hardwarestore.modelview;

import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import hardwarestore.modelview.HardwareStore;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.util.logging.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;



public class MainAppGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(MainAppGUI.class.getName());
	Handler fileHandler = new FileHandler("Assign_5_Log.log");
	//LOGGER.addHandler(fileHandler);
	//fileHandler.setLevel(Level.ALL);
	//LOGGER.setLevel(Level.ALL);
	//LOGGER.config("Configuration done.");
	private JPanel contentPane;
	private final HardwareStore hardwareStore;
    private static final Scanner CONSOLE_INPUT = new Scanner(System.in);
    
	//Main method to launch program:
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGGER.log(Level.INFO, "Beginning main thread.");
					MainAppGUI frame = new MainAppGUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.log(Level.SEVERE, "Failed to launch main thread.");
				}
			}
		});
		
	}

	//Constructor:
	public MainAppGUI() throws IOException {
		hardwareStore = new HardwareStore();
		initialize();
		LOGGER.log(Level.INFO, "Instantiated startup window.");
	}
	
	private void initialize() {
		setTitle("Hardware Store GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		
		//Center window to any sized screen:
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		this.setLocation(xPos, yPos);
		
		//Initialize and add components, add event handler:
		EventListener handler = new EventListener();
		
		LOGGER.log(Level.INFO, "Adding startup components...");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.RED);
		setJMenuBar(menuBar);
		
		JLabel labelManage = new JLabel("Manage: ");
		menuBar.add(labelManage);
		
		JMenu menuItems = new JMenu("ITEMS ");
		menuItems.setBackground(Color.RED);
		menuBar.add(menuItems);
		
		JMenuItem menuItemListAllItems = new JMenuItem("List All Items");
		menuItems.add(menuItemListAllItems);
		menuItemListAllItems.setActionCommand("List All Items");
		menuItemListAllItems.addActionListener(handler);
		
		JMenuItem menuItemAddNewItemQuantity = new JMenuItem("Add New Item/Quantity");
		menuItems.add(menuItemAddNewItemQuantity);
		menuItemAddNewItemQuantity.setActionCommand("Add New Item/Quantity");
		menuItemAddNewItemQuantity.addActionListener(handler);
		
		JMenuItem menuItemDeleteItem = new JMenuItem("Delete Item"); 
		menuItems.add(menuItemDeleteItem);
		menuItemDeleteItem.setActionCommand("Delete Item");
		menuItemDeleteItem.addActionListener(handler);
		
		JMenuItem menuItemSearchForItem = new JMenuItem("Search for Item");
		menuItems.add(menuItemSearchForItem);
		menuItemSearchForItem.setActionCommand("Search for Item");
		menuItemSearchForItem.addActionListener(handler);
		
		JMenu menuUsers = new JMenu("USERS ");
		menuBar.add(menuUsers);
		
		JMenuItem menuItemListAllUsers = new JMenuItem("List All Users");
		menuUsers.add(menuItemListAllUsers);
		menuItemListAllUsers.setActionCommand("List All Users");
		menuItemListAllUsers.addActionListener(handler);
		
		JMenuItem menuItemAddNewUser = new JMenuItem("Add New User");
		menuUsers.add(menuItemAddNewUser);
		menuItemAddNewUser.setActionCommand("Add New User");
		menuItemAddNewUser.addActionListener(handler);
		
		JMenuItem menuItemUpdateUserInfo = new JMenuItem("Update User Info");
		menuUsers.add(menuItemUpdateUserInfo);
		menuItemUpdateUserInfo.setActionCommand("Update User Info");
		menuItemUpdateUserInfo.addActionListener(handler);
		
		JMenu menuSales = new JMenu("SALES ");
		menuBar.add(menuSales);
		
		JMenuItem menuItemNewSaleTransaction = new JMenuItem("New Transaction");
		menuSales.add(menuItemNewSaleTransaction);
		menuItemNewSaleTransaction.setActionCommand("New Transaction");
		menuItemNewSaleTransaction.addActionListener(handler);
		
		JMenuItem menuItemListPastSales = new JMenuItem("List Past Sales");
		menuSales.add(menuItemListPastSales);
		menuItemListPastSales.setActionCommand("List Past Sales");
		menuItemListPastSales.addActionListener(handler);
		
		JButton buttonSaveAndExit = new JButton("Save and Exit");
		menuBar.add(buttonSaveAndExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		buttonSaveAndExit.setActionCommand("Save and Exit");
		buttonSaveAndExit.addActionListener(handler);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		JLabel labelMainMenu = new JLabel("Main Menu");
		labelMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		desktopPane.add(labelMainMenu, BorderLayout.CENTER);
		
		LOGGER.log(Level.INFO, "...Finished adding startup items.");
	}
	
	//Resets desktop pane, assigns title from parameter:
	private void redrawMainMenu(String MenuTitle) {
		getContentPane().removeAll(); //Clear components
		LOGGER.log(Level.INFO, "Cleared window.");
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		JLabel labelMainMenu = new JLabel(MenuTitle);
		labelMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		desktopPane.add(labelMainMenu, BorderLayout.NORTH);
		
		getContentPane().revalidate();
		getContentPane().repaint(); //Redraw added components
	}

	private void listAllItems() {
		getContentPane().removeAll(); //Clear components
		LOGGER.log(Level.INFO, "Cleared window.");
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(desktopPane);
		desktopPane.setLayout(new BorderLayout(0, 0));
		
		//Set menu title:
		JLabel labelMainMenu = new JLabel("Listing All Items Currently in Stock:");
		labelMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		desktopPane.add(labelMainMenu, BorderLayout.NORTH);
		
		//Create JTable of formatted items:
		//HardwareStore.sortItemList();
		//HardwareStore.getAllItemsFormatted();
		String[] columns = { "Item ID", "Name", "Quantity", "Price", "Item Type", "Cat./Brand"};
		String[][] rows = HardwareStore.getAllItemsFormatted();
		LOGGER.log(Level.WARNING, "Serialized database known to cause issues importing item list...");
			/*{	{"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"},
				{"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"},
				{"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"},
				{"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"},
				{"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"},
				{"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"}, {"1", "2", "3", "4", "5", "6"},}; */
		
		//Create new table:
		JTable itemListTable = new JTable(rows, columns);
		itemListTable.setPreferredScrollableViewportSize(new Dimension(380,100));
		itemListTable.setFillsViewportHeight(true);
		itemListTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(itemListTable); //Add JTable to scroll pane 
		desktopPane.add(scrollPane, BorderLayout.CENTER);
		
		//Redraw added components:
		getContentPane().revalidate();
		getContentPane().repaint(); 
	}
	
	private void addItemQuantity() {
		getContentPane().removeAll(); //Clear components
		LOGGER.log(Level.INFO, "Cleared window.");
		
		CardLayout cl = new CardLayout();
		JPanel panelCont = new JPanel();
		panelCont.setBackground(Color.LIGHT_GRAY);
		panelCont.setLayout(cl);
		
		//Create first card:
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("1. Enter ID number of item (5 letters/numbers): ");
		JTextField idInputField = new JTextField();
		idInputField.setPreferredSize(new Dimension(100,30));
		JButton button1 = new JButton("Submit");
		panel1.add(label1);
		panel1.add(idInputField);
		panel1.add(button1);
		panelCont.add(panel1, "1");
		
		//Create second card:
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("2. Enter name of new item: ");
		JTextField nameInputField = new JTextField();
		nameInputField.setPreferredSize(new Dimension(100,30));
		JButton button2 = new JButton("Submit");
		panel2.add(label2);
		panel2.add(nameInputField);
		panel2.add(button2);
		panelCont.add(panel2, "2");
		
		//Create third card:
		JPanel panel3 = new JPanel();
		JLabel label3 = new JLabel("3. Enter quantity of new item: ");
		JTextField qtyInputField = new JTextField();
		qtyInputField.setPreferredSize(new Dimension(50,30));
		JButton button3 = new JButton("Submit");
		panel3.add(label3);
		panel3.add(qtyInputField);
		panel3.add(button3);
		panelCont.add(panel3, "3");
		
		//Create fourth card:
		JPanel panel4 = new JPanel();
		JLabel label4 = new JLabel("4. Enter item price:  $");
		JTextField priceInputField = new JTextField("0.00");
		priceInputField.setPreferredSize(new Dimension(50,30));
		JButton button4 = new JButton("Submit");
		panel4.add(label4);
		panel4.add(priceInputField);
		panel4.add(button4);
		panelCont.add(panel4, "4");
		
		//Create fifth card:
		JPanel panel5 = new JPanel();
		JLabel label5a = new JLabel("5. Select item type: ");
		JLabel label5b = new JLabel(" or ");
		JButton button5a = new JButton("Small Hardware Item"); 
		JButton button5b = new JButton("Appliance");
		panel5.add(label5a);
		panel5.add(button5a);
		panel5.add(label5b);
		panel5.add(button5b);
		panelCont.add(panel5, "5");
		
		//Create sixth card:
		JPanel panel6 = new JPanel();
		JLabel label6 = new JLabel("6. Select category of Item: ");
		String[] catOptions = {"Door&Window", "Cabinet&Furniture", "Fasteners", "Structural", "Other"};
		JComboBox combo1 = new JComboBox(catOptions);
		JButton button6 = new JButton("Submit");
		panel6.add(label6);
		panel6.add(combo1);
		panel6.add(button6);
		panelCont.add(panel6, "6");
		
		//Create seventh card:
		JPanel panel7 = new JPanel();
		JLabel label7 = new JLabel("6. Select the type of appliance: ");
		String[] applTypeOptions = {"Refrigerators", "Washers&Dryers", "Ranges&Ovens", "Small Appliance"};
		JComboBox combo2 = new JComboBox(applTypeOptions); 
		JButton button7 = new JButton("Submit");
		panel7.add(label7);
		panel7.add(combo2);
		panel7.add(button7);
		panelCont.add(panel7, "7");
		
		//Create eighth card:
		JPanel panel8 = new JPanel();
		JLabel label8 = new JLabel("7. Enter the Brand of appliance: ");
		JTextField brandInputField = new JTextField();
		brandInputField.setPreferredSize(new Dimension(100,30));
		JButton button8 = new JButton("Submit");
		panel8.add(label8);
		panel8.add(brandInputField); 
		panel8.add(button8);
		panelCont.add(panel8, "8");
		
		
		//Redraw added components:
		contentPane.add(panelCont);
		cl.show(panelCont, "1");
		getContentPane().revalidate();
		getContentPane().repaint(); 
		
		//Assign actions to buttons:
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 1 clicked.");
				String idInput = idInputField.getText();
				if (!idInput.matches("[A-Za-z0-9]{5}")) 
				{
	                JOptionPane.showMessageDialog(null, "Invalid ID Number: improper format. "
	                        + "ID Number must be 5 alphanumeric characters.", "Error", JOptionPane.ERROR_MESSAGE);
	                LOGGER.log(Level.WARNING, "User input error.");
	            }
				else 
				{
					int itemIndex = hardwareStore.findItemIndex(idInput);
					if(itemIndex != -1) { //If item DOES exist:
						
					}
					else { //If item does NOT exist:
						cl.show(panelCont, "2");
						JOptionPane.showMessageDialog(null, "Item does not exist. Proceed to add new item.");
					}
				}
			}
		});
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.WARNING, "Button 2 clicked.");
				String nameInput = nameInputField.getText();
				if (nameInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid Name. Must not be empty.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else if (nameInput.length() > 20) {
					JOptionPane.showMessageDialog(null, "Invalid Name. Cannot exceed 20 characters.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					cl.show(panelCont,  "3");
				}
			}
		});
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 3 clicked.");
				int qtyInput = 0;
				try {
					qtyInput = Integer.parseInt(qtyInputField.getText());
					if (qtyInput <= 0) {
						JOptionPane.showMessageDialog(null, "Invalid Quantity. Cannot be zero nor negative.", "Error", 
								JOptionPane.ERROR_MESSAGE);
						LOGGER.log(Level.WARNING, "User input integer as zero or less than zero");
					}
					else {
						cl.show(panelCont, "4");
					}
				} 
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid input. Quantity must be an integer.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.SEVERE, "User did not input integer.", ex);
				}
			}
		});
		
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 4 clicked.");
				float priceInput = 0f;
				try {
					priceInput = Float.parseFloat(priceInputField.getText());
					if (priceInput <= 0.0f) {
						JOptionPane.showMessageDialog(null, "Invalid Price. Cannot be zero nor negative.", "Error", 
								JOptionPane.ERROR_MESSAGE);
						LOGGER.log(Level.WARNING, "User input negative or zero float.");
					}
					else {
						//Truncate float to two decimal places:
						int truncate = (int) (priceInput*100);
						priceInput = truncate / 100f;
						
						cl.show(panelCont,  "5");
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid input. Price must be a 2 decimal floating point number.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.SEVERE, "User did not input float.", ex);
				}
			}
		});
		
		button5a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 5a clicked.");
				//Type selected = Small Hardware Items
				cl.show(panelCont, "6");
			}
		});
		
		button5b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 5b clicked.");
				//Type selected = Appliances
				cl.show(panelCont, "7");
			}
		});
		
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 6 clicked.");
				String catInput = (String)combo1.getSelectedItem();
				JOptionPane.showMessageDialog(null, "Succesfully created and added new item.");
				
				redrawMainMenu("Main Menu.");
			}
		});
		
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 7 clicked.");
				String typeInput = (String)combo2.getSelectedItem();
				cl.show(panelCont, "8");
			}
		});
	
		button8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 8 clicked.");
				String brandInput = brandInputField.getText();
				if (brandInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid Brand. Must not be empty.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else if (brandInput.length() > 20) {
					JOptionPane.showMessageDialog(null, "Invalid Brand. Cannot exceed 20 characters.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Succesfully created and added new item.");
					
					redrawMainMenu("Main Menu.");
				}
			}
		});
	}
	
	private void deleteItem() {
		getContentPane().removeAll(); //Clear components
		CardLayout cl = new CardLayout();
		JPanel panelCont = new JPanel();
		panelCont.setBackground(Color.LIGHT_GRAY);
		panelCont.setLayout(cl);
		
		//Create card 1:
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Enter ID number of item to delete: ");
		JTextField idInputField = new JTextField();
		idInputField.setPreferredSize(new Dimension(100,30));
		JButton button1 = new JButton("Submit");
		panel1.add(label1);
		panel1.add(idInputField);
		panel1.add(button1);
		panelCont.add(panel1, "1");
		
		//Assign actions to buttons:
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button 1 clicked.");
				String idInput = idInputField.getText();
				if (!idInput.matches("[A-Za-z0-9]{5}")) 
				{
					JOptionPane.showMessageDialog(null, "Invalid ID Number: improper format. "
							+ "ID Number must be 5 alphanumeric characters.", "Error", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					int itemIndex = hardwareStore.findItemIndex(idInput);
					if(itemIndex != -1) { //If item DOES exist:
						
						JOptionPane.showMessageDialog(null, "Item has been successfully deleted from inventory");
						redrawMainMenu("Main Menu");
					}
					else { //If item does NOT exist:
						JOptionPane.showMessageDialog(null, "Item does not exist.");
					}
				}
			}
		});
		
		//Redraw added components:
		contentPane.add(panelCont);
		cl.show(panelCont, "1");
		getContentPane().revalidate();
		getContentPane().repaint(); 
	}
	
	private void searchForItem() {
		getContentPane().removeAll(); //Clear components
		CardLayout cl = new CardLayout();
		JPanel panelCont = new JPanel();
		panelCont.setBackground(Color.LIGHT_GRAY);
		panelCont.setLayout(cl);
		
		//Create card 1:
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Enter name (or partial name) of item to search for: ");
		JTextField nameInputField = new JTextField();
		nameInputField.setPreferredSize(new Dimension(100,30));
		JButton button1 = new JButton("Submit");
		panel1.add(label1);
		panel1.add(nameInputField);
		panel1.add(button1);
		panelCont.add(panel1, "1");
		
		//Create card 2:
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("Item found:");
		JTextField foundItemField = new JTextField();
		
		
		//Assign actions to buttons:
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nameInput = nameInputField.getText();
				String output = hardwareStore.getMatchingItemsByName(nameInput);
				if (nameInput.isEmpty()) 
				{
					JOptionPane.showMessageDialog(null, "Search query cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (output == null) {
					JOptionPane.showMessageDialog(null, "Item not found.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else { //When item is found:
					panel2.add(label2);
					foundItemField.setText(output);
					panel2.add(foundItemField);
					panelCont.add(panel2, "2");
					cl.show(panelCont, "2");
				}
			}
		});
		
		//Redraw added components:
		contentPane.add(panelCont);
		cl.show(panelCont, "1");
		getContentPane().revalidate();
		getContentPane().repaint(); 
	}
	
	private void listAllUsers() {
		LOGGER.log(Level.INFO, "Cleared main window.");
		getContentPane().removeAll(); //Clear components
		setLayout(new BorderLayout(0,0));
		
		JTextField listOfUsers = new JTextField(hardwareStore.getAllUsersFormatted());
		JScrollPane scrollPane = new JScrollPane(listOfUsers); //Add JTable to scroll pane 
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		//Redraw added components:
		getContentPane().revalidate();
		getContentPane().repaint(); 
	}
	
	private void addUser() {
		getContentPane().removeAll(); //Clear components
		CardLayout cl = new CardLayout();
		JPanel panelCont = new JPanel();
		panelCont.setBackground(Color.LIGHT_GRAY);
		panelCont.setLayout(cl);
		
		//Create first card:
		JPanel panel1 = new JPanel();
		JLabel label1a = new JLabel("1. Select type of user to add: ");
		JLabel label1b = new JLabel(" or ");
		JButton button1a = new JButton("Employee");
		JButton button1b = new JButton("Customer");
		panel1.add(label1a);
		panel1.add(button1a);
		panel1.add(label1b);
		panel1.add(button1b);
		panelCont.add(panel1, "1");
		
		//Create card 2: 
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("2. Enter Employee's first name: ");
		JTextField emp1stNameField = new JTextField();
		emp1stNameField.setPreferredSize(new Dimension(100,30));
		JButton button2 = new JButton("Submit");
		panel2.add(label2);
		panel2.add(emp1stNameField);
		panel2.add(button2);
		panelCont.add(panel2, "2");
		
		//Create card 3: 
		JPanel panel3 = new JPanel();
		JLabel label3 = new JLabel("3. Enter Employee's last name: ");
		JTextField empLastNameField = new JTextField();
		empLastNameField.setPreferredSize(new Dimension(100,30));
		JButton button3 = new JButton("Submit");
		panel3.add(label3);
		panel3.add(empLastNameField);
		panel3.add(button3);
		panelCont.add(panel3, "3");
		
		//Create card 4:
		JPanel panel4 = new JPanel();
		JLabel label4 = new JLabel("4. Enter Employee's SSN (9 digits only): ");
		JTextField ssnField = new JTextField();
		ssnField.setPreferredSize(new Dimension(100,30));
		JButton button4 = new JButton("Submit");
		panel4.add(label4);
		panel4.add(ssnField);
		panel4.add(button4);
		panelCont.add(panel4, "4");
		
		//Create card 5:
		JPanel panel5 = new JPanel();
		JLabel label5 = new JLabel("5. Enter monthly salary (as decimal):  $");
		JTextField salaryField = new JTextField("0.00");
		salaryField.setPreferredSize(new Dimension(100,30));
		JButton button5 = new JButton("Submit");
		panel5.add(label5);
		panel5.add(label5);
		panel5.add(salaryField);
		panel5.add(button5);
		panelCont.add(panel5, "5");
		
		//Create card 6: 
		JPanel panel6 = new JPanel();
		JLabel label6 = new JLabel("2. Enter Customer's first name: ");
		JTextField cust1stNameField = new JTextField();
		cust1stNameField.setPreferredSize(new Dimension(100,30));
		JButton button6 = new JButton("Submit");
		panel6.add(label6);
		panel6.add(cust1stNameField);
		panel6.add(button6);
		panelCont.add(panel6, "6");
		
		//Create card 7:
		JPanel panel7 = new JPanel();
		JLabel label7 = new JLabel("3. Enter Customer's last name: ");
		JTextField custLastNameField = new JTextField();
		custLastNameField.setPreferredSize(new Dimension(100,30));
		JButton button7 = new JButton();
		panel7.add(label7);
		panel7.add(custLastNameField);
		panel7.add(button7);
		panelCont.add(panel7, "7");
		
		//Create card 8:
		JPanel panel8 = new JPanel();
		JLabel label8 = new JLabel("4. Enter phone number (10 digits only): ");
		JTextField custPhoneField = new JTextField();
		custPhoneField.setPreferredSize(new Dimension(100,30));
		JButton button8 = new JButton("Submit");
		panel8.add(label8);
		panel8.add(custPhoneField);
		panel8.add(button8);
		panelCont.add(panel8, "8");
		
		//Create card 9:
		JPanel panel9 = new JPanel();
		JLabel label9 = new JLabel("5. Enter Customer's address: ");
		JTextField custAddrField = new JTextField();
		custAddrField.setPreferredSize(new Dimension(100,30));
		JButton button9 = new JButton("Submit");
		panel9.add(label9);
		panel9.add(custAddrField);
		panel9.add(button9);
		panelCont.add(panel9, "9");
		
		//Redraw added components:
		contentPane.add(panelCont);
		cl.show(panelCont, "1");
		getContentPane().revalidate();
		getContentPane().repaint(); 
		
		button1a.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Type selected = Employee
				LOGGER.log(Level.INFO, "Button Clicked.");
				cl.show(panelCont, "2");
			}
		});
		
		button1b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				cl.show(panelCont, "6");
			}
		});
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String nameInput = emp1stNameField.getText();
				if (nameInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid first name. Cannot be empty.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else if (nameInput.length() > 20) {
					JOptionPane.showMessageDialog(null, "Invalid first name. Cannot be longer than 20 characters.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					cl.show(panelCont,  "3");
				}
			}
		});
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String nameInput = empLastNameField.getText();
				if (nameInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid last name. Cannot be empty.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else if (nameInput.length() > 20) {
					JOptionPane.showMessageDialog(null, "Invalid last name. Cannot be longer than 20 characters.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					cl.show(panelCont,  "4");
				}
			}
		});
		
		button4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String ssnInput = ssnField.getText();
				try {
					int ssnInputInt = Integer.parseInt(ssnInput);
					if (ssnInputInt <= 100000000 || ssnInputInt > 999999999) {
						JOptionPane.showMessageDialog(null, "Invalid Social Security Number. Must be 9 numbers only.", "Error", 
								JOptionPane.ERROR_MESSAGE);
						LOGGER.log(Level.WARNING, "User input error.");
					}
					else {
						cl.show(panelCont, "5");
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid input. Price must be a 9 digit number.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.SEVERE, "User did not input correct SSN.", ex);
				}
			}
		});
		
		button5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String salaryInput = salaryField.getText();
				try {
					float salary = Float.parseFloat(salaryInput);
					if (salary <= 0.0f) {
						JOptionPane.showMessageDialog(null, "Invalid Salary. Cannot be zero nor negative.", "Error", 
								JOptionPane.ERROR_MESSAGE);
						LOGGER.log(Level.WARNING, "User input negative or zero float.");
					}
					else {
						//Truncate float to two decimal places:
						int truncate = (int) (salary*100);
						salary = truncate / 100f;
						//HardwareStore.addEmployee()
						JOptionPane.showMessageDialog(null, "Successfully created new employee.");
						redrawMainMenu("Main Menu");
					}
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Invalid Input. Must be floating point with 2 decimal places.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.SEVERE, "User input error.", ex);
				}
			}
		});
	
		button6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String nameInput = cust1stNameField.getText();
				if (nameInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid first name. Cannot be empty.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else if (nameInput.length() > 20) {
					JOptionPane.showMessageDialog(null, "Invalid first name. Cannot be longer than 20 characters.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					cl.show(panelCont,  "3");
				}
			}
		});
		
		button7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String nameInput = custLastNameField.getText();
				if (nameInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Invalid first name. Cannot be empty.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else if (nameInput.length() > 20) {
					JOptionPane.showMessageDialog(null, "Invalid first name. Cannot be longer than 20 characters.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					cl.show(panelCont,  "8");
				}
			}
		});
		
		button8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String phoneInput = custPhoneField.getText();
				if (phoneInput != "[0-9]{10}") {
					JOptionPane.showMessageDialog(null, "Invalid Phone Number. Must be 10 numbers only.", "Error", 
							JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					cl.show(panelCont, "9");
				}
			}
		});
		
		button9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				String addrInput = custAddrField.getText();
				if (addrInput.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Address cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
					LOGGER.log(Level.WARNING, "User input error.");
				}
				else {
					JOptionPane.showMessageDialog(null, "Successfully added new customer.");
					redrawMainMenu("Main Menu");
				}
			}
		});
	
	}
	
	private void updateUser() {
		getContentPane().removeAll(); //Clear components
		CardLayout cl = new CardLayout();
		JPanel panelCont = new JPanel();
		panelCont.setBackground(Color.LIGHT_GRAY);
		panelCont.setLayout(cl);
		
		//Create card 1:
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Enter the ID of the user: "); 
		JTextField idField = new JTextField();
		idField.setPreferredSize(new Dimension(100,30));
		JButton button1 = new JButton("Submit");
		panel1.add(label1);
		panel1.add(idField);
		panel1.add(button1);
		panelCont.add(panel1, "1");
		
		//Redraw added components:
		contentPane.add(panelCont);
		cl.show(panelCont, "1");
		getContentPane().revalidate();
		getContentPane().repaint(); 
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idInput = idField.getText();
				if (!idInput.matches("[A-Za-z0-9]{5}")) 
				{
					JOptionPane.showMessageDialog(null, "Invalid ID Number: improper format. "
							+ "ID Number must be 5 alphanumeric characters.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if (hardwareStore.findUser(Integer.parseInt(idInput)) == null) {
						JOptionPane.showMessageDialog(null, "User not found.");
					}
					else {
						JOptionPane.showMessageDialog(null, "User found. Proceed to edit user.");
						cl.show(panelCont, "2");
					}
				}
			}
		});

	}
	
	private void newSaleTransaction() {
		getContentPane().removeAll(); //Clear components
		CardLayout cl = new CardLayout();
		JPanel panelCont = new JPanel();
		panelCont.setBackground(Color.LIGHT_GRAY);
		panelCont.setLayout(cl);
		
		//Card 1:
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel("Enter ID of sale Item: ");
		JTextField idInput = new JTextField();
		idInput.setPreferredSize(new Dimension(100,30));
		JButton button1 = new JButton("Next");
		panel1.add(label1);
		panel1.add(idInput);
		panel1.add(button1);
		panelCont.add(panel1, "1");
		
		//Card 2:
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("Enter quantity of item to sell: ");
		JTextField qtyField = new JTextField();
		qtyField.setPreferredSize(new Dimension(100,30));
		JButton button2 = new JButton("Next");
		panel2.add(label2);
		panel2.add(qtyField);
		panel2.add(button2);
		panelCont.add(panel2, "2");
		
		//Card 3:
		JPanel panel3 = new JPanel();
		JLabel label3 = new JLabel("Enter Employee ID: ");
		JTextField emplField = new JTextField();
		emplField.setPreferredSize(new Dimension(100,30));
		JButton button3 = new JButton("Next");
		panel2.add(label3);
		panel2.add(emplField);
		panel2.add(button3);
		panelCont.add(panel3, "3");
		
		//Card 4:
		JPanel panel4 = new JPanel();
		JLabel label4 = new JLabel("Enter Customer ID: ");
		JTextField custField = new JTextField();
		custField.setPreferredSize(new Dimension(100,30));
		JButton button4 = new JButton("Next");
		panel2.add(label4);
		panel2.add(custField);
		panel2.add(button4);
		panelCont.add(panel4, "4");
		
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				cl.show(panelCont, "2");
			}
		});
		
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				cl.show(panelCont, "3");
			}
		});
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LOGGER.log(Level.INFO, "Button Clicked.");
				cl.show(panelCont, "4");
			}
		});
		
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Transaction completed.");
				redrawMainMenu("Main Menu");
			}
		});
		
		//Redraw added components:
		contentPane.add(panelCont);
		cl.show(panelCont, "1");
		getContentPane().revalidate();
		getContentPane().repaint(); 
	}
	
	private void listPastSales() {
		getContentPane().removeAll(); //Clear components
		setLayout(new BorderLayout(0,0));
		JTextField listOfUsers = new JTextField(hardwareStore.getAllTransactionsFormatted());
		JScrollPane scrollPane = new JScrollPane(listOfUsers); //Add JTable to scroll pane 
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		//Redraw added components:
		getContentPane().revalidate();
		getContentPane().repaint(); 
	}
	
	//Safely save database to disk after user confirmation:
	private void saveAndExit() throws IOException {
		int dialogChoice = JOptionPane.showConfirmDialog(null, "Are you sure you want to save and exit?", "Warning", JOptionPane.YES_NO_OPTION);
		if(dialogChoice == JOptionPane.YES_OPTION) {
			hardwareStore.writeDatabase();
			LOGGER.log(Level.INFO, "Saved database to file.");
			dispose(); //Close entire program.
		}
	}
	
	//Assign commands to click events:
	private class EventListener implements ActionListener{
	      public void actionPerformed(ActionEvent event) { 
	    	 String command = event.getActionCommand();  
	         if(command.equals("List All Items"))  {
	        	listAllItems();
	         }
	         else if(command.equals("Add New Item/Quantity")) {
	        	addItemQuantity();
	         }
	         else if(command.equals("Delete Item")) {
	        	deleteItem();
	         }
	         else if(command.equals("Search for Item")) {
	        	searchForItem();
	         }
	         else if(command.equals("List All Users")) {
	        	listAllUsers();
	         }
	         else if(command.equals("Add New User")) {
	        	addUser();
	         }
	         else if(command.equals("Update User Info")) {
	        	updateUser();
	         }
	         else if(command.equals("New Transaction")) {
	        	newSaleTransaction();
	         }
	         else if(command.equals("List Past Sales")) {
	        	listPastSales();
	         }
	         else if(command.equals("Save and Exit")) {
	        	try {
					saveAndExit();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.log(Level.SEVERE, "IO Exception.", e);
				}
	         }
	      }		
	   }
}

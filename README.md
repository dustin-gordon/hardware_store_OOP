# hardware_store_OOP
Java based implementation of a hardware store inventory manager to demonstrate aspects of object-oriented programming

Goal: The goal of this assignment is to help students familiarize themselves with the following
Java programming concepts:
1. Input/Output to and from the terminal.
2. Storing data in a file and reading data from a file.
3. Creating object-oriented classes and methods to handle data.
4. Using data structures to store data in main memory (e.g. ArrayList).
5. Working with character strings.
6. Using Javadoc comments and generating and html documentation of the program.
7. Unit testing
8. GUI using MVC model
9. Multithreading

Description:
For this assignment you will create a program to support a small hardware store. Your program
will be able to keep track of items in the inventory of the store, so that the owner knows the
quantities of each item in stock. Your program should provide the user (store owner) with a
command line choice menu about possible actions that they can perform. The choices should be
the following:
1. Show all existing items in stock and their quantities.
2. Add a new quantity of a specific item to the stock.
3. Remove a certain quantity of a specific item type.
4. Search for an item (given its name or part of its name).
5. Show a list of all items below a certain quantity.
6. Exit program.

To represent an item in your program, create a class named Item with the following fields:
• ID number (random string of length 5 – can contain letters and numbers)
• Name (string)
• Category (string)
• Quantity (integer)
• Price (floating point number)

The Category of the item can be only one of the following: “Door&Window”,
“Cabinet&Furniture”, “Fasteners”, “Structural”, “Other”.

When the program first loads, it reads all the saved records (if any) from a file named
database.txt into an ArrayList. While the program is running, the user can choose any of
the 6 available options. When the user selects the option 6 (exit program), the program stores the
current contents of the ArrayList to the file (replacing the old ones) and exits. During the
program execution, if the user chooses to add or delete items, only the ArrayList will be updated.
The database.txt file will be updated automatically only when the program is about to exit. 

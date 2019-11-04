# Shopping Cart Application
- The purpose of creating this program was to take user input and create a shopping cart which allows you to purchase items based on a defined budget.

# Application Features

- GUI interface which leverages JavaFx for a dynamic experience.  
- Implements a Table which is populated from user input.
    - Grand total is automatically populated from the 'Price' column.
    - Budget is fixed at 59.00 usd but can be user defined (feature to be implemented at a later date.) 
    
- The Shopping Cart Application allows users to add items from a different page.
- Allows users to enter a Item name value and strips out all non Alphabetic characters.

    - The Item name cannot be duplicated and must be unique.
- Allows user to enter a Item Priority value which is auto-populated [1-10].
    - Multiple Itme Priority can be used, the shopping cart application generated a unique ID per item in the cart.
    
- Input verification which removes the following:
     - White Spaces.
     - Numbers (On TextFields (Item Name)).
     - NULL values.

# Program Files
  **Main.java**
   - This is where the Main method resides.
   
   **DBConnector.java**
   - This is where the Main method resides.
   
  **ShoppingCartController.java**
   
   - Displays an Observable Table List.
   - Allows the user to perform the following actions:
     - Add Items
     - Remove Items
     - Checkout 
   - Grand total is automatically populated based on the prices of the items in the table.
   - Budget
     - Cannot be null or zero.
     
  **AddItemsController.java**
   - Allows the user to add a new item based on the following:
      - Priority
      - Item Name
      - Item Quantity
      - Item Price
   - User Input Verification occurs to ensure the following:
      - No duplicate records are added.
      - No NULL values are added.
      - No illegal strings are added.
        - Letters within numbers.
        - Numbers within strings.
 
  **Products.java**
   - This is where the getter, setters and constructors reside.

# Known Issues
  **Adding New Items**
   - When adding a new item you need to go to the main page before adding a new entry.
  
## Pseudo Code 

1. Main()
     - Display the initial Scene.
2. Connect()
    - Creates the initial database connection.
3. Insert()
    - Inserts user data into the database.
4. Get()
    - Collects all of the items within the Products Observable Item table.
5. Delete()
    - Removes selected items within the database by unique ID.
6. Products()
    - Getters and setters for the items in the shopping cart.
7. setColumns()
    - Sets Table column headers.
8. loadData()
    - Adds Items to arrayList.
9. getPriceSum()
    - Calculates the sum of all items in the table.
10. getBudget()
    - Calculates a fixed number and sets that information to a TextField.
11. completedCart()
     - Logic which is used to display which items could be purchased based on budget and priority.
      - The displays information as 'Purchased' and 'Not Purchased'.
12. handlecartCheckout()
     - Action which occurs when the user hits the checkout button on the shoppingCartController.
13. handleitemAddition()
     - Action which occurs when the user hits the add button on the shoppingCartController, this button changes the users scene to addItemsController.
14. handleitemDelete()
     - Action which occurs when the user hits the delete all button on the shoppingCartController.   
15. handleitemReturnCart()
     - Action which occurs when the user hits the shoppingCart button on the addItemsController.
16. handleitemAdd()
     - Action which occurs when the user hits the add button on the addItemsController.
17. isInputValid()
     - Logic which occurs after user input to verify if the data is valid.

 

    
## UML
![UML](UML.png)
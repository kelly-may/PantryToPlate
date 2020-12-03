# Pantry To Plate
SDEV-435 Applied Software Practice
Summer 2020

# Summary
Application for storing a food inventory and a recipe inventory. Recipes with all ingredients
found in the food inventory will be viewable, so user can choose recipes readily available to them. 
This program is written primarily in Java, with a SQL database that stores recipe and inventory data. 

# Background 
Food waste is an enormous problem in the United States, and other first world countries. Studies have shown that up to 40% of the food in the United States is never actually eaten (NRDC, 2020). This wastage comes from massive portions at restaurants that will throw out leftovers, grocery stores that overstock their shelves to provide variety, and farmers who cannot sell produce that is not perfect-looking. Most of all, food is wasted at home, with many items forgotten on shelves or in the fridge and only remembered when they are no longer edible. Pantry to Plate is an application that will be designed to help lessen the amount of food waste in the home by providing consumers knowledge about what they already have in their kitchens, and what they can do with what they have. Pantry to Plate will store information about the food items entered by the customer, including amount, where it is stored (refrigerator, freezer, or pantry shelves), and expiration dates. With this information, Pantry to Plate will match the food items logged in the application with recipes that can be made without taking a trip to the grocery store. The basic model of Pantry to Plate is a desktop platform that has a database, which users can enter both ingredient items such as produce, spices, grains, etc., and enter recipes with ingredients, their amount, and cooking instructions. The application will search for recipes that utilize only food items stored in the ingredients database and display those recipe options to the user. This will give the user options for what to cook with the ingredients that are readily available to them.

# Source Files:
Delete Inventory Actionable | ActionsDeleteInventory.java - 
Houses Editor GUI and confirmation pages, as well as controls actions needed to access SQL classes for 
deleting inventory information. 


Delete Recipe Actionable | ActionsDeleteRecipe.java - 
Holds Editor GUI and confirmation page as well as controls access to SQL classes for deleting recipe information. 


Insert Inventory Actionable | ActionsInsertInventory.java - 
Holds Editor GUI and confirmation page, as well as controls access to SQL classes for inserting an ingredient into the database


Insert Recipe Actionable | ActionsInsertRecipe.java - 
Holds Editor GUI and confirmation page, as well as controls access to SQL classes for inserting a recipe into the database


Update Inventory Actions | ActionsUpdateInventory.java - 
Holds Editor GUI and confirmation page, as well as controls access to SQL classes for updating an item in the inventory. 


Update Recipe Actions| ActionsUpdateRecipe.java - 
Holds Editor GUI and confirmation page, as well as controls access to SQL classes for updating recipe information and recipe_ingredient information.


Alert Messages| Alerts.java - 
Holds Alert pages that will appear if user input is incorrect. 


Database connection | DBConnection.java - 
Connects the Java program to Microsoft SQL server database PantryToPlate.


Delete Inventory SQL | DeleteInventory.java - 
Calls stored procedures to delete an inventory item in the database. Extends DBConnection.


Delete Recipe SQL | DeleteRecipe.java - 
Calls stored procedure to delete a recipe in the database. Extends DBConnection.


Insert into Inventory SQL | InsertInventory.java - 
Calls stored procedure to insert an item into the inventory. Extends DBConnection


Insert new Recipe into CookBook SQL | InsertRecipe.java - 
Calls stored procedures to insert recipe information and recipe_ingredient information into the database. Extends DBConnection.


Main GUI | PantryToPlate.java - 
Central control and user interface for the program. Contains GUI panes for the main stage of the program, and Action Events to control other actionable classes. 


Styles | Styles.java - 
Class that holds CSS style strings that can be accessed by all other classes with GUI components, so that the style of the interface is congruent throughout the program. 


Update Inventory SQL | UpdateInventory.java - 
Methods call user defined function to take in new information for a given inventory item, and update the information in the database. Extends DBConnection. 


Update Recipe SQL |UpdateRecipe.java - 
Methods call user defined functions to take in new information for a given recipe and update the recipe information as well as the recipe_ingredient information in the database. Extends DBConnection.


Verification Class | Verify.java - 
Class that verifies user inputs, and also checks inputs with what is already in the database. Extends DBConnection. 


View Available Recipes | ViewAvailableRecipes.java - 
Class that uses a User Defined function to display recipes that have ingredients available in the pantry. This is used to fill in a page in the GUI. Extends DBConnection. 


View Pantry Ingredients | ViewPantry.java - 
Class that uses a user defined function to display all the inventory items and their information. This is used to fill a page in the GUI. Extends DBConnection. 


View Recipe Ingredients for a given Recipe | ViewRecipeIngredients.java - 
Used in both the AvailableRecipes and All Recipes pages, this class uses a user defined function to show all the ingredients needed for a given recipe. Extends DBConnection. 


View Recipes |ViewRecipes.java - 
Class that uses a user defined function to display all the recipes in the Recipes table of the database. This is used to fill a page in the GUI. Extends DBConnection. 

Database Build | PantryToPlate_DB.sql - 
Will create the PantryToPlate Database. 

# Executables:
Application| PantryToPlate.exe - 
Found in Java > dist

#Program Requirements: 
Java JRE version 1.7.0 or newer
Microsoft SQL Server 2014
sqljdbc_auth.dll
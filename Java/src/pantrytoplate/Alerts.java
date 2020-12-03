package pantrytoplate;

import javafx.scene.control.Alert;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass Alerts Description: class that houses alert messages for input
 * validation, to help guide user to correct inputs
 */
//Begin Subclass Alerts
public class Alerts {

    /**
     * Default Constructor
     */
    public Alerts() {

    }

    /**
     * catchAllAlert() - alert triggered when an exception is thrown that was
     * unexpected and otherwise un-handled.
     */
    protected void catchAllAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Input Error");
        alert.setContentText("Something wrong happened with your inputs..."
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * badNameAlert - alert triggered if a name that cannot be used for an
     * inventory name is added to the textfield
     */
    protected void badNameAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Item Name Input Error");
        alert.setContentText("The item name you entered is invalid."
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * badQuantAlert - alert triggered if the quantity is not an integer value
     */
    protected void badQuantAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Quantity Input Error");
        alert.setContentText("Quantity values must be integers"
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * badUnitsAlert - alert triggered if the units are not correctly entered.
     */
    protected void badUnitsAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Units Input Error");
        alert.setContentText("Something wrong happened with your units..."
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * badLocaitonAlert - alert triggered if the location entered is invalid.
     */
    protected void badLocationAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Location Input Error");
        alert.setContentText("Something wrong happened with your Location input..."
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * badTypeAlert - alert triggered if the item type is invalid
     */
    protected void badTypeAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Type Input Error");
        alert.setContentText("Something wrong happened with your Type input..."
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * notFoundAlert - alert triggered if the item/recipe cannot be found
     */
    protected void notFoundAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Cannot Find what you are looking for");
        alert.setContentText("The item you are looking for cannot be found."
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * emptyEntryAlert - alert triggered if the items in the input fields are
     * not all filled out.
     */
    protected void emptyEntryAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("An Entry was left empty!");
        alert.setContentText("All fields must be filled out in order to enter an item"
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * badURLAlert- alert triggered if a URL button is selected but there is not
     * a proper URL to hyperlink.
     */
    protected void badURLAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("No URL");
        alert.setContentText("URL is not available for this recipe"
                + "\nPlease try again.");
        alert.showAndWait();
    }

    /**
     * recipeExists - alert triggered if user tries to create a recipe with 
     * a name that already is in the database. 
     */ 
    protected void recipeExists() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Recipe Already Exists");
        alert.setContentText("There is already a recipe in the database with "
                + "that name. \n Please either delete the old recipe first, "
                + "or change the name of this recipe before adding to cookbook."
                + "\nPlease try again.");
        alert.showAndWait();
    }
    
    /**
     * ingredientExists() - alert triggered if user tries to create an ingredient
     * with a name that already exists in the database. 
     */
    protected void ingredientExists(){
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Ingredient Already Exists");
        alert.setContentText("There is already an ingredient in the database with "
                + "that name. \n Please either delete the old ingredient first, "
                + "or change the name of this ingredient before adding to cookbook."
                + "\nPlease try again.");
        alert.showAndWait();
    }
} //End Subclass Alerts

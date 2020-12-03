package pantrytoplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass ViewRecipeIngredients Description: Pulls the ingredients associated
 * with a selected recipe from SQL database.
 */
//Begin Subclass ViewRecipeIngredients
public class ViewRecipeIngredients extends DBConnection {

    String ingredients = "Name \t Quantity \t Units \n ";

    /**
     * Constructor
     */
    public ViewRecipeIngredients() {
        try {
            statement = connection.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database could not be connected.");
        }
    }

    /**
     * setIngredients - method shows all ingredients associated with a recipe
     *
     * @param name
     */
    public void setIngredients(String name) {
        try {
            String queryString = "select Ingredient_Name, Quantity_Required, "
                    + "Units_Required from Recipe_Ingredient where Recipe_Name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ingredients += resultSet.getString(1) + " " + resultSet.getString(2)
                        + " " + resultSet.getString(3) + "\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPantry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("view recipies could not be completed");
        }
    }

    /**
     * getIngrediets - method returns the ingredients string
     *
     * @return
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * clearIngredients - method clears the ingredients string for next query
     */
    public void clearIngredients() {
        ingredients = "Name \t\t Quantity \t\t Units\n";
    }

} //End Subclass ViewRecipeIngredients

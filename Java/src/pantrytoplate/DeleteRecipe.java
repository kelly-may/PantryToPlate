package pantrytoplate;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass DeleteRecipe Description: class that controls the actions needed to
 * deletes a recipe, and its description from the Recipes table, also deletes
 * ingredient information from Recipe_Ingredient table. This is done by SQL
 * stored procedures
 */
//Imports
//Begin Subclass DeleteRecipe
public class DeleteRecipe extends DBConnection {

    public CallableStatement callable = null;
    public CallableStatement callableIng = null;
    public String name;

    /**
     * Default Constructor
     */
    public DeleteRecipe(String n) {

        try {
            this.name = n;
            statement = connection.createStatement();
            delete(name);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database could not be connected.");
        }
    }

    public void delete(String n) {
        try {
            String method = "{call Delete_Recipe(?)}";
            callable = connection.prepareCall(method);
            callable.setString(1, n);
            ResultSet rs = callable.executeQuery(); // call stored procedure. 

        } catch (SQLException ex) {
            System.out.println("Delete Recipe SP Issue");
        }
    }

} //End Subclass DeleteRecipe

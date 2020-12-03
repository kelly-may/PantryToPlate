package pantrytoplate;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass InsertRecipe Description: class that controls the actions needed to
 * insert a recipe, and its description into the Recipes table, also inserts
 * ingredient information into Recipe_Ingredient table. This is done by SQL
 * stored procedures
 */
//Imports
//Begin Subclass InsertRecipe
public final class InsertRecipe extends DBConnection {

    public CallableStatement callable = null;

    public String recipeName;
    public String recipeDesc;
    public String recipeType;
    public String recipeLink;

    public String ingredientName;
    public int ingredientQuant;
    public String ingredientUnits;
    
    public int num; 

    /**
     * Default Constructor
     *
     * @param name
     * @param desc
     * @param type
     * @param link
     */
    public InsertRecipe(String name, String desc, String type, String link) {
        try {
            this.recipeName = name;
            this.recipeDesc = desc;
            this.recipeType = type;
            this.recipeLink = link;

            statement = connection.createStatement();
            addRecipe();
        } catch (SQLException e) {
            System.out.println("Database could not be connected.");
        }

    }
    
   

    public void addRecipe() {
        try {
            String method = "{call Insert_Recipe_Info(?,?,?,?)}";

            PreparedStatement ps;
            ps = connection.prepareStatement(method);

            callable = connection.prepareCall(method);
            callable.setString(1, recipeName);
            callable.setString(2, recipeDesc);
            callable.setString(3, recipeType);
            callable.setString(4, recipeLink);
            ResultSet rs = callable.executeQuery(); //call stored procedure

        } catch (SQLException ex) {
            System.out.println("Insert Recipe Info problem");
        }
    }

    public void addIngredients(String name, int quant, String units) {
        try {
            String method = "{call Insert_Recipe_Ingredient(?,?,?,?)}";
            PreparedStatement ps;
            ps = connection.prepareStatement(method);

            callable = connection.prepareCall(method);
            callable.setString(1, recipeName);
            callable.setString(2, name);
            callable.setInt(3, quant);
            callable.setString(4, units);

            ResultSet rs = callable.executeQuery(); // call stored procedure

        } catch (SQLException ex) {
            System.out.println("Insert Ingredient info problem");
        }
    }

    public void updateIngredients() {
        try {
            String method = "{call Update_Recipe_Ingredient()}";
            PreparedStatement ps;
            ps = connection.prepareStatement(method);

            callable = connection.prepareCall(method);

            ResultSet rs = callable.executeQuery(); // call stored procedure

        } catch (SQLException ex) {
            System.out.println("Update Ingredient info problem");
        }
    }

} //End Subclass InsertRecipe

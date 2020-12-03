package pantrytoplate;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass UpdateRecipe Description: class that controls the actions needed to
 * update something about a recipe, or a recipe ingredient. uses SQL stored
 * procedures.
 */
//Imports
//Begin Subclass UpdateRecipe
public class UpdateRecipe extends DBConnection {

    public ArrayList<String> recipeNames = new ArrayList<>();
    public String rName = "";

    public ArrayList<String> ingredientNames = new ArrayList<>();
    public ArrayList<Integer> ingredientQuant = new ArrayList<>();
    public ArrayList<String> ingredientUnits = new ArrayList<>();
    public ArrayList<Integer> ingredientID = new ArrayList<>();
    private String iName = "";
    private String iQuant = "";
    private String iUnits = "";

    private String name;
    private String description;
    private String type;
    private String link;

    private int id;
    public CallableStatement callable = null;

    /**
     * Default Constructor
     */
    public UpdateRecipe() {
        try {
            statement = connection.createStatement();
            setRecipeNames();

        } catch (SQLException e) {
            System.out.println("Database could not be connected.");
        }
    }

    /**
     * pulls the recipe names from the SQL database into a string
     */
    public void setRecipeNames() {
        try {
            ResultSet resultSet = statement.executeQuery("select Recipe_Name"
                    + " from Recipes");

            while (resultSet.next()) {
                rName += resultSet.getString(1) + "~";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPantry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("view inventory item could not be completed");
        }

        setRecipeArray();
    }

    /**
     * *
     * clears the rName and recipeNames for reuse
     */
    public void clearRecipeNames() {
        rName = "";
        recipeNames.clear();
    }

    /**
     * parses the recipe name string into an ArrayList for better use
     */
    public void setRecipeArray() {
        List<String> nameList = new ArrayList<>(Arrays.asList(rName.split("~")));
        for (String s : nameList) {
            recipeNames.add(String.valueOf(s));
        }
    }

    /**
     * setRecipeFromSQL - method that selects all the information for a given
     * recipe so that it can be viewed, and later updated.
     *
     * @param selection
     */
    public void setRecipeFromSQL(String selection) {
        try {
            String SQL = "Select Recipe_Name, Recipe_Description, Recipe_Type, "
                    + "Recipe_Link from "
                    + "Recipes Where Recipe_Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, selection);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                name = resultSet.getString(1);
                description = resultSet.getString(2);
                type = resultSet.getString(3);
                link = resultSet.getString(4);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateInventory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("getting recipe didn't work");
        }

        //set the result strings into the setItem method
        //setItem(name, description, type, link);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getLink() {
        return link;
    }

    /**
     * setIngredientsFromSQL - gathers information about all recipe_ingredients 
     * for a given recipe
     * @param selection 
     */
    public void setIngredientsFromSQL(String selection) {
        try {
            String SQL = "SELECT RI_ID, Ingredient_Name, Quantity_Required, Units_Required "
                    + "FROM Recipe_Ingredient WHERE Recipe_Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, selection);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ingredientID.add(resultSet.getInt(1));
                ingredientNames.add(resultSet.getString(2));
                ingredientQuant.add(resultSet.getInt(3));
                ingredientUnits.add(resultSet.getString(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateInventory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("getting recipe didn't work");
        }

       
    }

    public ArrayList<String> getIngredientNames() {
        return ingredientNames;
    }

    public ArrayList<Integer> getIngredientQuant() {
        return ingredientQuant;
    }

    public ArrayList<String> getIngredientUnits() {
        return ingredientUnits;
    }

    public ArrayList<Integer> getIngredientID() {
        return ingredientID;
    }

    public void clearIngredientNames() {
        ingredientNames.clear();
        ingredientQuant.clear();
        ingredientUnits.clear();
    }

   /**
    * updateRecipe - method updates the Recipe table with new information
    * @param n
    * @param d
    * @param t
    * @param l
    * @param selection
    * @return 
    */
    public ResultSet updateRecipe(String n, String d, String t, String l, String selection) {
        ResultSet rs = null;
        //Find Recipe_ID value based on name:
        try {
            String SQLOne = "Select Recipe_ID FROM Recipes WHERE Recipe_Name = ?";
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement(SQLOne);
            preparedStatement.setString(1, selection);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateInventory.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String SQL = "UPDATE Recipes SET "
                    + "Recipe_Name = ?, "
                    + "Recipe_Description = ?, "
                    + "Recipe_Type = ?, "
                    + "Recipe_Link = ? "
                    + "WHERE "
                    + "Recipe_ID = ?";

            callable = connection.prepareCall(SQL);
            callable.setString(1, n);
            callable.setString(2, d);
            callable.setString(3, t);
            callable.setString(4, l);
            callable.setInt(5, id);
            rs = callable.executeQuery();

            System.out.println(rs);

        } catch (SQLException ex) {
            System.out.println("result set not returned.");
        }
        
        return rs;
    }

   
    /**
     * update Recipe_Ingredient information
     *
     * @param rec
     * @param n
     * @param q
     * @param u
     * @param i
     * @return
     */
    public ResultSet updateRI(String rec, String n, Integer q, String u, int i) {
        ResultSet rs = null;

        try {
            String SQL = "UPDATE Recipe_Ingredient SET "
                    + "Recipe_Name = ?, "
                    + "Ingredient_Name = ?, "
                    + "Quantity_Required = ?, "
                    + "Units_Required = ? "
                    + "WHERE "
                    + "RI_ID = ?";

            callable = connection.prepareCall(SQL);
            callable.setString(1, rec);
            callable.setString(2, n);
            callable.setInt(3, q);
            callable.setString(4, u);
            callable.setInt(5, i);
            rs = callable.executeQuery();

            System.out.println(rs);

        } catch (SQLException ex) {
            System.out.println("result set not returned.");
        }
        updateIngredients();
        return rs;
    }

    /**
     * update ingredient key information to match after updates
     */
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
} //End Subclass UpdateRecipe

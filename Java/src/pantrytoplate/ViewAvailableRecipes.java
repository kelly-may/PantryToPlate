package pantrytoplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass ViewAvailableRecipes Description: subclass that shows recipes
 * available from Recipes table, based on inventory table.
 */
//Begin Subclass ViewAvailableRecipes
public class ViewAvailableRecipes extends DBConnection {

    public String rName = "";
    public String rDes = "";
    public String rType = "";
    public ArrayList<Integer> number = new ArrayList<>();
    public ArrayList<String> name = new ArrayList<>();
    public ArrayList<String> description = new ArrayList<>();
    public ArrayList<String> type = new ArrayList<>();
    private int value = 0;
    GridPane gPane = new GridPane();
    public String recipeLink = "";
    public String ingredients = "";
    
    Styles st = new Styles(); 

    /**
     * default constructor
     */
    public ViewAvailableRecipes() {
        try {
            statement = connection.createStatement();
            setAvailables();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database could not be connected.");
        }
    }

    /**
     * setAvailables - method uses a select/join query to display the names of
     * recipes that can be cooked with what is in the inventory.
     */
    public void setAvailables() {
        try {
            String queryString = "Select Recipes.Recipe_Name, Recipe_Description, Recipe_Type "
                    + "FROM Recipes "
                    + "JOIN Recipe_Ingredient ON (Recipe_Ingredient.Recipe_Name = Recipes.Recipe_Name) "
                    + "JOIN Inventory ON (Recipe_Ingredient.Ingredient_ID = Inventory.Ingredient_ID) "
                    + "WHERE Quantity >0 "
                    + "GROUP BY Recipes.Recipe_Name, Recipe_Description, Recipe_Type";

            PreparedStatement preparedStatement = connection.prepareStatement(queryString);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                number.add(value);
                name.add(resultSet.getString(1)); 
                description.add(resultSet.getString(2)); 
                type.add(resultSet.getString(3));                 
                value++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPantry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("view recipies could not be completed");
        }
        //setRecipeArrays();
    }

    public void setRecipeArrays() {
        List<String> nameList = new ArrayList<>(Arrays.asList(rName.split("~")));
        for (String s : nameList) {
            name.add(String.valueOf(s));
        }

        List<String> desList = new ArrayList<>(Arrays.asList(rDes.split("~")));
        for (String s : desList) {
            description.add(String.valueOf(s));
        }

        List<String> typeList = new ArrayList<>(Arrays.asList(rType.split("~")));
        for (String s : typeList) {
            type.add(String.valueOf(s));
        }
    }

    public ArrayList<String> getRecipeName() {
        return name;
    }

    public ArrayList<String> getRecipeDes() {
        return description;
    }

    public ArrayList<String> getRecipeType() {
        return type;
    }

    /**
     * getRecipes - GridPane for showing all the recipe information needed.
     *
     * @return
     */
    public ScrollPane getRecipes() {
        ScrollPane scroll = new ScrollPane();
        scroll.setStyle(st.scrollBackground());
        
        //gPane.setStyle(st.borderColor());
        gPane.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, null, null)));
        gPane.setPadding(new Insets(2, 2, 2, 2));
        gPane.setAlignment(Pos.TOP_CENTER);
        gPane.setHgap(8);
        gPane.setVgap(10);

        Label lblNum = new Label("Num:");
        Label lblName = new Label("Recipe Name:");
        Label lblDescription = new Label("Description:");
        Label lblType = new Label("Type:");

        gPane.add(lblNum, 0, 0);
        gPane.add(lblName, 1, 0);
        gPane.add(lblDescription, 2, 0);
        gPane.add(lblType, 3, 0);

        int row = 1;
        int i = 0;
        while (i < name.size()) {
            Label gridNum = new Label(Integer.toString(number.get(i)));
            Label gridName = new Label(String.valueOf((name.get(i))));
            Label gridDes = new Label(String.valueOf(description.get(i)));
            Label gridType = new Label(String.valueOf(type.get(i)));

            gPane.add(gridNum, 0, row);
            gPane.add(gridName, 1, row);
            gPane.add(gridDes, 2, row);
            gPane.add(gridType, 3, row);

            row++;
            i++;
        }
        scroll.setContent(gPane); 
        return scroll;
    }

    /**
     * clearAvailables - clears avail string for next database query
     */
    public void clearAvailables() {
        number.clear();
        name.clear();
        description.clear();
        type.clear();
        rName = "";
        rDes = "";
        rType = "";
        value = 0;
    }

    /**
     * Method for displaying a recipe's information and the link to the
     * instructions
     *
     * @param input
     */
    public void showARecipe(int input) {
        String recipeShow = name.get(input); //gets the name of the recipe

        //Create a URL link
        try {
            String SQL = "select Recipe_Link from Recipes where Recipe_Name = ?";
            PreparedStatement ps;
            ps = connection.prepareStatement(SQL);
            ps.setString(1, recipeShow);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                recipeLink = (resultSet.getString(1)); //makes recipe URL a string
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewPantry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            System.out.println("view recipies could not be completed");
        }

        //Show ingredient list
        try {
            ingredients = "";
            String method = "Select Ingredient_Name, Quantity_Required, "
                    + "Units_Required from Recipe_Ingredient where Recipe_Name = ?";
            PreparedStatement ps;
            ps = connection.prepareStatement(method);
            ps.setString(1, recipeShow);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //appends ingredients needed into a list/string
                ingredients += rs.getString(1) + ", " + rs.getString(2) + rs.getString(3) + "\n";
            }
        } catch (SQLException ex) {
            System.out.println("could not get recipe_ingredients");
        }

        // GUI Portion:
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, null, null)));
        vBox.setSpacing(10);

        Text title = new Text(recipeShow);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Label lblIngredients = new Label("Ingredients Needed:");
        Text txtIngredients = new Text(ingredients);
        Text txtWeb = new Text("Website Instructions:");
        txtWeb.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.REGULAR, 15));

        vBox.getChildren().addAll(title, lblIngredients, txtIngredients, txtWeb);

        //create a new scene and stage:
        Scene delScene = new Scene(vBox, 400, 400);
        Stage delStage = new Stage();
        delStage.setTitle(recipeShow);
        delStage.setScene(delScene);
        delStage.show();

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
        webEngine.load(recipeLink);
        vBox.getChildren().add(browser);
        vBox.setVgrow(browser, Priority.ALWAYS);

    }
} //End Subclass ViewAvailableRecipes

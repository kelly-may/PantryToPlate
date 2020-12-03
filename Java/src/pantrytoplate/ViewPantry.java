package pantrytoplate;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass ViewPantry Description: Class that connects to Microsoft SQL Server
 * database, then shows all values in the pantry. This is for the
 * "YourPantryPane"
 */
//Begin Subclass ViewPantry
public class ViewPantry extends DBConnection {

    private ArrayList<Integer> number = new ArrayList<>();
    private int value = 0;
    public String iName = "";
    public String iQuant = "";
    public String iUnit = "";
    public String iStorage = "";
    public String iExp = "";
    public String iType = "";
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<String> units = new ArrayList<>();
    private ArrayList<String> storage = new ArrayList<>();
    private ArrayList<String> expDate = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();
    GridPane gPane = new GridPane();

    Styles st = new Styles(); 
    /**
     * Constructor
     */
    public ViewPantry() {
        try {
            statement = connection.createStatement();
            setInventory(); //make inventory

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database could not be connected.");
        }
    }

    /**
     * setInventory - method that executes a select query for inventory items
     */
    public void setInventory() {
        try {
            ResultSet resultSet = statement.executeQuery("select Ingredient_Name, Quantity, Quantity_Units, "
                    + "Storage_Location, Expiration_Date, Ingredient_Type from Inventory");

            while (resultSet.next()) {
                number.add(value);
                iName += resultSet.getString(1) + "~";
                iQuant += resultSet.getString(2) + "~";
                iUnit += resultSet.getString(3) + "~";
                iStorage += resultSet.getString(4) + "~";
                iExp += resultSet.getString(5) + "~";
                iType += resultSet.getString(6) + "~";
                value++;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPantry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("view inventory could not be completed");
        }

        setInventoryArrays();
    }

    public void setInventoryArrays() {
        List<String> nameList = new ArrayList<>(Arrays.asList(iName.split("~")));
        for (String s : nameList) {
            name.add(String.valueOf(s));
        }

        List<String> quantList = new ArrayList<>(Arrays.asList(iQuant.split("~")));
        for (String s : quantList) {
            quantity.add(String.valueOf(s));
        }

        List<String> unitList = new ArrayList<>(Arrays.asList(iUnit.split("~")));
        for (String s : unitList) {
            units.add(String.valueOf(s));
        }

        List<String> storeList = new ArrayList<>(Arrays.asList(iStorage.split("~")));
        for (String s : storeList) {
            storage.add(String.valueOf(s));
        }

        List<String> expList = new ArrayList<>(Arrays.asList(iExp.split("~")));
        for (String s : expList) {
            expDate.add(String.valueOf(s));
        }

        List<String> typeList = new ArrayList<>(Arrays.asList(iType.split("~")));
        for (String s : typeList) {
            type.add(String.valueOf(s));
        }
    }

    public ArrayList<String> getIngredientName() {
        return name;
    }

    public ArrayList<String> getIngredientQuant() {
        return quantity;
    }

    public ArrayList<String> getIngredientUnits() {
        return units;
    }

    public ArrayList<String> getIngredientStorage() {
        return storage;
    }

    public ArrayList<String> getIngredientExp() {
        return expDate;
    }

    public ArrayList<String> getIngredientType() {
        return type;
    }

    /**
     * getInventory - method returns the inventory string
     *
     * @return
     */
    public ScrollPane getInventory() {
        ScrollPane scroll = new ScrollPane();
        scroll.setStyle(st.scrollBackground());
        
        //gPane.setStyle(st.borderColor());
        gPane.setBackground(new Background(new BackgroundFill(Color.PAPAYAWHIP, null, null)));
        gPane.setPadding(new Insets(2, 2, 2, 2));
        gPane.setAlignment(Pos.TOP_CENTER);
        gPane.setHgap(15);
        gPane.setVgap(10);

        Label lblNum = new Label("Num:");
        Label lblName = new Label("Ingredient Name:");
        Label lblQuant = new Label("Quantity:");
        Label lblUnit = new Label("Units:");
        Label lblStore = new Label("Storage Location:");
        Label lblExp = new Label("Exp Date:");
        Label lblType = new Label("Type:");

        gPane.add(lblNum, 0, 0);
        gPane.add(lblName, 1, 0);
        gPane.add(lblQuant, 2, 0);
        gPane.add(lblUnit, 3, 0);
        gPane.add(lblStore, 4, 0);
        gPane.add(lblExp, 5, 0);
        gPane.add(lblType, 6, 0);

        int row = 1;
        int i = 0;
        while (i < name.size()) {
            Label gridNum = new Label(Integer.toString(number.get(i)));
            Label gridName = new Label(String.valueOf((name.get(i))));
            Label gridQuant = new Label(String.valueOf(quantity.get(i)));
            Label gridUnit = new Label(String.valueOf(units.get(i)));
            Label gridStore = new Label(String.valueOf(storage.get(i)));
            Label gridDate = new Label(String.valueOf(expDate.get(i)));
            Label gridType = new Label(String.valueOf(type.get(i)));

            gPane.add(gridNum, 0, row);
            gPane.add(gridName, 1, row);
            gPane.add(gridQuant, 2, row);
            gPane.add(gridUnit, 3, row);
            gPane.add(gridStore, 4, row);
            gPane.add(gridDate, 5, row);
            gPane.add(gridType, 6, row);

            row++;
            i++;
        }

        scroll.setContent(gPane); 
        return scroll;
    }

    /**
     * clearInventory - clears the inventory string for updates to database
     */
    public void clearInventory() {
        number.clear();
        name.clear();
        quantity.clear();
        units.clear();
        storage.clear();
        expDate.clear();
        type.clear();
        value = 0;
        iName = "";
        iQuant = "";
        iUnit = "";
        iStorage = "";
        iExp = "";
        iType = "";

    }

} //End Subclass ViewPantry

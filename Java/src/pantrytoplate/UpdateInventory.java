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
import javafx.beans.property.SimpleStringProperty;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass UpdateInventory Description:class that controls the actions needed
 * to update something about an inventory item. uses SQL stored procedures.
 */
//Imports
//Begin Subclass UpdateInventory
public class UpdateInventory extends DBConnection {

    public ArrayList<String> inventoryNames = new ArrayList<>();
    public String iName = "";

    //creating a tableview for an inventory item to update
    private SimpleStringProperty itemName;
    private int itemQuant;
    private SimpleStringProperty itemUnit;
    private SimpleStringProperty itemLocation;
    private LocalDate itemDate;
    private SimpleStringProperty itemType;
    public String name;
    public int quant;
    public String units;
    public String location;
    public String sDate;
    public LocalDate date;
    public String type;
    public Date updateDate;
    private int id;
    public CallableStatement callable = null;

    /**
     * Default Constructor
     */
    public UpdateInventory() {
        try {
            statement = connection.createStatement();
            setInventoryNames();

        } catch (SQLException e) {
            System.out.println("Database could not be connected.");
        }
    }

    public void setInventoryNames() {
        try {
            ResultSet resultSet = statement.executeQuery("select Ingredient_Name"
                    + " from Inventory");

            while (resultSet.next()) {
                iName += resultSet.getString(1) + "~";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewPantry.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("view inventory item could not be completed");
        }

        setInventoryArray();
    }

    public void clearInventoryNames() {
        iName = "";
        inventoryNames.clear(); 
    }

    public void setInventoryArray() {
        List<String> nameList = new ArrayList<>(Arrays.asList(iName.split("~")));
        for (String s : nameList) {
            inventoryNames.add(String.valueOf(s));
        }
    }

    public ArrayList<String> getInventoryNames() {
        return inventoryNames;
    }

    /**
     * METHODS FOR FILLING THE TABLEVIEW!!!
     *
     * @param name
     * @param quant
     * @param units
     * @param location
     * @param date
     * @param type
     */
    public void setItem(String name, int quant, String units,
            String location, LocalDate date, String type) {
        this.itemName = new SimpleStringProperty(name);
        this.itemQuant = quant;
        this.itemUnit = new SimpleStringProperty(units);
        this.itemLocation = new SimpleStringProperty(location);
        this.itemDate = date;
        this.itemType = new SimpleStringProperty(type);
    }

    public String getName() {
        return itemName.get();
    }

    public int getQuant() {
        return itemQuant;
    }

    public String getUnits() {
        return itemUnit.get();
    }

    public String getLocation() {
        return itemLocation.get();
    }

    public LocalDate getDate() {
        return itemDate;
    }

    public String getType() {
        return itemType.get();
    }

    public void setItemsFromSQL(String selection) {
        try {
            String SQL = "Select Ingredient_Name, Quantity, Quantity_Units, "
                    + "Storage_Location, Expiration_Date, Ingredient_Type from "
                    + "Inventory Where Ingredient_Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, selection);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                name = resultSet.getString(1);
                quant = resultSet.getInt(2);
                units = resultSet.getString(3);
                location = resultSet.getString(4);
                sDate = resultSet.getString(5);
                type = resultSet.getString(6);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UpdateInventory.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("getting items didn't work");
        }
        //parse date into LocalDate:
        date = LocalDate.parse(sDate);

        //set the result strings into the setItem method
        setItem(name, quant, units, location, date, type);
    }

    public ResultSet update(String n, int q, String u, String l,
            Date d, String t, String selection) {
        ResultSet rs = null;
        //Find Ingredient_ID value based on name:
        try {
            String SQLOne = "Select Ingredient_ID FROM Inventory WHERE Ingredient_Name = ?";
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
            String SQL = "UPDATE Inventory SET "
                    + "Ingredient_Name = ?, "
                    + "Quantity = ?, "
                    + "Quantity_Units = ?, "
                    + "Storage_Location = ?, "
                    + "Expiration_Date = ?, "
                    + "Ingredient_Type = ? "
                    + "WHERE "
                    + "Ingredient_ID = ?";

            callable = connection.prepareCall(SQL);
            callable.setString(1, n);
            callable.setInt(2, q);
            callable.setString(3, u);
            callable.setString(4, l);
            callable.setDate(5, d);
            callable.setString(6, t);
            callable.setInt(7, id);
            rs = callable.executeQuery();

            System.out.println(rs);

        } catch (SQLException ex) {
            System.out.println("result set not returned.");
        }
        return rs;
    }

} //End Subclass UpdateInventory

package pantrytoplate;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass InsertInventory Description:class that controls the actions needed
 * to insert an ingredient/item into the database inventory table. Uses a SQL
 * stored procedure
 */
//Imports
//Begin Subclass InsertInventory
public final class InsertInventory extends DBConnection {

    public CallableStatement callable = null;
    public String ingName;
    public int quant;
    public String ingUnits;
    public String ingLocation;
    public Date expDate;
    public String ingType;

    /**
     * Default Constructor
     */
    public InsertInventory(String name, int quantity, String units, String location, Date date, String type) {
        try {
            this.ingName = name;
            this.quant = quantity;
            this.ingUnits = units;
            this.ingLocation = location;
            this.expDate = date;
            this.ingType = type;
            statement = connection.createStatement();
            insert(ingName, quant, ingUnits, ingLocation, expDate, ingType);
        } catch (SQLException e) {
            System.out.println("Database could not be connected.");
        }
    }

    public void insert(String name, int quantity, String units, String location, Date date, String type) {
        try {
            String method = "{call Insert_Inventory(?,?,?,?,?,?)}";
            callable = connection.prepareCall(method);
            callable.setString(1, name);
            callable.setInt(2, quantity);
            callable.setString(3, units);
            callable.setString(4, location);
            callable.setDate(5, date);
            callable.setString(6, type);
            ResultSet rs = callable.executeQuery(); // call stored procedure. 
        } catch (SQLException ex) {
            System.out.println("Insert Ingredient SP issue");
        }
    }

} //End Subclass InsertInventory

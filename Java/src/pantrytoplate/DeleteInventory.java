package pantrytoplate;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 5, 2020
 * @Subclass DeleteInventory Description:class that controls the actions needed
 * to delete an ingredient/item into the database inventory table. Uses a SQL
 * stored procedure
 */
//Imports
//Begin Subclass DeleteInventory
public final class DeleteInventory extends DBConnection {

    public CallableStatement callable = null;
    public String name;

    /**
     * Constructor
     */
    DeleteInventory(String n) {
        try {
            this.name = n;
            statement = connection.createStatement();
            delete(name);

        } catch (SQLException e) {
            System.out.println("Database could not be connected.");
        }
    }

    public void delete(String n) {
        try {
            String method = "{call Delete_Inventory(?)}";
            callable = connection.prepareCall(method);
            callable.setString(1, n);
            ResultSet rs = callable.executeQuery(); // call stored procedure. 
//            while (rs.next()) {
//                System.out.println(rs.getString(1)); // prints rows from result set
//            }
        } catch (SQLException ex) {
            System.out.println("Delete_Inventory Stored Procedure Issue");
        }
    }

} //End Subclass DeleteInventory

package pantrytoplate;

/**
 * * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 9, 2020
 * @Subclass Verify Description: Class for verifying inputs
 */
//Imports
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//Begin Subclass Verify

public class Verify extends DBConnection {

    public int num = 0;
    public CallableStatement callable = null;

    /**
     * Default Constructor - simply connects to db
     */
    public Verify() {
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Database could not be connected");
        }
    }

    /**
     * method that returns a number if a recipe already exists. returns 0 if no
     * recipe exists yet.
     *
     * @param selection
     * @return
     */
    public int recipeExists(String selection) {
        try {
            String method = "SELECT Recipe_ID FROM Recipes "
                    + "WHERE Recipe_Name = ?";
            PreparedStatement ps = connection.prepareStatement(method);
            ps.setString(1, selection);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                num = rs.getInt(1);
                System.out.println(num); 
            }
        } catch (SQLException ex) {
            System.out.println("Problem verifying if recipeExists");
            System.out.println("num " + num);
        }
        return num; 
    }
    
   

} //End Subclass Verify

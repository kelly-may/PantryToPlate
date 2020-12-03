

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jun 18, 2020
 * @Subclass sqlDAO Description:
 * https://www.developer.com/java/data/working-with-the-javafx-ui-and-jdbc-applications.html
 * use this site to figure out database connection this class is used to connect
 * the sql database to java.
 */
//Imports
//Begin Subclass sqlDAO
public class sqlDAO {

    public static final String DB_URL = "jdbc:sqlserver://RAINBOW-SPARKLE\\SQLEXPRESS;"
            + "databaseName=Pantry_To_Plate";
    //public static final String DRIVER = ""; 
    public static final String USER = "kelly";
    public static final String PASS = " "; //spacebar//
    

    public sqlDAO() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to Microsoft SQL Server");
        } catch (SQLException ex) {
            Logger.getLogger(sqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database connection not working");
            ex.printStackTrace();
        }
    }
}//End Subclass sqlDAO

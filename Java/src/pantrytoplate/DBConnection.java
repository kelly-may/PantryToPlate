package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jun 20, 2020
 * @Subclass DBConnection Description: Parent class that connects Microsoft SQL
 * Server Database to Java program with JDBC driver. Subclasses are different
 * SQL queries.
 */
//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//Begin Subclass DBConnection
public class DBConnection {

    protected static String DB_URL
            = "jdbc:sqlserver://RAINBOW-SPARKLE\\SQLEXPRESS:1433;databaseName=PantryToPlate;user=kelly;password= ";
    protected static String USER = "kmay";
    protected static String PASS = "password"; //spacebar//
    protected Connection connection;
    protected Statement statement;

    /**
     * Default Constructor - connects database
     */
    public DBConnection() {
        try {
            //Class.forName("com.microsoft.sqlserver.jdbc.sqlserverdriver");
            System.out.println("Driver Loaded");

            //establish connection:
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to Microsoft SQL Server.");

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Database connection not working.");
            ex.printStackTrace();

        }
    }

    /**
     * closeConnection - closes the SQL connection.
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("DB could not be disconnected.");
        }
    }

} //End Subclass DBConnection

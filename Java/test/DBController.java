
/**
*  @Course: SDEV 250 ~ Java Programming I
*  @Author Name: Kelly
*  @Assignment Name: pantrytoplate
*  @Date: Jun 20, 2020
*  @Subclass DBController Description:
*/
//Imports
 import javafx.scene.control.TextField;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
 import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
 import javax.print.DocFlavor.URL;
//Begin Subclass DBController
public class DBController {
    //inventory objects
    @FXML
    public TextField txtIngredientID; 
    @FXML
    public TextField txtIngredientName; 
    @FXML
    public TextField txtQuantity; 
    @FXML
    public TextField txtQuantityUnits; 
    @FXML
    public TextField txtStorageLocation;
    @FXML
    public TextField txtExpirationDate; 
    @FXML
    public TextField txtIngredientType; 
    
    //recipe objects
    @FXML
    public TextField txtRecipeID; 
    @FXML
    public TextField txtRecipeName; 
    @FXML
    public TextField txtDescription; 
    @FXML
    public TextField txtRecipeType; 
    @FXML
    public TextField txtRecipeLink; 
    
    //recipeIngredient objects
    @FXML
    public TextField txtRiID; 
    @FXML 
    public TextField txtRiRecipeID; 
    @FXML
    public TextField txtRiRecipeName; 
    @FXML
    public TextField txtRiIngredientID; 
    @FXML
    public TextField txtRiIngredientName; 
    @FXML
    public TextField txtQuantityRequired; 
    @FXML
    public TextField txtUnitsRequired; 
    
    public Connection conn = null; 
    public PreparedStatement pat = null; 
    
    @FXML
    public void Rmsubmit(ActionEvent actionEvent){
        
    }
    
    

} //End Subclass DBController

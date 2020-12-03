package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jul 11, 2020
 * @Subclass Actions Description: Java class that houses the GUI and actions for
 * inserting an item in the inventory
 */
//Imports
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//Begin Subclass ActionsInsertInventory

public class ActionsInsertInventory {

    //Style object
    Styles st = new Styles();
    //Alert object
    Alerts alert = new Alerts();
    //Initialize additem entries:
    String itemName = "";
    int itemQuant = 0;
    String itemUnits = "";
    String itemLocation = "";
    String itemType = "";
    Date itemExp;

    //addItem entry fields:
    TextField txtName = new TextField();
    Spinner<Integer> spQuant = new Spinner<>();
    TextField txtUnits = new TextField();
    TextField txtLocation = new TextField();
    DatePicker dtExp;
    TextField txtType = new TextField();

    //iterators for adding textfields.
    final int MAX = 20;

    /**
     * Default Constructor
     */
    public ActionsInsertInventory() {

    }

    /**
     * addItemActions - creates a stage for adding an item into the inventory
     * uses a SQL CallableStatement from the InsertInventory class
     */
    public void addItemActions() {

        //create pane
        BorderPane bPane = new BorderPane();
        bPane.setBackground(new Background(st.bgColor()));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(10);
        titleBox.setPadding(new Insets(10));
        titleBox.setStyle(st.borderColor());
        Text title = new Text("Insert an Item into the Inventory");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Label instructions = new Label("Enter the values of the item below: ");
        titleBox.getChildren().addAll(title, instructions);

        GridPane entries = new GridPane();
        entries.setPadding(new Insets(10));
        entries.setHgap(10);
        entries.setVgap(10);

        Label lblName = new Label("Item Name:");
        Label lblQuant = new Label("Item Quantity:");
        Label lblUnits = new Label("Quantity Units:");
        Label lblLocation = new Label("Item Storage Location:");
        Label lblExp = new Label("Expiration Date:");
        Label lblType = new Label("Item Type:");

        int initialValue = 0;
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, initialValue);
        spQuant.setValueFactory(valueFactory);
        //TextField txtQuant = new TextField();

        dtExp = new DatePicker();

        entries.add(lblName, 0, 0);
        entries.add(txtName, 1, 0);
        entries.add(lblQuant, 0, 1);
        entries.add(spQuant, 1, 1);
        entries.add(lblUnits, 0, 2);
        entries.add(txtUnits, 1, 2);
        entries.add(lblLocation, 0, 3);
        entries.add(txtLocation, 1, 3);
        entries.add(lblExp, 0, 4);
        entries.add(dtExp, 1, 4);
        entries.add(lblType, 0, 5);
        entries.add(txtType, 1, 5);

        Button btEnter = new Button("Enter");
        Button btExit = new Button("Exit");
        btExit.setStyle(st.buttonStyle());
        btEnter.setStyle(st.buttonStyle());
        vBox.getChildren().addAll(entries, btEnter, btExit);
        bPane.setTop(titleBox);
        bPane.setCenter(vBox);

        //create a new scene and stage:
        Scene addScene = new Scene(bPane, 300, 400);
        Stage addStage = new Stage();
        addStage.setTitle("Add Item to Inventory");
        addStage.setScene(addScene);
        addStage.show();
        //deleteInventory SQL object:
        btEnter.setOnAction((ActionEvent e) -> {
            //ADD ERROR HANDLING/INPUT VALIDATION HERE******************
            ViewPantry vp = new ViewPantry(); 
            vp.setInventory();
            
            if (txtName.getText().isEmpty() || txtUnits.getText().isEmpty() || txtLocation.getText().isEmpty()
                    || txtType.getText().isEmpty()) {
                alert.emptyEntryAlert(); //if any entry is empty, alert user. 
            }                    
            else if(vp.getIngredientName().contains(txtName.getText())){
                //check to see if item is already in inventory
                    alert.ingredientExists(); 
                    }
            else {
                try {
                    itemName = txtName.getText();
                } catch (Exception ex) {
                    alert.badNameAlert();
                    txtName.clear();
                    txtName.requestFocus();
                }
                try {
                    itemQuant = spQuant.getValue();
                } catch (NumberFormatException ex) {
                    alert.badQuantAlert();
                    spQuant.requestFocus();
                }
                try {
                    itemUnits = txtUnits.getText();
                } catch (Exception ex) {
                    alert.badUnitsAlert();
                    txtUnits.clear();
                    txtUnits.requestFocus();
                }
                try {
                    itemLocation = txtLocation.getText();
                } catch (Exception ex) {
                    alert.badLocationAlert();
                    txtLocation.clear();
                    txtLocation.requestFocus();
                }
                itemExp = Date.valueOf(dtExp.getValue());
                try {
                    itemType = txtType.getText();
                } catch (Exception ex) {
                    alert.badTypeAlert();
                    txtType.clear();
                    txtType.requestFocus();
                }
                addInventoryCheck(itemName, itemQuant, itemUnits, itemLocation,
                        itemExp, itemType); // if inventory check confirmed, perform the following:
            }
        });
        btExit.setOnAction(e -> {
            addStage.close();
        });
    }

    /**
     * addInventoryCheck - confirmation page that appears once user has entered
     * information.
     *
     * @param name
     * @param quant
     * @param units
     * @param location
     * @param exp
     * @param type
     * @return
     */
    public BorderPane addInventoryCheck(String name, int quant, String units,
            String location, Date exp, String type) {
        Stage checker = new Stage();

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 300, 350);
        pane.setStyle(st.scrollBackground());
        pane.setPadding(new Insets(10));

        //----------------------Titles
        VBox titles = new VBox();
        titles.setAlignment(Pos.CENTER);
        Text title = new Text("Add to Inventory Confirmation Page");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Text subtitle = new Text("Please confirm that the following information"
                + "is correct.");
        titles.getChildren().addAll(title, subtitle);

        //-------------------Confirmation Buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);

        Button btConfirm = new Button("Confirm");
        Button btEdit = new Button("Edit");
        btConfirm.setStyle(st.buttonStyle());
        btEdit.setStyle(st.buttonStyle());
        buttons.getChildren().addAll(btConfirm, btEdit);

        //--------------------Entry information
        GridPane check = new GridPane();
        check.setAlignment(Pos.CENTER);
        check.setPadding(new Insets(10));
        check.setHgap(10);
        check.setVgap(10);

        check.add(new Label("Item Name:"), 0, 0);
        check.add(new Label(name), 1, 0);

        check.add(new Label("Quantity:"), 0, 1);
        check.add(new Label(String.valueOf(quant)), 1, 1);
        check.add(new Label(units), 2, 1);

        check.add(new Label("Storage Location:"), 0, 2);
        check.add(new Label(location), 1, 2);

        check.add(new Label("Expiration Date:"), 0, 3);
        check.add(new Label(String.valueOf(exp)), 1, 3);

        check.add(new Label("Type of Item:"), 0, 4);
        check.add(new Label(type), 1, 4);

        //---------Add to BorderPane:
        pane.setTop(title);
        pane.setCenter(check);
        pane.setBottom(buttons);

        //------------stage
        checker.setTitle("Verify Data");
        checker.setScene(scene);
        checker.show();

        //---------------ActionsInsertInventory
        btConfirm.setOnAction(e -> {
            InsertInventory ii = new InsertInventory(itemName, itemQuant,
                    itemUnits, itemLocation, itemExp, itemType);
            UpdateRecipe ur = new UpdateRecipe(); 
            ur.updateIngredients();
            txtName.clear();
            txtUnits.clear();
            txtLocation.clear();
            txtType.clear();
            checker.close();
        });
        btEdit.setOnAction(e -> {
            checker.close();
        });

        return pane;

    }

} //End Subclass ActionsInsertInventory

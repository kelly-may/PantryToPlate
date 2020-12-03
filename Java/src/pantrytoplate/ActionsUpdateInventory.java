package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 8, 2020
 * @Subclass UpdateInventoryActions Description: Class for GUI and actions
 * performed when user updates an item in the inventory.
 */
//Imports
import java.sql.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//Begin Subclass ActionsUpdateInventory

public class ActionsUpdateInventory {

    Styles st = new Styles();
    Alerts alert = new Alerts();

    //textfields for editing:
    Label lblInventory = new Label("Select an Item:");
    Label lblName = new Label("Name:");
    Label lblQuant = new Label("Quantity");
    Label lblUnits = new Label("Units:");
    Label lblLocation = new Label("Location:");
    Label lblDate = new Label("Expiration Date:");
    Label lblType = new Label("Type:");
    TextField txtName = new TextField();
    Spinner<Integer> txtQuant = new Spinner<>();
    TextField txtUnits = new TextField();
    TextField txtLocation = new TextField();
    DatePicker txtDate = new DatePicker();
    TextField txtType = new TextField();
    
    Stage delStage = new Stage();

    /**
     * Default Constructor
     */
    public ActionsUpdateInventory() {

    }

    /**
     * modifyItemActions - method for updating an item in the inventory of the
     * database. User selects an inventory item, then views all components of
     * the item/changes the values of these components
     *
     */
    public void modifyItemActions() {
        UpdateInventory ui = new UpdateInventory();

        // modify an item from inventory
        BorderPane bPane = new BorderPane();
        bPane.setBackground(new Background(st.bgColor()));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        titleBox.setSpacing(5);
        titleBox.setStyle(st.borderColor());
        Text title = new Text("Update Item Information");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 25));
        Label instructions = new Label("Select the Inventory Item you want to "
                + "update, then \nchange what you would like to change. Inventory \nnames will"
                + " update in comboBox when you \nclose and reopen this page.");
        titleBox.getChildren().addAll(title, instructions);

        bPane.setTop(titleBox);

        ComboBox cbInventory = new ComboBox();
        ui.clearInventoryNames(); // clear old names
        ui.setInventoryNames();
        cbInventory.getItems().addAll(ui.inventoryNames);
        cbInventory.setVisibleRowCount(5);

        Button btUpdate = new Button("Update");
        Button btClose = new Button("Close Updater");
        btUpdate.setStyle(st.buttonStyle());
        btClose.setStyle(st.buttonStyle());

        int initialValue = 0;
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, initialValue);
        txtQuant.setValueFactory(valueFactory);
        //TextField txtQuant = new TextField();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(lblInventory, 0, 0);
        grid.add(cbInventory, 1, 0);
        grid.add(lblName, 0, 1);
        grid.add(txtName, 1, 1);
        grid.add(lblQuant, 0, 2);
        grid.add(txtQuant, 1, 2);
        grid.add(lblUnits, 0, 3);
        grid.add(txtUnits, 1, 3);
        grid.add(lblLocation, 0, 4);
        grid.add(txtLocation, 1, 4);
        grid.add(lblDate, 0, 5);
        grid.add(txtDate, 1, 5);
        grid.add(lblType, 0, 6);
        grid.add(txtType, 1, 6);

        bPane.setCenter(grid);
        vBox.getChildren().addAll(btUpdate, btClose);
        vBox.setStyle(st.borderColor());
        bPane.setBottom(vBox);
        cbInventory.setOnAction(e -> {
            //call UpdateInventory object:
            ui.setItemsFromSQL(cbInventory.getValue().toString());
            txtName.setText(ui.getName());
            txtQuant.getValueFactory().setValue(ui.getQuant());
            txtUnits.setText(ui.getUnits());
            txtLocation.setText(ui.getLocation());
            txtDate.setValue(ui.getDate());
            txtType.setText(ui.getType());

            ui.clearInventoryNames();

        });

        btUpdate.setOnAction(e -> {
            String getName = txtName.getText();
            int getQuant = txtQuant.getValue();
            String getUnits = txtUnits.getText();
            String getLocation = txtLocation.getText();
            Date getDate = Date.valueOf(txtDate.getValue());
            String getType = txtType.getText();
            String getSelection = cbInventory.getValue().toString();

            //make sure values are entered:
            if (getName.isEmpty() || getUnits.isEmpty() || getLocation.isEmpty()
                    || getType.isEmpty() || getSelection.isEmpty()) {
                alert.emptyEntryAlert();
            } else {
                //update database confirmation page:
                modifyItemChecker(getSelection);
            }
        });

        //create a new scene and stage:
        Scene delScene = new Scene(bPane, 300, 500);
        
        delStage.setTitle("Update Item from Inventory");
        delStage.setScene(delScene);
        delStage.show();

        //close stage 
        btClose.setOnAction(e -> {
            delStage.close();
        });

    }

    /**
     * modifyItemChecker - confirmation page that appears when user enters
     * information
     *
     * @param getSelection
     * @return
     */
    public BorderPane modifyItemChecker(String getSelection) {
        UpdateInventory ui = new UpdateInventory(); // for viewing recipeNames
        BorderPane pane = new BorderPane();
        Stage checker = new Stage();

        Scene scene = new Scene(pane, 300, 350);
        pane.setStyle(st.scrollBackground());
        pane.setPadding(new Insets(10));

        //----------------------Titles
        VBox titles = new VBox();
        titles.setAlignment(Pos.CENTER);
        Text title = new Text("Delete from Inventory Confirmation Page");
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

        //--------------------Entry Information
        GridPane check = new GridPane();
        check.setAlignment(Pos.CENTER);
        check.setPadding(new Insets(10));
        check.setHgap(10);
        check.setVgap(10);

        String getName = txtName.getText();
        int getQuant = txtQuant.getValue();
        String getUnits = txtUnits.getText();
        String getLocation = txtLocation.getText();
        Date getDate = Date.valueOf(txtDate.getValue());
        String getType = txtType.getText();

        check.add(new Label("Item Name:"), 0, 0);
        check.add(new Label(getName), 1, 0);
        check.add(new Label("Quantity:"), 0, 1);
        check.add(new Label(String.valueOf(getQuant)), 1, 1);
        check.add(new Label(getUnits), 2, 1);
        check.add(new Label("Location:"), 0, 2);
        check.add(new Label(getLocation), 1, 2);
        check.add(new Label("Expiration Date:"), 0, 3);
        check.add(new Label(String.valueOf(getDate)), 1, 3);
        check.add(new Label("Type:"), 0, 4);
        check.add(new Label(getType), 1, 4);

        //---------Add to BorderPane:
        pane.setTop(title);
        pane.setCenter(check);
        pane.setBottom(buttons);

        //------------stage
        checker.setTitle("Verify Data");
        checker.setScene(scene);
        checker.show();

        //---------------Actions
        btConfirm.setOnAction(e -> {
            ui.update(getName, getQuant, getUnits, getLocation, getDate, getType, getSelection);
            ui.clearInventoryNames();
            checker.close();
            delStage.close(); 
            modifyItemActions(); 
        });
        btEdit.setOnAction(e -> {
            checker.close();
        });
        return pane;
    }

} //End Subclass ActionsUpdateInventory

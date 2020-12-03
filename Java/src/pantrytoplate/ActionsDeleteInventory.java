package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 8, 2020
 * @Subclass DeleteInventoryActions Description: Class for GUI and actions
 * performed when user deletes an item in the inventory/pantry
 */
//Imports
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
//Begin Subclass ActionsDeleteInventory

public class ActionsDeleteInventory {

    //Style object
    Styles st = new Styles();

    //Alert object
    Alerts alert = new Alerts();
    //initialize delete item entry:
    String entry = "";

    /**
     * Default Constructor
     */
    public ActionsDeleteInventory() {

    }

    /**
     * deleteItemActions - creates a stage for deleting an item from the
     * inventory uses a SQL CallableStatement from the DeleteInventory class
     */
    public void deleteItemActions() {
        // delete item from inventory
        UpdateInventory ui = new UpdateInventory(); // for viewing recipeNames

        //create pane
        BorderPane bPane = new BorderPane();
        bPane.setBackground(new Background(st.bgColor()));
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Delete an Item from the Inventory");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Label instructions = new Label("Enter the name of the item you want to delete: ");

        ComboBox cbSelect = new ComboBox();
        cbSelect.getItems().addAll(ui.inventoryNames);

        Button btEnter = new Button("Enter");
        btEnter.setStyle(st.buttonStyle());
        Button btClose = new Button("Exit");
        btClose.setStyle(st.buttonStyle());
        vBox.getChildren().addAll(instructions, cbSelect, btEnter, btClose);
        bPane.setTop(title);
        bPane.setCenter(vBox);

        //create a new scene and stage:
        Scene delScene = new Scene(bPane, 300, 150);
        Stage delStage = new Stage();
        delStage.setTitle("Delete Item from Inventory");
        delStage.setScene(delScene);
        delStage.show();
        //deleteInventory SQL object:
        btEnter.setOnAction((ActionEvent e) -> {
            //ADD ERROR HANDLING/INPUT VALIDATION HERE*****************
            try {
                entry = cbSelect.getValue().toString();
            } catch (Exception ex) {
                alert.notFoundAlert();
                cbSelect.requestFocus();
            }
            // ADD A CONFIRMATION PAGE HERE ********************
            deleteItemCheck(entry);
        });

        btClose.setOnAction(e -> {
            delStage.close();
        });

    }

    /**
     * deleteItemCheck - confirmation page that appears when user enters information to be deleted. 
     * @param entry
     * @return 
     */
    public BorderPane deleteItemCheck(String entry) {
        UpdateInventory ui = new UpdateInventory(); // for viewing recipeNames
        BorderPane pane = new BorderPane();
        Stage checker = new Stage();

        Scene scene = new Scene(pane, 420, 250);
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

        check.add(new Label("Inventory Name:"), 0, 0);
        check.add(new Label(entry), 1, 0);

        ui.setItemsFromSQL(entry);
        check.add(new Label("Quantity:"), 0, 1);
        check.add(new Label(String.valueOf(ui.getQuant())), 1, 1);
        check.add(new Label(ui.getUnits()), 2, 1);

        check.add(new Label("Storage Location:"), 0, 2);
        check.add(new Label(ui.getLocation()), 1, 2);

        check.add(new Label("Expiration Date:"), 0, 3);
        check.add(new Label(String.valueOf(ui.getDate())), 1, 3);

        //---------Add to BorderPane:
        pane.setTop(titles);
        pane.setCenter(check);
        pane.setBottom(buttons);

        //------------stage
        checker.setTitle("Verify Data");
        checker.setScene(scene);
        checker.show();

        //---------------Actions
        btConfirm.setOnAction(e -> {
            DeleteInventory di = new DeleteInventory(entry);
            checker.close();
        });
        btEdit.setOnAction(e -> {
            checker.close();
        });

        return pane;
    }
} //End Subclass ActionsDeleteInventory

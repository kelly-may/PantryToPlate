package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 9, 2020
 * @Subclass ActionsDeleteRecipe Description: class for GUI and actions for when
 * a user deletes a recipe in the cookbook.
 */
//Imports
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
//Begin Subclass ActionsDeleteRecipe

public class ActionsDeleteRecipe {

    Styles st = new Styles();
    Alerts alert = new Alerts();

    //initialize delete recipe entry
    String recEntry = "";

    /**
     * Default Constructor
     */
    public ActionsDeleteRecipe() {

    }

    /**
     * deleteRecipeActions - creates a stage for deleting a recipe, the recipe
     * ingredients from the database. uses a SQL CallableStatement from the
     * DeleteRecipe class
     */
    public void deleteRecipeActions() {
        ViewRecipes vr = new ViewRecipes();
        // delete a recipe
        //create pane
        BorderPane bPane = new BorderPane();
        bPane.setBackground(new Background(st.bgColor()));
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        Text title = new Text("Delete a Recipe from the CookBook");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Label instructions = new Label("Enter the name of the recipe you want to delete: ");
        ComboBox cbSelect = new ComboBox();
        cbSelect.getItems().addAll(vr.getRecipeName());

        // TextField tf = new TextField();
        Button btEnter = new Button("Enter");
        Button btExit = new Button("Exit");
        btEnter.setStyle(st.buttonStyle());
        btExit.setStyle(st.buttonStyle());
        vBox.getChildren().addAll(instructions, cbSelect, btEnter, btExit);
        bPane.setTop(title);
        bPane.setCenter(vBox);

        //create a new scene and stage:
        Scene delScene = new Scene(bPane, 300, 150);
        Stage delStage = new Stage();
        delStage.setTitle("Delete Recipe from CookBook");
        delStage.setScene(delScene);
        delStage.show();
       
        // ACTIONABLES:
        btEnter.setOnAction((ActionEvent e) -> {
            try {
                recEntry = cbSelect.getValue().toString();
            } catch (Exception ex) {
                alert.notFoundAlert();
                cbSelect.requestFocus();
            }

            if (recEntry.isEmpty()) { //if recEntry isn't chosen, alert user
                alert.emptyEntryAlert();
            } else {
                //confirmation page
                deleteRecipeCheck(recEntry);
            }
        });

        btExit.setOnAction(e -> {
            delStage.close();
        });
    }

    /**
     * deleteRecipeCheck - confirmation page that appears when user enters a
     * recipe that they wish to delete.
     *
     * @param entry
     * @return
     */
    public BorderPane deleteRecipeCheck(String entry) {
        BorderPane pane = new BorderPane();
        Stage checker = new Stage();

        Scene scene = new Scene(pane, 420, 250);
        pane.setStyle(st.scrollBackground());
        pane.setPadding(new Insets(10));

        //----------------Titles 
        VBox titles = new VBox();
        titles.setAlignment(Pos.CENTER);
        titles.setStyle(st.borderColor());
        Text title = new Text("Delete Recipe Confirmation Page");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        Text subtitle = new Text("Please confirm that the following information can be deleted"
                + "from the cookbook:");
        titles.getChildren().addAll(title, subtitle);

        //-----------------Confirmation Buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);

        Button btConfirm = new Button("Confirm");
        Button btEdit = new Button("Edit");
        btConfirm.setStyle(st.buttonStyle());
        btEdit.setStyle(st.buttonStyle());
        buttons.getChildren().addAll(btConfirm, btEdit);

        //-------------------Entry Information
        VBox values = new VBox();
        values.setAlignment(Pos.CENTER);

        GridPane check = new GridPane();
        check.setAlignment(Pos.CENTER);
        check.setPadding(new Insets(10));
        check.setHgap(10);
        check.setVgap(10);

        check.add(new Label("Recipe Name:"), 0, 0);
        check.add(new Label(entry), 1, 0);

        UpdateRecipe ur = new UpdateRecipe();
        ur.setRecipeFromSQL(entry);

        check.add(new Label("Description:"), 0, 1);
        check.add(new Label(ur.getDescription()), 1, 1);
        check.add(new Label("Type:"), 0, 2);
        check.add(new Label(ur.getType()), 1, 2);
        check.add(new Label("URL Link:"), 0, 3);
        check.add(new Label(ur.getLink()), 1, 3);

        GridPane ingredients = new GridPane();
        ingredients.setAlignment(Pos.CENTER);
        ur.setIngredientsFromSQL(entry);

        int row = 1;
        for (int i = 0; i < ur.ingredientID.size(); i++) {
            ingredients.add(new Label((i + 1) + "- Ingredient Name:"), 0, row);
            ingredients.add(new Label(ur.ingredientNames.get(i)), 1, row);
            row++;
            ingredients.add(new Label((i + 1) + "- Quantity:"), 0, row);
            ingredients.add(new Label(Integer.toString(ur.ingredientQuant.get(i))), 1, row);
            ingredients.add(new Label(ur.ingredientUnits.get(i)), 2, row);
            row++;
        }

        values.getChildren().addAll(check, ingredients);

        //------------------------Add to BorderPane:
        pane.setTop(titles);
        pane.setCenter(values);
        pane.setBottom(buttons);

        //------------stage
        checker.setTitle("Verify Data");
        checker.setScene(scene);
        checker.show();

        //---------------Actions
        btConfirm.setOnAction(e -> {
            DeleteRecipe dr = new DeleteRecipe(entry);
            checker.close();
        });
        btEdit.setOnAction(e -> {
            checker.close();
        });
        return pane;
    }

} //End Subclass ActionsDeleteRecipe

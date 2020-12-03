package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 9, 2020
 * @Subclass ActionsUpdateRecipe Description: Class for GUI and actions when
 * user updates a recipe in the cookbook
 */
//Imports
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
//Begin Subclass ActionsUpdateRecipe

public class ActionsUpdateRecipe {

    Alerts alert = new Alerts();
    Styles st = new Styles();

    Stage addStage = new Stage();
    public String getRecipeName;
    public String getRecipeDescription;
    public String getRecipeType;
    public String getRecipeLink;

    ComboBox cbSelect = new ComboBox<>();
    ArrayList<Integer> id = new ArrayList<>();
    int numIngredients;

    ArrayList<TextField> txtIngredient = new ArrayList<>();
    ArrayList<Spinner<Integer>> txtQuant = new ArrayList<>();
    ArrayList<TextField> txtUnit = new ArrayList<>();

    /**
     * Default Constructor
     */
    public ActionsUpdateRecipe() {
    }

    /**
     * modifyRecipeActions - creates a stage for updating a recipe in the
     * database. Allows user to update the recipe description information and
     * all recipe_ingredient information.
     */
    public void modifyRecipeActions() {
        UpdateRecipe ur = new UpdateRecipe();

        //main pane
        BorderPane bPane = new BorderPane();
        bPane.setBackground(new Background(st.bgColor()));

        //------------------------------- TITLE
        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        titleBox.setStyle(st.borderColor());

        Text title = new Text("Update Recipe Information");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Text instructions = new Text("Select a Recipe you wish to update, then update any field(s).");
        Text instructions2 = new Text("Press Update Recipe when you are done.");
        titleBox.getChildren().addAll(title, instructions, instructions2);

        //----------------------------- BUTTONS
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle(st.borderColor());
        buttons.setPadding(new Insets(10));
        buttons.setSpacing(10);
        Button btUpdate = new Button("Update Recipe");
        btUpdate.setStyle(st.buttonStyle());
        Button btClose = new Button("Close");
        btClose.setStyle(st.buttonStyle());
        buttons.getChildren().addAll(btUpdate, btClose);

        //---------------------------CENTER
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));
        ScrollPane scroll = new ScrollPane();
        scroll.setStyle(st.scrollBackground());
        scroll.setContent(vBox);

        //Recipe Selector:
        Label lblSelect = new Label("Select a Recipe:");

        // cbSelect.getItems().addAll(ur.recipeNames);
        ObservableList<String> data = FXCollections.observableArrayList(ur.recipeNames);
        cbSelect = new ComboBox<>(data);
        cbSelect.setVisibleRowCount(5);

        //Recipe Grid
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(lblSelect, 0, 0);
        grid.add(cbSelect, 1, 0);

        //Recipe Information
        Label lblName = new Label("Recipe Name:");
        Label lblDescription = new Label("Description:");
        Label lblType = new Label("Type:");
        Label lblLink = new Label("Link:");
        TextField txtName = new TextField();
        TextField txtDescription = new TextField();
        TextField txtType = new TextField();
        TextField txtLink = new TextField();

        grid.add(lblName, 0, 2);
        grid.add(txtName, 1, 2);
        grid.add(lblDescription, 0, 3);
        grid.add(txtDescription, 1, 3);
        grid.add(lblType, 0, 4);
        grid.add(txtType, 1, 4);
        grid.add(lblLink, 0, 5);
        grid.add(txtLink, 1, 5);

        vBox.getChildren().add(grid);

        //set title, center, buttons into border pane
        bPane.setTop(titleBox);
        bPane.setCenter(scroll);
        bPane.setBottom(buttons);

        //----------------------------SCENE AND STAGE
        final Scene addScene = new Scene(bPane, 350, 600);

        addStage.setTitle("Update Recipe");
        addStage.setScene(addScene);
        addStage.show();

        //----------------------------ACTIONS
        cbSelect.setOnAction(e -> {
            if (txtIngredient.isEmpty()) { //only works if nothing has been selected
                //recipe info
                ur.setRecipeFromSQL(cbSelect.getValue().toString());
                txtName.setText(ur.getName());
                txtDescription.setText(ur.getDescription());
                txtType.setText(ur.getType());
                txtLink.setText(ur.getLink());

                //ingredient info:
                ur.setIngredientsFromSQL(cbSelect.getValue().toString());
                int numIngredients = ur.getIngredientNames().size();
                int row = 1;
                //make number of text fields for each ingredient
                //grid for recipe_ingredients
                GridPane iGrid = new GridPane();
                iGrid.setAlignment(Pos.CENTER);
                iGrid.setHgap(10);
                iGrid.setVgap(10);
                for (int i = 0; i < numIngredients; i++) {

                    iGrid.add(new Label(String.valueOf(i + 1) + "- Ingredient Name:"), 0, row);
                    txtIngredient.add(new TextField());
                    iGrid.add(txtIngredient.get(i), 1, row);
                    row++;

                    iGrid.add(new Label("Quantity Required:"), 0, row);
                    txtQuant.add(new Spinner<Integer>());
                    txtQuant.get(i).setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 0));
                    iGrid.add(txtQuant.get(i), 1, row);
                    row++;

                    iGrid.add(new Label("Units:"), 0, row);
                    txtUnit.add(new TextField());
                    iGrid.add(txtUnit.get(i), 1, row);
                    row += 2;
                }

                //fill textfields with current ingredient information
                for (int i = 0; i < numIngredients; i++) {
                    txtIngredient.get(i).setText(ur.ingredientNames.get(i));
                    txtQuant.get(i).getValueFactory().setValue(ur.ingredientQuant.get(i));
                    txtUnit.get(i).setText(ur.ingredientUnits.get(i));
                    id.add(ur.ingredientID.get(i)); //get RI_ID for later update                    
                }

                vBox.getChildren().add(iGrid);

            } else {// reopen fresh stage
                addStage.close();
                txtIngredient.clear();
                modifyRecipeActions();
            }
        });

        btUpdate.setOnAction(e -> {
            //recipe information
            getRecipeName = txtName.getText();
            getRecipeDescription = txtDescription.getText();
            getRecipeType = txtType.getText();
            getRecipeLink = txtLink.getText();

            //gather number of ingredients shown
            numIngredients = ur.getIngredientNames().size();

            //check for empty ingredients
            int ingredientCheck=0;
            for (int i = 0; i < numIngredients; i++) {
                if (txtIngredient.get(i).getText().isEmpty()) {
                    ingredientCheck = 1;
                } else if (txtUnit.get(i).getText().isEmpty()) {
                    ingredientCheck = 1;
                }
            }

            if (ingredientCheck == 1) { //check if ingredient left empty
                alert.emptyEntryAlert();
            } //check for empty recipe names
            else if (getRecipeName.isEmpty() || getRecipeType.isEmpty()) {
                alert.emptyEntryAlert();
            } else {
                // Confirmation Page
                ingredientCheck = 0; 
                updateRecipeCheck(ur);
            }
        });

        btClose.setOnAction(e -> {
            txtIngredient.clear();
            addStage.close();
        });

    }

    /**
     * updateRecipeCheck - confirmation GUI page for updating a recipe, and its
     * ingredients
     *
     * @return
     */
    public BorderPane updateRecipeCheck(UpdateRecipe ur) {

        BorderPane pane = new BorderPane();
        Stage checker = new Stage();

        Scene scene = new Scene(pane, 420, 250);
        pane.setStyle(st.scrollBackground());
        pane.setPadding(new Insets(10));

        //------------------Titles 
        VBox titles = new VBox();
        titles.setAlignment(Pos.CENTER);
        titles.setStyle(st.borderColor());
        Text title = new Text("Update Recipe Confirmation Page");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Text subtitle = new Text("Please confirm that the following information"
                + "is correct.");
        titles.getChildren().addAll(title, subtitle);

        //-----------------Confirmation Buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        Button btConfirm = new Button("Confirm");
        Button btEdit = new Button("Edit");
        btConfirm.setStyle(st.buttonStyle());
        btEdit.setStyle(st.buttonStyle());
        buttons.getChildren().addAll(btConfirm, btEdit);

        //--------------------Entry Information
        VBox values = new VBox();
        values.setAlignment(Pos.CENTER);

        GridPane check = new GridPane();
        check.setAlignment(Pos.CENTER);
        check.setPadding(new Insets(10));
        check.setHgap(10);
        check.setVgap(10);

        check.add(new Label("Recipe Name:"), 0, 0);
        check.add(new Label(getRecipeName), 1, 0);

        check.add(new Label("Description:"), 0, 1);
        check.add(new Label(getRecipeDescription), 1, 1);
        check.add(new Label("Type:"), 0, 2);
        check.add(new Label(getRecipeType), 1, 2);
        check.add(new Label("URL Link:"), 0, 3);
        check.add(new Label(getRecipeLink), 1, 3);

        GridPane ingredients = new GridPane();
        ingredients.setAlignment(Pos.CENTER);

        int row = 1;
        for (int i = 0; i < numIngredients; i++) {
            ingredients.add(new Label((i + 1) + "- Ingredient Name:  "), 0, row);
            ingredients.add(new Label(txtIngredient.get(i).getText()), 1, row);
            row++;
            ingredients.add(new Label((i + 1) + "- Quantity:  "), 0, row);
            ingredients.add(new Label(String.valueOf(txtQuant.get(i).getValue())), 1, row);
            ingredients.add(new Label(txtUnit.get(i).getText()), 2, row);
            row++;
        }
        values.getChildren().addAll(check, ingredients);

        //-------------------------Add to BorderPane
        pane.setTop(titles);
        pane.setCenter(values);
        pane.setBottom(buttons);

        //------------------Stage
        checker.setTitle("Verify Recipe Data");
        checker.setScene(scene);
        checker.show();

        //--------------------Actions
        btConfirm.setOnAction(e -> {
            ur.updateRecipe(getRecipeName, getRecipeDescription, getRecipeType,
                    getRecipeLink, cbSelect.getValue().toString());

            for (int i = 0; i < numIngredients; i++) {
                ur.updateRI(getRecipeName, txtIngredient.get(i).getText(),
                        txtQuant.get(i).getValue(), txtUnit.get(i).getText(), ur.ingredientID.get(i));
            }
            ur.updateIngredients();

            ur.clearRecipeNames();
            ur.clearIngredientNames();
            id.clear();
            txtIngredient.clear();
            txtQuant.clear();
            txtUnit.clear();

            checker.close();
            addStage.close();
            modifyRecipeActions();
        });
        btEdit.setOnAction(e -> {
            checker.close();
        });

        return pane;
    }
} //End Subclass ActionsUpdateRecipe

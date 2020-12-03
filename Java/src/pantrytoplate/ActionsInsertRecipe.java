package pantrytoplate;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 9, 2020
 * @Subclass ActionsInsertRecipe Description: Class for GUI and actions to
 * insert a Recipe into the CookBook
 */
//Imports
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
//Begin Subclass ActionsInsertRecipe

public class ActionsInsertRecipe {

    Styles st = new Styles();
    Verify v = new Verify();
    Alerts a = new Alerts();

    String recipeName;
    String recipeDesc;
    String recipeType;
    String recipeLink;

    //Recipe Ingredient fields:
    ArrayList<TextField> txtIngredient = new ArrayList<>();
    ArrayList<Spinner<Integer>> txtQuant = new ArrayList<>();
    SpinnerValueFactory<Integer> vf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 0);
    ArrayList<TextField> txtUnit = new ArrayList<>();

    //Recipe Ingredient insert Arrays
    ArrayList<String> ingredient = new ArrayList<>();
    ArrayList<Integer> quant = new ArrayList<>();
    ArrayList<String> units = new ArrayList<>();

    /**
     * Default constructor
     */
    public ActionsInsertRecipe() {

    }

    /**
     * addRecipeActions - NEEDS WORK!!! Dynamically adds number of ingredients
     * to the GUI so user can add multiple ingredients at a time. Currently,
     * doesn't work because the item needs to already be in the inventory to add
     * to the recipe_ingredients table. This needs to be resolved on the SQL
     * end.
     */
    public void addRecipeActions() {
        // add a recipe - needs to dynamically add new text fields. 
        BorderPane bPane = new BorderPane();
        bPane.setBackground(new Background(st.bgColor()));

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);

        VBox titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        titleBox.setStyle(st.borderColor());
        titleBox.setSpacing(10);
        titleBox.setPadding(new Insets(10));

        VBox buttons = new VBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle(st.borderColor());
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10));

        ScrollPane scroll = new ScrollPane();
        scroll.setStyle(st.scrollBackground());

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //grid for ingredients
        GridPane iGrid = new GridPane();
        iGrid.setAlignment(Pos.CENTER);
        iGrid.setHgap(10);
        iGrid.setVgap(10);

        Text title = new Text("Add a new Recipe to the Cook Book");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 20));
        Text instructions = new Text("Enter recipe information below:");
        titleBox.getChildren().addAll(title, instructions);

        Label lblRecName = new Label("Recipe Name:");
        Label lblRecDescription = new Label("Description:");
        Label lblRecType = new Label("Recipe Type:");
        Label lblRecLink = new Label("Link to Recipe:");
        TextField txtRecName = new TextField();
        TextField txtRecDescription = new TextField();
        TextField txtRecType = new TextField();
        TextField txtRecLink = new TextField();
        Label lblStartIngredients = new Label("Ingredients:");
        lblStartIngredients.setFont(Font.font("TimesNewRoman", FontWeight.BOLD,
                FontPosture.ITALIC, 10));

        grid.add(lblRecName, 0, 0);
        grid.add(txtRecName, 1, 0);
        grid.add(lblRecDescription, 0, 1);
        grid.add(txtRecDescription, 1, 1);
        grid.add(lblRecType, 0, 2);
        grid.add(txtRecType, 1, 2);
        grid.add(lblRecLink, 0, 3);
        grid.add(txtRecLink, 1, 3);

        //entry for number of ingredients:
        Label lblNumIngredient = new Label("Number of Ingredients:");
        Spinner<Integer> spNumIngredient = new Spinner<Integer>();
        int initialValue = 1;
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, initialValue);
        spNumIngredient.setValueFactory(valueFactory);
        Button btNumIngredient = new Button("Enter Ingredient Number");
        btNumIngredient.setStyle(st.buttonStyle());

        grid.add(lblNumIngredient, 0, 4);
        grid.add(spNumIngredient, 1, 4);
        grid.add(btNumIngredient, 1, 5);

        Button btEnter = new Button("Enter Recipe Into CookBook");
        btEnter.setStyle(st.buttonStyle());
        Button btClose = new Button("Close");
        btClose.setStyle(st.buttonStyle());

        vBox.getChildren().add(grid);
        scroll.setContent(vBox);
        buttons.getChildren().addAll(btEnter, btClose);
        bPane.setTop(titleBox);
        bPane.setCenter(scroll);
        bPane.setBottom(buttons);
        //create a new scene and stage:
        final Scene addScene = new Scene(bPane, 350, 600);
        Stage addStage = new Stage();
        addStage.setTitle("Add Recipe to Cookbook");
        addStage.setScene(addScene);
        addStage.show();

//        //Actionables
        btNumIngredient.setOnAction((ActionEvent e) -> {
            vBox.getChildren().remove(iGrid);
            int row = 1;
            txtIngredient.clear();
            txtQuant.clear();
            txtUnit.clear();

            for (int i = 0; i < spNumIngredient.getValue(); i++) {
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

            vBox.getChildren().add(iGrid);

        });

        btEnter.setOnAction((ActionEvent e) -> {
            recipeName = txtRecName.getText();
            //make sure recipe doesn't already exist, if free, confirm entries.

            if (v.recipeExists(recipeName) == 0) {
                recipeDesc = txtRecDescription.getText();
                recipeType = txtRecType.getText();
                recipeLink = txtRecLink.getText();

                for (int i = 0; i < spNumIngredient.getValue(); i++) {
                    ingredient.add(txtIngredient.get(i).getText());
                    quant.add(txtQuant.get(i).getValue());
                    units.add(txtUnit.get(i).getText());
                }
                addRecipeCheck(txtIngredient.size());
            } else {
                a.recipeExists(); 
            }
        });
        btClose.setOnAction((ActionEvent e) -> {
            addStage.close();
        });

    }

    /**
     * addRecipeCheck - confirmation page that appears after the user has added
     * information about a recipe.
     *
     * @param num
     * @return
     */
    public BorderPane addRecipeCheck(int num) {
        Stage checker = new Stage();

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 325, 350);
        pane.setStyle(st.scrollBackground());
        pane.setPadding(new Insets(10));

        //----------------------Titles
        VBox titles = new VBox();
        titles.setAlignment(Pos.CENTER);
        Text title = new Text("Add New Recipe Confirmation Page");
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
        VBox entries = new VBox();
        entries.setAlignment(Pos.CENTER); 
        GridPane check = new GridPane();
        check.setAlignment(Pos.CENTER);
        check.setPadding(new Insets(10));
        check.setHgap(10);
        check.setVgap(10);

        check.add(new Label("Recipe Name:"), 0, 0);
        check.add(new Label(recipeName), 1, 0);

        check.add(new Label("Description:"), 0, 1);
        check.add(new Label(recipeDesc), 1, 1);

        check.add(new Label("Type of Recipe:"), 0, 2);
        check.add(new Label(recipeType), 1, 2);

        check.add(new Label("Website URL:"), 0, 3);
        check.add(new Label(recipeLink), 1, 3);

        // for ingredients:
        GridPane iCheck = new GridPane();
        iCheck.setAlignment(Pos.CENTER);
        iCheck.setPadding(new Insets(10));
        iCheck.setHgap(10);
        iCheck.setVgap(10);

        int row = 0;
        for (int i = 0; i < num; i++) {
            iCheck.add(new Label((i + 1) + "- Ingredient: "), 0, row);
            iCheck.add(new Label(ingredient.get(i)), 1, row);
            row++;

            iCheck.add(new Label((i + 1) + "- Quantity: "), 0, row);
            iCheck.add(new Label(String.valueOf(quant.get(i))), 1, row);
            iCheck.add(new Label(units.get(i)), 2, row);
            row++;
        }

        entries.getChildren().addAll(check, iCheck);
        //---------Add to BorderPane:
        pane.setTop(titles);
        pane.setCenter(entries);
        pane.setBottom(buttons);

        //------------stage
        checker.setTitle("Verify Data");
        checker.setScene(scene);
        checker.show();

        //---------------ActionsInsertInventory
        btConfirm.setOnAction(e -> {

            //*************if recipe exists CHECK!! 
            InsertRecipe ir = new InsertRecipe(recipeName, recipeDesc, recipeType, recipeLink);

            //---------------add ingredients:
            int m = 0;
            while (m < num) {
                String ingredientName = txtIngredient.get(m).getText();
                int ingredientQuant = txtQuant.get(m).getValue();
                String ingredientUnits = txtUnit.get(m).getText();

                ir.addIngredients(ingredientName, ingredientQuant, ingredientUnits);
                ir.updateIngredients();
                m++;
            }

            //clear everything:
            recipeName = "";
            recipeDesc = "";
            recipeType = "";
            recipeLink = "";
            txtIngredient.clear();
            txtQuant.clear();
            txtUnit.clear();
            ingredient.clear();
            quant.clear();
            units.clear();
            checker.close();
        });
        btEdit.setOnAction(e -> {
            checker.close();
        });

        return pane;
    }

    public void ingredientInfo(String name, int quant, String units) {

    }
} //End Subclass ActionsInsertRecipe

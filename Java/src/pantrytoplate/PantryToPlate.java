package pantrytoplate;

/**
 * @Course: SDEV435 - Applied Software Practice
 * @Author Name: Kelly May
 * @Assignment Name: pantrytoplate
 * @Date: Jun 1, 2020
 * @Description: This is the GUI for Pantry_To_Plate application. Contains the
 * various panes of the GUI, as well as basic ActionEvents for menu buttons.
 * Last Edit - June 13, 2020 To Do - join GUI with SQL database, allow user to
 * update database through GUI controls.
 */
//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;

//Begin Class PantryToPlate
public class PantryToPlate extends Application {

    //button variables:
    Button btPantry = new Button("Pantry");
    Button btAvailRes = new Button("Available Recipes");
    Button btCookBook = new Button("Cook Book");
    Button btEdPantry = new Button("Edit Pantry");
    Button btHelp = new Button("Help");
    Button btExit = new Button("Exit");
    Button btAddItem = new Button("Add New Item");
    Button btDelItem = new Button("Delete Item");
    Button btModItem = new Button("Modify Item");
    Button btAddRec = new Button("Add New Recipe");
    Button btDelRec = new Button("Delete Recipe");
    Button btModRec = new Button("Modify Recipe");

    //style object:
    Styles st = new Styles();

    //databse objects:
    //DBConnection db = new DBConnection();
    ViewRecipeIngredients vi = new ViewRecipeIngredients();

    ActionsInsertInventory actions = new ActionsInsertInventory();
    ActionsDeleteInventory dia = new ActionsDeleteInventory(); 
    ActionsUpdateInventory uia = new ActionsUpdateInventory(); 
    ActionsInsertRecipe ira = new ActionsInsertRecipe(); 
    ActionsDeleteRecipe ida = new ActionsDeleteRecipe(); 
    ActionsUpdateRecipe iua = new ActionsUpdateRecipe(); 
    

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane bPane = new BorderPane();
        bPane.setTop(TitlePane());
        bPane.setLeft(MenuPane());
        bPane.setCenter(HomePane());

        //menu button actions
        btPantry.setOnAction(e -> {
            bPane.setCenter(YourPantryPane());
        });
        btAvailRes.setOnAction(e -> {
            bPane.setCenter(RecipePane());
        });
        btCookBook.setOnAction(e -> {
            bPane.setCenter(CookPane());
        });
        btEdPantry.setOnAction(e -> {
            bPane.setCenter(EditPane());
        });
        btHelp.setOnAction(e -> {
            bPane.setCenter(HelpPane());
        });
        btExit.setOnAction(e -> {
            Platform.exit();
        });

        Scene scene = new Scene(bPane, 750, 550); // scene set to border pane, and size
        scene.setFill(Color.BISQUE);
        primaryStage.setTitle("Pantry to Plate - Home"); //name stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * TitlePane - GUI - houses the Title of the application
     *
     * @return
     */
    public VBox TitlePane() {
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setSpacing(20);
        Text title = new Text(50, 50, "Pantry to Plate");
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD,
                FontPosture.ITALIC, 50));
        title.setFill(Color.CRIMSON);
        title.setStyle("-fx-border-color: black");
        pane.setStyle(st.borderColor());
        pane.setBackground(new Background(st.borderBGColor()));
        pane.getChildren().add(title);

        return pane;
    }

    /**
     * MenuPane - GUI - houses the menu buttons
     *
     * @return
     */
    public VBox MenuPane() {
        VBox pane = new VBox();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setSpacing(20);
        pane.setStyle(st.borderColor());
        pane.setBackground(new Background(st.borderBGColor()));
        Text menu = new Text("Menu:");
        menu.setFont(Font.font("Times New Roman", FontWeight.BOLD, 30));
        menu.setFill(Color.CRIMSON);

        //Make buttons look stylish:
        Arrays.asList(btPantry, btAvailRes, btCookBook, btEdPantry,
                btHelp, btExit).forEach((b) -> {
                    b.setStyle(st.buttonStyle());
                    b.setPrefWidth(140);
                });

        pane.getChildren().addAll(menu, btPantry, btAvailRes, btCookBook,
                btEdPantry, btHelp, btExit);

        return pane;
    }

    /**
     * HomePane - GUI - First page that appears in GUI.
     *
     * @return
     */
    public VBox HomePane() {
        VBox pane = new VBox();
        pane.setStyle(st.borderColor());
        pane.setBackground(new Background(st.bgColor()));
        pane.setPadding(new Insets(2, 2, 2, 2));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setSpacing(20);

        // title and info:
        Text homeTitle = new Text("Home Page");
        homeTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Text subTitle = new Text("Welcome to Pantry to Plate!");
        subTitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        Text instruct = new Text("To get started: ");
        instruct.setFill(Color.CRIMSON);
        instruct.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        Text addPant = new Text("Add Items to your Pantry: ");
        addPant.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        Text addCook = new Text("Add Recipes to your CookBook: ");
        addCook.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));

        HBox hPant = new HBox();
        hPant.setAlignment(Pos.CENTER);
        hPant.getChildren().addAll(addPant, btAddItem);

        HBox hCook = new HBox();
        hCook.setAlignment(Pos.CENTER);
        hCook.getChildren().addAll(addCook, btAddRec);

        // make buttons look fancy
        Arrays.asList(btAddItem, btAddRec).forEach((b) -> {
            b.setStyle(st.buttonStyle());
        });

        btAddItem.setOnAction(e -> {
            actions.addItemActions();
        });

        btAddRec.setOnAction(e -> {
            ira.addRecipeActions();
        });

        // home picture
        Image homeImage = new Image("/quiche.jpg");
        ImageView homeView = new ImageView(homeImage);
        homeView.setFitHeight(250);
        homeView.setFitWidth(250);
        pane.getChildren().addAll(homeTitle, subTitle, instruct, hPant, hCook, homeView);
        return pane;
    }

    /**
     * YourPantryPane - GUI - pane that displays your pantry inventory
     *
     * @return
     */
    public VBox YourPantryPane() {
        ViewPantry vp = new ViewPantry();
        VBox vPane = new VBox();
        vPane.setStyle(st.borderColor());
        vPane.setBackground(new Background(st.bgColor()));
        vPane.setPadding(new Insets(2, 2, 2, 2));
        vPane.setAlignment(Pos.TOP_CENTER);
        Text subtitle = new Text("Your Pantry:");
        subtitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        vPane.getChildren().add(subtitle);

        // ***** need to add DB table of pantry/ingredients.
        vPane.getChildren().add(vp.getInventory());
        vp.clearInventory();
        vp.setInventory();

        return vPane;
    }

    /**
     * HelpPane - GUI - pane that shows information about PTP, and a FAQ
     *
     * @return
     * @throws java.io.FileNotFoundException
     */
    public VBox HelpPane() {
        VBox pane = new VBox();
        Text subtitle = new Text("Help/FAQ:");
        subtitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        pane.getChildren().add(subtitle);
        pane.setBackground(new Background(st.bgColor()));
        pane.setSpacing(20);
        pane.setAlignment(Pos.TOP_CENTER);

        /**
         * ** MAKE THESE INTO FLAT TEXT FILES!!***********
         */
        // About Pantry to Plate Text Area:
        String line = "";
        TextArea about = new TextArea();
        about.setEditable(false); 
        
        try{
        Scanner aboutScan = new Scanner(new File("C:/Users/Kelly/Documents/School/SDEV435-Applied_Software_Prac/PantryToPlate_Project/Java/about.txt"));
        //InputStream aboutScan = this.getClass().getClassLoader().getResourceAsStrem("C:/Users/Kelly/Documents/School/SDEV435-Applied_Software_Prac/PantryToPlate_Project/Java/about.txt");
        while (aboutScan.hasNextLine()) {
            line = aboutScan.nextLine() + "\n";
            about.appendText(line);
        }
        } catch(FileNotFoundException ex){
            System.out.println("about.txt not found");
        }
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(about);
        scroll.setPrefSize(120, 120); 
        pane.getChildren().add(about);

        // Frequently Asked Questions Text Area
        String faqLine = "";
        TextArea faq = new TextArea();
        faq.setEditable(false); 
        try {

            Scanner faqScan = new Scanner(new File("C:/Users/Kelly/Documents/School/SDEV435-Applied_Software_Prac/PantryToPlate_Project/Java/qa.txt"));
            while (faqScan.hasNextLine()) {
                faqLine = faqScan.nextLine() + "\n";
                faq.appendText(faqLine);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("qa.txt not found.");
        }

        ScrollPane scroll2 = new ScrollPane();
        scroll2.setContent(faq);
        scroll2.setPrefSize(120, 120);
        pane.getChildren().add(faq);

        return pane;

    }

    /**
     * RecipePane - GUI - displays recipes that are available to make based on
     * inventory quantities
     *
     * @return
     */
    public VBox RecipePane() {
        ViewAvailableRecipes va = new ViewAvailableRecipes();

        VBox pane = new VBox();
        pane.setStyle(st.borderColor());
        pane.setBackground(new Background(st.bgColor()));
        pane.setSpacing(10);
        pane.setAlignment(Pos.TOP_CENTER);

        Text subtitle = new Text("Available Recipes:");
        Text explain = new Text("These recipes contain ingredients you already"
                + " have in your pantry!");
        explain.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 13));
        subtitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        pane.getChildren().addAll(subtitle, explain);

        // ***** Need to make way to add recipes from DB here.
        pane.getChildren().add(va.getRecipes());
        HBox pickerBox = new HBox();
        pickerBox.setAlignment(Pos.CENTER);
        pickerBox.setSpacing(10);
        Text instruct = new Text("Select a recipe, then click Get Recipe:");
        ComboBox cbChoice = new ComboBox();
        cbChoice.getItems().addAll(va.number);
        cbChoice.getSelectionModel().select(va.number.get(0));
        cbChoice.setVisibleRowCount(5);//makes combobox scrollable. 
        Button btGetRecipe = new Button("Get Recipe");
        btGetRecipe.setStyle(st.buttonStyle());
        pickerBox.getChildren().addAll(instruct, cbChoice, btGetRecipe);

        pane.getChildren().add(pickerBox);

        btGetRecipe.setOnAction(e -> {
            int selection = (int) cbChoice.getValue();

            va.showARecipe(selection); //method will pop out the link and description of a recipe. 
        });

        return pane;
    }

    /**
     * CookPane - GUI - displays entire cookbook/recipes regardless of inventory
     *
     * @return
     */
    public VBox CookPane() {
        ViewRecipes vr = new ViewRecipes();

        VBox pane = new VBox();
        pane.setStyle(st.borderColor());
        pane.setBackground(new Background(st.bgColor()));
        pane.setSpacing(10);
        pane.setAlignment(Pos.TOP_CENTER);

        Text subtitle = new Text("All Recipes:");
        Text warning = new Text("Warning!");
        warning.setFill(Color.CRIMSON);
        Text explain = new Text("You may not have the "
                + "ingredients to make some of these recipes!");
        explain.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, 13));
        subtitle.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));

        //add title information to the pane
        pane.getChildren().addAll(subtitle, warning, explain);
        //add grid of recipe information
        pane.getChildren().add(vr.getRecipes());

        HBox pickerBox = new HBox();
        pickerBox.setAlignment(Pos.CENTER);
        pickerBox.setSpacing(10);
        Text instruct = new Text("Select a recipe, then click Get Recipe:");
        ComboBox cbChoice = new ComboBox();
        cbChoice.getItems().addAll(vr.number);
        cbChoice.getSelectionModel().select(vr.number.get(0));
        cbChoice.setVisibleRowCount(5);//makes combobox scrollable. 
        Button btGetRecipe = new Button("Get Recipe");
        btGetRecipe.setStyle(st.buttonStyle());
        pickerBox.getChildren().addAll(instruct, cbChoice, btGetRecipe);

        pane.getChildren().add(pickerBox);

        btGetRecipe.setOnAction(e -> {
            int selection = (int) cbChoice.getValue();

            vr.showARecipe(selection); //method will pop out the link and description of a recipe. 
        });

        return pane;
    }

    /**
     * EditPane - GUI - features buttons for editing the inventory and recipes/
     * recipe_ingredient tables of the database
     *
     * @return
     */
    public VBox EditPane() {
        VBox pane = new VBox();
        pane.setStyle(st.borderColor());
        pane.setBackground(new Background(st.bgColor()));
        pane.setSpacing(50);
        pane.setPadding(new Insets(10));
        pane.setAlignment(Pos.TOP_CENTER);

        //Titles:
        Font titleFont = Font.font("Times New Roman", FontWeight.BOLD, 20);
        Text txtPantry = new Text("Pantry Editor:");
        txtPantry.setFont(titleFont);
        Text txtCook = new Text("CookBook Editor:");
        txtCook.setFont(titleFont);

        // HBoxes with pantry edit and cookbook edit buttons:
        HBox pantryButtons = new HBox();
        pantryButtons.setAlignment(Pos.CENTER);
        pantryButtons.setSpacing(10);
        pantryButtons.getChildren().addAll(btAddItem, btDelItem, btModItem);

        HBox cookButtons = new HBox();
        cookButtons.setAlignment(Pos.CENTER);
        cookButtons.setSpacing(10);
        cookButtons.getChildren().addAll(btAddRec, btDelRec, btModRec);

        // make buttons look fancy
        Arrays.asList(btAddItem, btDelItem, btModItem, btAddRec, btDelRec,
                btModRec).forEach((b) -> {
                    b.setStyle(st.buttonStyle());
                });
        // add titles and button rows to original vbox:
        pane.getChildren().addAll(txtPantry, pantryButtons, txtCook, cookButtons);

        btAddItem.setOnAction(e -> {
            actions.addItemActions();
        });
        btDelItem.setOnAction(e -> {
            dia.deleteItemActions();
        });
        btModItem.setOnAction(e -> {
            uia.modifyItemActions();
        });
        btAddRec.setOnAction(e -> {
            ira.addRecipeActions();
        });
        btDelRec.setOnAction(e -> {
            ida.deleteRecipeActions();
        });
        btModRec.setOnAction(e -> {
            iua.modifyRecipeActions();
        });
//
        return pane;
    }

} //End Class PantryToPlate


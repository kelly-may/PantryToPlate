package pantrytoplate;

import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Aug 1, 2020
 * @Subclass Styles Description: Class for centralizing the main style
 * components of Pantry to Plate, to be accessed by multiple classes holding GUI
 * panes.
 */
//Imports
//Begin Subclass Styles
public class Styles {

    /**
     * Default Constructor
     */
    public Styles() {

    }

    /**
     * method for returning a css string for a style of button to be used for
     * entire PtP project
     *
     * @return
     */
    public String buttonStyle() {
        String style = " -fx-padding: 8 15 15 15;\n"
                + "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n"
                + "    -fx-background-radius: 8;\n"
                + "    -fx-background-color: \n"
                + "        linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n"
                + "        #9d4024,\n"
                + "        #d86e3a,\n"
                + "        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n"
                + "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n"
                + "    -fx-font-weight: bold;";
        return style;
    }

    /**
     * method for creating the "PapayaWhip" color in scroll backgrounds
     *
     * @return
     */
    public String scrollBackground() {
        String style = "-fx-background: #FFEFD5;";
        return style;
    }

    /**
     * method for the pane border color
     *
     * @return
     */
    public String borderColor() {
        String color = "-fx-border-color: crimson";
        return color;
    }

    /**
     * method for the title pane and menu pane colors
     *
     * @return
     */
    public BackgroundFill borderBGColor() {
        BackgroundFill bg = new BackgroundFill(Color.BISQUE, null, null);
        return bg;
    }

    /**
     * method for the main background pane color - used in update methods as
     * well.
     *
     * @return
     */
    public BackgroundFill bgColor() {
        BackgroundFill bg = new BackgroundFill(Color.PAPAYAWHIP, null, null);
        return bg;
    }

} //End Subclass Styles

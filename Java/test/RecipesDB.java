

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Kelly
 * @Assignment Name: pantrytoplate
 * @Date: Jun 18, 2020
 * @Subclass RecipesDB Description:
 */
//Imports
//Begin Subclass RecipesDB
public class RecipesDB {

    private int recipe_id;
    private String recipe_name;
    private String recipe_description;
    private String recipe_type;
    private String recipe_link;
//default constructor

    public RecipesDB() {
    }

//setters
    public void setID(int id) {
        this.recipe_id = id;
    }

    public void setName(String name) {
        this.recipe_name = name;
    }

    public void setDescription(String desc) {
        this.recipe_description = desc;
    }

    public void setType(String type) {
        this.recipe_type = type;
    }

    public void setLink(String link) {
        this.recipe_link = link;
    }

//getters:
    public int getID() {
        return recipe_id;
    }

    public String getName() {
        return recipe_name;
    }

    public String getDescription() {
        return recipe_description;
    }

    public String getType() {
        return recipe_type;
    }

    public String getLink() {
        return recipe_link;
    }
} //End Subclass RecipesDB

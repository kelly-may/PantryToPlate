
/**
*  @Course: SDEV 250 ~ Java Programming I
*  @Author Name: Kelly
*  @Assignment Name: pantrytoplate
*  @Date: Jun 18, 2020
*  @Subclass RecipeIngredientDB Description:
*/
//Imports
//Begin Subclass RecipeIngredientDB
public class RecipeIngredientDB {
    private int ri_id; 
    private int recipe_id; 
    private String recipe_name; 
    private int ingredient_id; 
    private String ingredient_name; 
    private double quantity_required; 
    private String units_required; 

    //default constructor
    public RecipeIngredientDB(){
    }
    
    //setters
    public void setID(int id){
        this.ri_id = id; 
    }
    public void setRecipeID (int rid){
        this.recipe_id = rid; 
    }
    public void setRecipeName(String rname){
        this.recipe_name = rname; 
    }
    public void setIngredientID (int iid){
        this.ingredient_id = iid; 
    }
    public void setIngredientName(String iname){
        this.ingredient_name = iname; 
    }
    public void setQuantity(double quant){
        this.quantity_required = quant;
    }
    public void setUnits(String units){
        this.units_required = units; 
    }
    
    //getters
    public int getID(){
        return ri_id; 
    }
    public int getRecipeID(){
        return recipe_id; 
    }
    public String getRecipeName(){
        return recipe_name; 
    }
    public int getIngredientID(){
        return ingredient_id; 
    }
    public String getIngredientName(){
        return ingredient_name; 
    }
    public double getQuantity(){
        return quantity_required; 
    }
    public String getUnits(){
        return units_required; 
    }
} //End Subclass RecipeIngredientDB




import java.util.Date;

/**
 * @Course: SDEV 250 ~ Java Programming I
 * @Author Name: Kelly
 * @Assignment Name:
 * @Date: Jun 18, 2020
 * @Subclass InventoryDB Description:
 */
//Imports
//Begin Subclass InventoryDB
public class InventoryDB {

    private int ingredient_id;
    private String ingredient_name;
    private double quantity;
    private String quantity_units;
    private String storage_location;
    private Date expiration_date;
    private String ingredient_type;

    //default constructor
    public InventoryDB() {
    }

    //setters
    public void setID(int id) {
        this.ingredient_id = id;
    }

    public void setName(String name) {
        this.ingredient_name = name;
    }
    
    public void setQuantity(double quant){
        this.quantity = quant; 
    }
    public void setQuantityUnits(String units){
        this.quantity_units = units; 
    }
    public void setStorage (String location){
        this.storage_location = location; 
    }
    public void setExpDate (Date exp){
        this.expiration_date = exp; 
    }
    public void setType (String type){
        this.ingredient_type = type; 
    }
    
    // getters:
    public int getID(){
        return ingredient_id; 
    }
    public String getName(){
        return ingredient_name; 
    }
    public double getQuantity(){
        return quantity; 
    }
    public String getUnits(){
        return quantity_units; 
    }
    public String getStorage(){
        return storage_location; 
    }
    public Date getExpDate(){
        return expiration_date; 
    }
    public String getType(){
        return ingredient_type; 
    }
} //End Subclass InventoryDB

package classes;

import exceptions.PanOverflowException;
import loggers.Logger;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Pan implements MyInfo{   
//   panSize and panType have multiple choices, each choice is represented with an integer starting from 1
//   panSize(small: 1, medium: 2, large: 3) , panType(pan 1, deepDish 2)

    private ArrayList<Ingredient> toppings = new ArrayList(); // NOT USED // name it toppings?
     
    private double capacity;
    private double weight;
    private double calories;
    private int panSize;
    private int panType;
    private String type;
    private String size;
    private Logger logger;
    private final int slices = 8;
    
    private Mixer dough;

    public Pan(int panSize, int panType, Logger logger) {
        this.panSize = panSize;
        this.panType = panType;
        this.logger = logger;
        this.capacity = panSize * 150;//* 250;
        dough = new Mixer(this.panSize, this.panType, logger); // prepare the dough
        pourDough();
    }

    public double getCapacity() {
        return capacity;
    }
    
    public Mixer getDough() {
        return dough;
    }
    
    public double getWeight() {
        return weight;
    }

    public double getCalories() {
        return calories;
    }

    private void pourDough(){
        logger.log("pouring dough into pan\n");//ch 1
        weight += dough.getWeight();
        calories += dough.getCalories();
    }
    
    public void bake() throws InterruptedException{
        Thread.sleep(10); // millisec
    }

    public void addTopping(Ingredient ing) throws PanOverflowException{
        logger.log("adding " + ing.getName() + "\n");
            if(ing.getWeight() <=  capacity){
                toppings.add(ing);
                weight += ing.getWeight();
                calories += ing.getCalories();
            }
            else{
                throw new PanOverflowException(ing.getName());
            }
    }
    
    @Override
    public String getInfo() {
        translate();
        return "Pan Type: " + type + "\n" + "Pan Size = " + size + "\nToppings:\n" +  toppingsInfo() + "\n" + slicesInfo(); // + slices.getInfo 
    }

    
    public String slicesInfo() {
        return "Slice Info {" + " weight = " + Math.ceil(weight/slices)  + ", calories = " + Math.ceil(calories/slices) + " calorie}\n";
    }

    
    
    private void translate(){  // translates int values given to panSize and panType to String values
        switch (panSize) {
            case 1:
                size = "Small";
                break;
            case 2:
                size = "Medium";
                break;
            case 3:
                size = "Large";
                break;
        }
        
        switch (panType) {
            case 1:
                type = "Pan";
                break;
            case 2:
                type = "Deep Dish";
                break;
        }
    }
    
    private String toppingsInfo(){
        String str = "";
        for(Ingredient i : toppings){
            str += i.getInfo();  
        }
        return str;
    }
    
}

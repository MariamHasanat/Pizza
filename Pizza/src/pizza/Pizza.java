package pizza;

import java.util.ArrayList;

public class Pizza implements MyInfo{
    int panSize;
    int panType;
    Pan pan;
    Logger logger;
    private static int count = 0;
    
    // dough weight + topping weight in one double variable
    // dough calories + toppings calories in one double variable

    public Pizza(int panSize, int panType, Logger logger) {
        this.panSize = panSize;
        this.panType = panType;
        this.logger = logger;
        count++;
        logger.log("Pizza #" + count + " was ordered\nDetails:");
        pan = new Pan(panSize, panType, logger);
    }

    @Override
    public String getInfo() {
        return pan.getInfo();
    }

    public Pan getPan() {
        return pan;
    }

//    public static int getCount() {
//        return count;
//    }
    
    
}

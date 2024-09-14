/**
 * Represents a Product that can be stored in a slot of the Gym Meal Machine.
 */
public class Product {
    private String productName; // Name of the product
    private int calorie, price; // Calorie and price of the product
    private double protein, carb, fat; // Nutritional values of the product

    /**
     * Constructs a new Product with the given product name.
     * @param productName The name of the product.
     */
    Product(String productName){
        this.productName = productName;
    }


    // Calculates and sets the calorie value based on protein, carb, and fat values
    public void findAndSetCalorie(){
        double calorieBeforeRounding = (4*this.protein) + (4*this.carb) + (9*this.fat);
        String[] calorieStringArray = Double.toString(calorieBeforeRounding).split("\\.");

        int decimal = Integer.parseInt(calorieStringArray[1].substring(0, 1));
        int roundedCalorie;

        if (decimal >= 5){
            roundedCalorie = (int) calorieBeforeRounding + 1;
        }
        else{
            roundedCalorie = (int) calorieBeforeRounding;
        }
        this.calorie = roundedCalorie;
    }


    // Getters and setters for product properties

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        // Ensures price is positive
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }
}
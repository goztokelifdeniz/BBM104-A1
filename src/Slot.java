/**
 * Represents a slot in the Gym Meal Machine.
 */
public class Slot {
    Product product; // The product currently occupying the slot
    String slotName; // The name or identifier of the slot
    private String productName = "___"; // The name of the product in the slot (default: "___" for empty slot)
    private int productAmount, price, calorie; // Product amount, price, and calorie content
    private double protein, carb, fat; // Nutritional values of the product

    /**
     * Constructs a slot with the specified name.
     * @param slotName The name or identifier of the slot.
     */
    Slot(String slotName){
        this.slotName = slotName;
    }


    /**
     * Fills the slot with the specified product, updating slot information.
     * @param product The product to place in the slot.
     */
    public void fillTheSlotWithProduct(Product product){
        this.product = product;
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.protein = product.getProtein();
        this.carb = product.getCarb();
        this.fat = product.getFat();
        this.calorie = product.getCalorie();
    }


    /**
     * Resets the slot to its initial empty state.
     */
    public void resetSlot(){
        this.product = null;
        this.productName = "___";
        this.price = 0;
        this.protein = 0.0d;
        this.carb = 0.0d;
        this.fat = 0.0d;
        this.calorie = 0;
    }


    // Getters and setters for slot attributes

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getProtein() {
        return protein;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getCarb() {
        return carb;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFat() {
        return fat;
    }
}
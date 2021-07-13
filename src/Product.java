//Base class for all products the store will sell
public class Product {
    //Instance Variables
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartQuantity;

    //Constructor
    public Product(double initPrice, int initQuantity){
        price = initPrice;
        stockQuantity = initQuantity;
    }

    //Getter Methods
    public int getStockQuantity(){ return this.stockQuantity; }
    public int getSoldQuantity(){ return this.soldQuantity; }
    public int getCartQuantity() {return this.cartQuantity;}
    public double getPrice(){ return this.price; }

    //Setter Methods
    public void setStockQuantity(int i){ this.stockQuantity = i; }
    public void setCartQuantity(int i) {this.cartQuantity = i;}

    //Method that sells a specified amount of a product type.
    public double sellUnits(int amount){
        if(amount > 0 && this.cartQuantity >= amount && this.soldQuantity <= 10){
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }

    //To-string method.
    public String toString() {
        if (this.getCartQuantity() > 0){
            return this.getCartQuantity() + " x ";}
        else{
            return "";}
    }

}
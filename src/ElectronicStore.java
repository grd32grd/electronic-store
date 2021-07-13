//Imports
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//Model Class
public class ElectronicStore  {

  //Instance Variables
  public final int MAX_PRODUCTS = 10;
  private int curProducts;
  private String name;
  private ArrayList<Product> originalStock;
  private ArrayList<Product> stock;
  private double revenue;
  private int sales;
  private ArrayList<Product> cart;
  private double cartCost;

  //Constructor
  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    originalStock = new ArrayList<Product>();
    stock = new ArrayList<Product>();
    cart = new ArrayList<Product>();
    curProducts = 0;
    sales = 0;
  }

  //Getter Methods
  public double getRevenue(){
    return this.revenue;
  }
  public String getName(){
    return this.name;
  }
  public int getSales() {return this.sales;}
  public double getPricePerSale() {return (revenue/sales);}
  public ArrayList<Product> getStock() {return this.stock;}
  public ArrayList<Product> getCart() {return cart;}
  public double getCartCost() {return this.cartCost;}

  //Setter Methods
  public void setCartCost(int i) {this.cartCost = i;}

  //Other Methods

  //Method that initializes the original stock.
  public boolean setOriginalStock(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
      originalStock.add(curProducts, newProduct);
      curProducts++;
      return true; }
    return false; }

  //Method that initializes the actual stock.
  public void setStock() {
    for (int i = 0; i < originalStock.size(); i++)
      stock.add(originalStock.get(i));
  }

  //Method that confirms a sale.
  public void confirmSale()  {this.sales+=1;}

  //Method that enacts the processing of adding/removing items from the cart.
  public void transferringItems(Product p, boolean b) {

    //moving items from stock to cart
    if (b){
      if (!this.cart.contains(p))
        this.cart.add(p);
      if (p.getStockQuantity()==1)
        this.stock.remove(p);
      p.setStockQuantity(p.getStockQuantity()-1);
      p.setCartQuantity(p.getCartQuantity()+1);
      cartCost+=p.getPrice();}

    //moving items from cart back to stock
    else{
      if (!this.stock.contains(p))
        this.stock.add(p);
      if (p.getCartQuantity()==1)
        this.cart.remove(p);
      p.setStockQuantity(p.getStockQuantity()+1);
      p.setCartQuantity(p.getCartQuantity()-1);
      cartCost-=p.getPrice();}
  }

  //Method that determines & returns the most popular store items.
  public ArrayList<Product> mostPopularStock() {
    ArrayList<Product> most = new ArrayList<Product>();

    for (int i=0; i<this.originalStock.size(); i++)
      most.add(originalStock.get(i));

    Collections.sort(most, new Comparator<Product>() {
      public int compare(Product p1, Product p2) {
        return Integer.compare(p2.getSoldQuantity(), p1.getSoldQuantity());
      }
    });

    ArrayList<Product> mostPopular = new ArrayList<>();
    for (int i=0; i<3; i++)
      if (most.get(i) != null)
        mostPopular.add(most.get(i));
    return mostPopular;
  }

  //Method that sells the cart items and updates the store revenue.
  public void sellCartItems(Product p, int amount){
    this.revenue += p.sellUnits(amount);
  }

  //Static method that creates the store.
  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.setOriginalStock(d1);
    store1.setOriginalStock(d2);
    store1.setOriginalStock(l1);
    store1.setOriginalStock(l2);
    store1.setOriginalStock(f1);
    store1.setOriginalStock(f2);
    store1.setOriginalStock(t1);
    store1.setOriginalStock(t2);
    return store1;
  }
}
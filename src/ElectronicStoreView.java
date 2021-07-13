//Imports
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

//View Class
public class ElectronicStoreView extends Pane implements StoreView {
    private ListView<Product> popularItems, storeStock, currentCart;
    private TextField numberOfSales, revenueField, moneyOfSales;
    private Label label7;
    private ElectronicStoreButtonPane buttonPane;

    //An update method that updates the view whenever called by the controller.
    public void update(ElectronicStore store) {
        //Buttons
        buttonPane.getCartAdd().setDisable(true);
        buttonPane.getCartRemove().setDisable(true);

        //TextFields
        revenueField.setText(String.valueOf(store.getRevenue()));
        numberOfSales.setText(String.valueOf(store.getSales()));
        if (store.getPricePerSale() != store.getPricePerSale())
            moneyOfSales.setText("N/A");
        else
            moneyOfSales.setText(String.valueOf(store.getPricePerSale()));

        //ListViews
        storeStock.setItems(FXCollections.observableArrayList(store.getStock()));
        currentCart.setItems(FXCollections.observableArrayList(store.getCart()));
        popularItems.setItems(FXCollections.observableArrayList(store.mostPopularStock()));

        //Labels
        label7.setText(String.format("Current Cart ($%.2f):", store.getCartCost()));
    }

    //Getter Methods
    public ListView<Product> getPopularItems(){ return popularItems;}
    public ListView<Product> getStoreStock(){ return storeStock;}
    public ListView<Product> getCurrentCart(){ return currentCart;}
    public ElectronicStoreButtonPane getButtonPane() {return buttonPane;}

    public ElectronicStoreView(){
        popularItems = new ListView<Product>();
        popularItems.relocate(10,175);
        popularItems.setPrefSize(200,135);

        storeStock = new ListView<Product>();
        storeStock.relocate(220,40);
        storeStock.setPrefSize(280,270);

        currentCart = new ListView<Product>();
        currentCart.relocate(510,40);
        currentCart.setPrefSize(280,270);

        Label label1 = new Label("Store Summary:");
        label1.relocate(65,20);
        Label label2 = new Label("# Sales:");
        label2.relocate(46,50);
        Label label3 = new Label("Revenue:");
        label3.relocate(40,80);
        Label label4 = new Label("$ / Sales:");
        label4.relocate(41,110);
        Label label5 = new Label("Most Popular Items:");
        label5.relocate(55,150);
        Label label6 = new Label("Store Stock:");
        label6.relocate(330,20);
        label7 = new Label(String.format("Current Cart ($%.2f):", 0.0));
        label7.relocate(590,20);

        numberOfSales = new TextField();
        numberOfSales.relocate(95,45);
        numberOfSales.setPrefSize(115,10);

        revenueField = new TextField();
        revenueField.relocate(95,75);
        revenueField.setPrefSize(115,10);

        moneyOfSales = new TextField();
        moneyOfSales.relocate(95,105);
        moneyOfSales.setPrefSize(115,10);

        buttonPane = new ElectronicStoreButtonPane();
        buttonPane.relocate(20, 320);

        getChildren().addAll(popularItems, storeStock, currentCart, label1, label2, label3, label4, label5, label6, label7,
                numberOfSales, revenueField, moneyOfSales, buttonPane);

        setPrefSize(800, 400);
    }
}
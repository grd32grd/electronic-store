//Imports
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

//Controller Class
public class ElectronicStoreApp extends Application{
    //Instance Variable
    private static ElectronicStore store;

    //Constructor
    public ElectronicStoreApp() {
        store = ElectronicStore.createStore();
    }

    public void start(Stage primaryStage) {

        //Pane initalization.
        Pane aPane = new Pane();
        ElectronicStoreView  view = new ElectronicStoreView();
        aPane.getChildren().add(view);

        //Stage initalization.
        primaryStage.setTitle("Electronic Store Application - " + store.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        store.setStock();
        view.update(store);

        //Events that enable/disable buttons when an item in the listviews are clicked on.
        view.getStoreStock().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getButtonPane().getCartAdd().setDisable(false);
                view.getButtonPane().getCartRemove().setDisable(true);
            }
        });
        view.getCurrentCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getButtonPane().getCartRemove().setDisable(false);
                view.getButtonPane().getCartAdd().setDisable(true);
            }
        });
        view.getPopularItems().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                view.getButtonPane().getCartAdd().setDisable(true);
                view.getButtonPane().getCartRemove().setDisable(true);
            }
        });

        //Event that enacts the adding to cart process.
        view.getButtonPane().getCartAdd().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                store.transferringItems(view.getStoreStock().getSelectionModel().getSelectedItem(), true);
                if (store.getCart().size() > 0)
                    view.getButtonPane().getCompleteSale().setDisable(false);
                view.update(store);
            }
        });
        //Event that enacts the removing from cart process.
        view.getButtonPane().getCartRemove().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                store.transferringItems(view.getCurrentCart().getSelectionModel().getSelectedItem(), false);
                if (store.getCart().size() <= 0)
                    view.getButtonPane().getCompleteSale().setDisable(true);
                view.update(store);
            }
        });
        //Event that enacts a transaction for all the items in the cart and empties the cart as well.
        view.getButtonPane().getCompleteSale().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int i=0; i< store.getCart().size(); i++){
                    store.sellCartItems(store.getCart().get(i),store.getCart().get(i).getCartQuantity());
                    store.getCart().get(i).setCartQuantity(0); }
                store.getCart().clear();
                store.confirmSale();
                store.setCartCost(0);
                view.update(store);
                view.getButtonPane().getCompleteSale().setDisable(true);
            }
        });
        //Event that reset's the entire store.
        view.getButtonPane().getReset().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                new ElectronicStoreApp();
                store.setStock();
                view.update(store);
            }
        });
    }


    public static void main(String[] args) {
        ElectronicStore store = ElectronicStore.createStore();
        launch(args);
    }
}

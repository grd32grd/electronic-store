import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

//Pane Class
public class ElectronicStoreButtonPane extends Pane {
    //Instance Variables
    private Button reset, cartAdd, cartRemove, completeSale;

    //Getter Methods
    public Button getReset() { return reset; }
    public Button getCartAdd() { return cartAdd; }
    public Button getCartRemove() { return cartRemove; }
    public Button getCompleteSale() { return completeSale; }

    public ElectronicStoreButtonPane() {
        Pane innerPane = new Pane();

        reset = new Button("Reset Store");
        reset.relocate(0, 0);
        reset.setPrefSize(160,55);

        cartAdd = new Button("Add to Cart");
        cartAdd.relocate(220, 0);
        cartAdd.setPrefSize(160,55);
        cartAdd.setDisable(true);

        cartRemove = new Button("Remove from Cart");
        cartRemove.relocate(430, 0);
        cartRemove.setPrefSize(160,55);
        cartRemove.setDisable(true);

        completeSale = new Button("Complete Sale");
        completeSale.relocate(590, 0);
        completeSale.setPrefSize(160,55);
        completeSale.setDisable(true);

        innerPane.getChildren().addAll(reset, cartAdd, cartRemove, completeSale);

        getChildren().addAll(innerPane);
    }
}

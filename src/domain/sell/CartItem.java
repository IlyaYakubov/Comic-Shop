package domain.sell;

import domain.Comic;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Элемент корзины
 */
public class CartItem {

    private SimpleStringProperty comic;
    private SimpleDoubleProperty price;

    public CartItem(Comic comic, double price) {
        this.comic = new SimpleStringProperty(comic.getName());
        this.price = new SimpleDoubleProperty(price);
    }

    public String getComic() {
        return comic.get();
    }

    public void setComic(String comic) {
        this.comic.set(comic);
    }

    public SimpleStringProperty comicProperty() {
        return comic;
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }
}

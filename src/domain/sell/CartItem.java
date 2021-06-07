package domain.sell;

import domain.Comic;

/**
 * Элемент корзины
 */
public class CartItem {

    private String name;
    private double price;
    private Comic comic;

    public CartItem(Comic comic, double price, String name) {
        this.comic = comic;
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}

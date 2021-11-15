package domains.sale;

import domains.Comic;

/**
 * Элемент корзины
 */
public class CartItem implements Comparable<CartItem> {

    private String nameOfComic;
    private double price;
    private Comic comic;

    public CartItem(Comic comic, double price, String nameOfComic) {
        this.comic = comic;
        this.price = price;
        this.nameOfComic = nameOfComic;
    }

    public String getNameOfComic() {
        return nameOfComic;
    }

    public void setNameOfComic(String nameOfComic) {
        this.nameOfComic = nameOfComic;
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

    @Override
    public int compareTo(CartItem cartItem) {
        return this.nameOfComic.compareTo(cartItem.nameOfComic);
    }
}

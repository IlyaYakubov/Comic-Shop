package domain.sale;

/**
 * Магазин
 */
public class Store {

    private Cart cart;

    public Store(Cart cart) {
        this.cart = cart;
    }

    public void makePurchase() {
        cart.extractComics();
    }
}

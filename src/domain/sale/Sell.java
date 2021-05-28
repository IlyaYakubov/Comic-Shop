package domain.sale;

/**
 * Продажа
 */
public class Sell {

    private Cart cart;

    public Sell(Cart cart) {
        this.cart = cart;
    }

    public void makePurchase() {
        cart.extractComics();
    }
}
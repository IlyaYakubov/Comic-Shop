package domain.sell;

/**
 * Продажа
 */
public class Sell {

    private final Cart cart;

    public Sell(Cart cart) {
        this.cart = cart;
    }

    /**
     * Совершить продажу
     */
    public void makePurchase() {
        cart.clear();
    }
}

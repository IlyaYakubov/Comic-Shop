package domains.sell;

import java.time.LocalDateTime;

/**
 * Продажа
 */
public class Sell {

    private LocalDateTime date;
    private Cart cart;

    public Sell(LocalDateTime date, Cart cart) {
        this.date = date;
        this.cart = cart;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Продажа
     */
    public void makePurchase() {
        date = LocalDateTime.now();
    }
}

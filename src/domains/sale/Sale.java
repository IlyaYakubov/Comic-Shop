package domains.sale;

import java.time.LocalDateTime;

/**
 * Продажа
 */
public class Sale {

    private LocalDateTime date;
    private Cart cart;

    public Sale(LocalDateTime date, Cart cart) {
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

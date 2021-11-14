package domains.reservation;

import domains.sale.Cart;

/**
 * Резервирование комиксов
 */
public class Reservation {

    private String name;
    private Customer customer;
    private Cart cart;

    public Reservation(Customer customer, Cart cart) {
        this.customer = customer;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Резервирование
     */
    public void makeReserve() {
        name = "";
        customer = null;
        cart.clear();
    }
}

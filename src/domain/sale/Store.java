package domain.sale;

import domain.Comic;

import java.util.HashMap;
import java.util.List;

/**
 * Магазин
 */
public class Store {

    private HashMap<Customer, Cart> customersWithCarts;

    public void makePurchase(List<Comic> comics, int quantity) {
        Cart cart = new Cart();
        cart.addComic(comics, quantity);
    }
}

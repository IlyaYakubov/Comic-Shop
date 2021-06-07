package domain.discounts;

import domain.sell.Cart;
import domain.sell.CartItem;

/**
 * Акция
 */
public class Discount {

    private String name;
    private Cart cart = new Cart();

    public String getName() {
        return name;
    }

    /**
     * Добавление скидочного комикса в корзину с акцией
     *
     * @param cartItem элемент корзины
     */
    public void add(CartItem cartItem) {
        name = cartItem.getName();
        cart.addComic(cartItem.getComic());
    }
}
package domain.discounts;

import domain.sell.Cart;
import domain.sell.CartItem;

/**
 * Акция
 */
public class Discount {

    private String name;
    private String dateBegin;
    private String dateEnd;
    private final Cart cart = new Cart();

    public String getName() {
        return name;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public Cart getCart() {
        return cart;
    }

    /**
     * Добавление скидочного комикса в корзину с акцией
     *
     * @param cartItem элемент корзины
     */
    public void add(CartItem cartItem, String dateBegin, String dateEnd) {
        name = cartItem.getName();
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        cart.addComic(cartItem.getComic());
    }
}

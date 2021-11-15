package domains.sale;

import domains.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Корзина с комиксами
 * В корзине лежат элементы - которые состоят из комикса, его цены продажи и названия
 */
public class Cart {

    private double amount;
    private List<CartItem> cartItems = new ArrayList<>();

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        updateAmount();
    }

    /**
     * Добавление элемента в корзину
     *
     * @param cartItem элемент корзины
     */
    public void addItem(CartItem cartItem) {
        cartItems.add(cartItem);
        updateAmount();
    }

    /**
     * Добавление комикса в корзину
     * Получается элемент корзины состоящий из комикса, его цены и его наименования
     *
     * @param comic комикс
     */
    public void addComic(Comic comic) {
        Price price = comic.getComicPrice();
        cartItems.add(new CartItem(comic, price.getSellingPrice(), comic.getName()));
        updateAmount();
    }

    /**
     * Очистка корзины
     */
    public void clear() {
        cartItems.clear();
        amount = 0;
    }

    /**
     * @return размер корзины
     */
    public int size() {
        return cartItems.size();
    }

    /**
     * Обновление скидок
     */
    private void updateAmount() {
        amount = 0;
        for (CartItem cartItem : cartItems) {
            amount += cartItem.getPrice();
        }
    }
}

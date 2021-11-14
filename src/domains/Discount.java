package domains;

import domains.sale.Cart;
import domains.sale.CartItem;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Акция
 */
public class Discount {

    private String name;
    private LocalDateTime date;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Cart cart;

    public Discount(String name, LocalDateTime date, LocalDate dateBegin, LocalDate dateEnd, Cart cart) {
        this.name = name;
        this.date = date;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Добавление комикса в корзину с акцией
     *
     * @param comic комикс со скидкой
     */
    public void add(Comic comic) {
        cart.addComic(comic);
    }

    /**
     * Расчет скидок для установки цен комиксам
     *
     * @param percent процент скидки
     */
    public void calculateDiscounts(int percent) {
        for (CartItem cartItem : cart.getCartItems()) {
            double amount = cartItem.getComic().getComicPrice().getSellingPrice() -
                    (cartItem.getComic().getComicPrice().getSellingPrice() * percent / 100);
            cartItem.getComic().getComicPrice().setSellingPrice(amount);
        }
    }
}

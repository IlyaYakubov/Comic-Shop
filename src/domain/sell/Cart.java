package domain.sell;

import domain.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Корзина с комиксами
 */
public class Cart {

    private double amount;
    private final List<CartItem> comics = new ArrayList<>();

    public double getAmount() {
        return amount;
    }

    public List<CartItem> getComics() {
        return comics;
    }

    /**
     * Добавление комикса в корзину
     * Получается элемент корзины состоящий из комикса, его цены и его наименования
     *
     * @param comic - комикс
     */
    public void addComic(Comic comic) {
        ComicPrice comicPrice = comic.getComicPrice();
        comics.add(new CartItem(comic, comicPrice.getSellingPrice(), comic.getName()));
        amount += comicPrice.getSellingPrice();
    }

    /**
     * Удаление одного комикса из корзины
     *
     * @param comic - комикс
     */
    public void deleteComic(Comic comic) {
        for (CartItem cartItem : comics) {
            if (cartItem.getComic().getName().equals(comic.getName())) {
                comics.remove(cartItem);
                break;
            }
        }
    }

    /**
     * Очистка
     */
    public void clear() {
        comics.clear();
        amount = 0;
    }
}

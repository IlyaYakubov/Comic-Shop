package domain.sell;

import domain.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Корзина с комиксами
 */
public class Cart {

    private double amount;
    private List<CartItem> comics = new ArrayList<>();

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
     * Очистка
     */
    public void clear() {
        comics.clear();
    }
}

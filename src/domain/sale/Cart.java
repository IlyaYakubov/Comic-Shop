package domain.sale;

import domain.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Корзина с комиксами
 */
public class Cart {

    private List<Comic> comics = new ArrayList<>();

    public List<Comic> getComics() {
        return comics;
    }

    /**
     * Добавление комикса в корзину
     * @param comic - комикс
     */
    public void addComic(Comic comic) {
        comics.add(comic);
    }

    /**
     * Опустошение корзины
     */
    public void extractComics() {
        comics.clear();
    }
}

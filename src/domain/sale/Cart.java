package domain.sale;

import domain.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Корзина с комиксами
 */
public class Cart {

    private List<Comic> purchasedComics = new ArrayList<>();

    public void addComic(Comic comic) {
        purchasedComics.add(comic);
    }

    public void extractComics() {
        purchasedComics.clear();
    }
}

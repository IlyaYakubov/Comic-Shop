package domain.sale;

import domain.Comic;

import java.util.List;

/**
 * Элемент покупки комиксов (элемент корзины)
 */
public class PurchaseItem {

    private List<Comic> comics;

    public List<Comic> getComics() {
        return comics;
    }
}

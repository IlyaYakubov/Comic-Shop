package domain.sale;

import domain.Comic;

import java.util.List;

/**
 * Корзина с комиксами
 */
public class Cart {

    private List<PurchaseItem> purchasedComics;

    public void addComic(List<Comic> comics, int quantity) {
        PurchaseItem purchaseItem = new PurchaseItem();
        purchasedComics.add(purchaseItem);
    }
}

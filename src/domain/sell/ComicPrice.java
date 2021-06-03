package domain.sell;

import domain.Comic;

/**
 * Цены комикса
 */
public class ComicPrice {

    private Comic comic;
    private double costPrice;
    private double sellingPrice;

    public ComicPrice(Comic comic, double costPrice, double sellingPrice) {
        this.comic = comic;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
}

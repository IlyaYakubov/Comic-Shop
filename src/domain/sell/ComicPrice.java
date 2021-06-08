package domain.sell;

/**
 * Цены комикса
 */
public class ComicPrice {

    private final double costPrice;
    private final double sellingPrice;

    public ComicPrice(double costPrice, double sellingPrice) {
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

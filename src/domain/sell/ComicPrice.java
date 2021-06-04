package domain.sell;

/**
 * Цены комикса
 */
public class ComicPrice {

    private double costPrice;
    private double sellingPrice;

    public ComicPrice(double costPrice, double sellingPrice) {
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }
}

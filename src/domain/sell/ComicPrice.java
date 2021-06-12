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

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}

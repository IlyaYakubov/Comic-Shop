package domains.sale;

/**
 * Цены комикса
 */
public class Price {

    private double costPrice;
    private double sellingPrice;

    public Price(double costPrice, double sellingPrice) {
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

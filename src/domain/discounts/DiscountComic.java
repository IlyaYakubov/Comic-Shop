package domain.discounts;

import domain.Author;
import domain.Comic;
import domain.Genre;
import domain.Publishing;

/**
 * Комикс со скидкой
 */
public class DiscountComic extends Comic {

    private double amountOfDiscount;

    public DiscountComic(
            String name,
            Author author,
            Publishing publishing,
            int numberOfPages,
            Genre genre,
            int yearOfPublishing,
            double costPrice,
            double sellingPrice,
            boolean isContinuation) {
        super(name, author, publishing, numberOfPages, genre, yearOfPublishing, costPrice, sellingPrice, isContinuation);
    }

    public double getAmountOfDiscount() {
        return amountOfDiscount;
    }

    /**
     * Рассчитывает сумму скидки
     *
     * @param percent - процент скидки
     */
    public void calculateDiscount(int percent) {
        amountOfDiscount = getComicPrice().getSellingPrice() * percent / 100;
    }
}

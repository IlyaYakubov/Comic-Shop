package domain;

import java.time.LocalDateTime;

/**
 * Комикс для отчетов
 */
public class ReportingComic extends Comic {

    private LocalDateTime receiptDate;

    public ReportingComic(
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

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }
}

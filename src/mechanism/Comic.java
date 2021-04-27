package mechanism;

/**
 * Структура комикса
 */
public class Comic {

    // разделитель данных в файле, для точного определения необходимых данных
    private static final String DELIMITER = ";";

    private String name;
    private String author;
    private String publishing;
    private int numberOfPages;
    private String genre;
    private int yearOfPublishing;
    private double costPrice;
    private double sellingPrice;
    private boolean isContinuation;

    public Comic(String name,
                 String author,
                 String publishing,
                 int numberOfPages,
                 String genre,
                 int yearOfPublishing,
                 double costPrice,
                 double sellingPrice,
                 boolean isContinuation) {
        this.name = name;
        this.author = author;
        this.publishing = publishing;
        this.numberOfPages = numberOfPages;
        this.genre = genre;
        this.yearOfPublishing = yearOfPublishing;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.isContinuation = isContinuation;
    }

    /**
     * @return данные одного комикса
     */
    public StringBuilder getAllData() {
        StringBuilder data = new StringBuilder();
        data
                .append(name).append(DELIMITER).append(author).append(DELIMITER).append(publishing).append(DELIMITER)
                .append(numberOfPages).append(DELIMITER).append(genre).append(DELIMITER)
                .append(yearOfPublishing).append(DELIMITER).append(costPrice).append(DELIMITER)
                .append(sellingPrice).append(DELIMITER).append(isContinuation).append(DELIMITER).append("\n");
        return data;
    }
}

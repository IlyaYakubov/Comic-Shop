/**
 * class Comic
 */
public class Comic {

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
}

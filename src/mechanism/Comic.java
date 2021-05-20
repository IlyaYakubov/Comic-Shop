package mechanism;

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

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishing() {
        return publishing;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public String getGenre() {
        return genre;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public boolean isContinuation() {
        return isContinuation;
    }
}

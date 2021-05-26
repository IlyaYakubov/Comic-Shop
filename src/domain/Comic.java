package domain;

/**
 * Модель комикса
 */
public class Comic {

    private String name;
    private int numberOfPages;
    private int yearOfPublishing;
    private boolean isContinuation;

    private Author author;
    private Publishing publishing;
    private Genre genre;
    private ComicPricingBook comicPricingBook;

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
        this.numberOfPages = numberOfPages;
        this.yearOfPublishing = yearOfPublishing;
        this.isContinuation = isContinuation;

        this.author = new Author(author);
        this.publishing = new Publishing(publishing);
        this.genre = new Genre(genre);
        this.comicPricingBook = new ComicPricingBook(costPrice, sellingPrice);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publishing getPublishing() {
        return publishing;
    }

    public void setPublishing(Publishing publishing) {
        this.publishing = publishing;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public boolean isContinuation() {
        return isContinuation;
    }
}

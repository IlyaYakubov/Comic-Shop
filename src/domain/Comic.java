package domain;

import domain.sell.ComicPrice;
import javafx.beans.property.SimpleObjectProperty;

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
    private ComicPrice comicPrice;

    public Comic(String name,
                 Author author,
                 Publishing publishing,
                 int numberOfPages,
                 Genre genre,
                 int yearOfPublishing,
                 double costPrice,
                 double sellingPrice,
                 boolean isContinuation) {
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.yearOfPublishing = yearOfPublishing;
        this.isContinuation = isContinuation;
        this.author = author;
        this.publishing = publishing;
        this.genre = genre;
        this.comicPrice = new ComicPrice(this, costPrice, sellingPrice);
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

    public ComicPrice getComicPrice() {
        return comicPrice;
    }
}

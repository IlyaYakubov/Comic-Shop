package domains;

import domains.sale.Price;

import java.util.Objects;

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
    private Price price;

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
        this.price = new Price(costPrice, sellingPrice);
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

    public boolean isContinuation() {
        return isContinuation;
    }

    public void setContinuation(boolean continuation) {
        isContinuation = continuation;
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

    public Price getComicPrice() {
        return price;
    }

    public void setComicPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comic comic = (Comic) o;

        if (numberOfPages != comic.numberOfPages) return false;
        if (yearOfPublishing != comic.yearOfPublishing) return false;
        if (isContinuation != comic.isContinuation) return false;
        if (!Objects.equals(name, comic.name)) return false;
        if (!Objects.equals(author, comic.author)) return false;
        if (!Objects.equals(publishing, comic.publishing)) return false;
        if (!Objects.equals(genre, comic.genre)) return false;
        return Objects.equals(price, comic.price);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + numberOfPages;
        result = 31 * result + yearOfPublishing;
        result = 31 * result + (isContinuation ? 1 : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publishing != null ? publishing.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}

package services;

import domain.Author;
import domain.Comic;
import domain.Genre;
import domain.Publishing;
import domain.sell.Cart;
import domain.sell.CartItem;
import repository.FileDao;

import java.util.List;

public class SearchService {

    public static SearchService INSTANCE = new SearchService();
    private static final String DELIMITER = ";";

    private final FileDao fileDao = FileDao.INSTANCE;
    private Cart cart = new Cart();

    private SearchService() {
    }

    /**
     * Получить все комиксы
     *
     * @return список комиксов в форме элемента корзины
     */
    public List<CartItem> getAllComics() {
        cart.clear();
        List<String> stringsComics = fileDao.readFromFile();
        for (String stringComic : stringsComics) {
            String[] elementsOfComic = stringComic.split(DELIMITER);
            Comic comic = new Comic(
                    elementsOfComic[0], // наименование
                    new Author(elementsOfComic[1]), // автор
                    new Publishing(elementsOfComic[2]), // издательство
                    Integer.parseInt(elementsOfComic[3]), // количество страниц
                    new Genre(elementsOfComic[4]), // жанр
                    Integer.parseInt(elementsOfComic[5]), // год издания
                    Double.parseDouble(elementsOfComic[6]), // себестоимость
                    Double.parseDouble(elementsOfComic[7]), // цена продажи
                    Boolean.parseBoolean(elementsOfComic[8]) // является ли продолжением
            );
            cart.addComic(comic);
        }

        return cart.getComics();
    }

    /**
     * Получение комикса по наименованию
     *
     * @param comicHashCode - хэш код комикса
     * @return - комикс из коллекции
     */
    public CartItem getComicByHashCode(String comicHashCode) {
        for (CartItem cartItem : cart.getComics()) {
            if (comicHashCode.equals(String.valueOf(cartItem.getComic().hashCode()))) {
                return cartItem;
            }
        }
        return null;
    }

    /**
     * Получение комикса по наименованию
     *
     * @param comicName - наименование комикса
     * @return - комикс из коллекции
     */
    public CartItem getComicByName(String comicName) {
        for (CartItem cartItem : cart.getComics()) {
            if (comicName.equals(cartItem.getComic().getName())) {
                return cartItem;
            }
        }
        return null;
    }
}

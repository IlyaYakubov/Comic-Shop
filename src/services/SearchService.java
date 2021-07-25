package services;

import domain.Comic;
import domain.sell.Cart;
import domain.sell.CartItem;

import java.util.List;

/**
 * Сервис по поиску комикса
 */
public class SearchService {

    public static SearchService INSTANCE = new SearchService();
    private final Cart CART = new Cart();
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    private SearchService() {
    }

    /**
     * Получить все элементы комикса корзины
     *
     * @return элементы корзины
     */
    public List<CartItem> getAllCartItems() {
        updateComicsFromFile();
        return CART.getCartItems();
    }

    /**
     * Получить элементы комикса корзины определенного наименования
     *
     * @return элементы корзины
     */
    public List<CartItem> getAllCartItems(String comicName) {
        updateComicsFromFile();
        CART.getCartItems().removeIf(cartItem -> !cartItem.getComic().getName().equals(comicName));
        return CART.getCartItems();
    }

    /**
     * Получить отфильтрованные элементы комикса корзины
     *
     * @return элементы корзины
     */
    public List<CartItem> getAllCartItems(List<CartItem> cartItems) {
        updateComicsFromFile();
        cartItems.forEach(cartItemFilter -> CART.getCartItems().removeIf(cartItem -> cartItemFilter.getComic().equals(cartItem.getComic())));
        return CART.getCartItems();
    }

    /**
     * Получение комикса по наименованию
     *
     * @param comicName наименование комикса
     * @return комикс из коллекции
     */
    public Comic getComicByName(String comicName) {
        updateComicsFromFile();
        for (CartItem cartItem : CART.getCartItems()) {
            if (comicName.equals(cartItem.getComic().getName())) {
                return cartItem.getComic();
            }
        }
        return null;
    }

    /**
     * Получение комикса по автору
     *
     * @param comicAuthor автор комикса
     * @return комикс из коллекции
     */
    public List<CartItem> getComicByAuthor(String comicAuthor) {
        updateComicsFromFile();
        CART.getCartItems().removeIf(cartItem -> !cartItem.getComic().getAuthor().getName().equals(comicAuthor));
        return CART.getCartItems();
    }

    /**
     * Получение комикса по жанру
     *
     * @param comicGenre жанр комикса
     * @return комикс из коллекции
     */
    public List<CartItem> getComicByGenre(String comicGenre) {
        updateComicsFromFile();
        CART.getCartItems().removeIf(cartItem -> !cartItem.getComic().getGenre().getName().equals(comicGenre));
        return CART.getCartItems();
    }

    private void updateComicsFromFile() {
        CART.clear();
        for (Comic comic : COMIC_SERVICE.getComics()) {
            CART.addComic(comic);
        }
    }
}

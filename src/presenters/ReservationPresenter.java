package presenters;

import domain.Comic;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import services.ComicService;
import services.SearchService;

import java.util.List;

/**
 * Контроллер резервирования комиксов
 */
public class ReservationPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final Cart CART = new Cart();
    private TableView<CartItem> tableComics;

    public ReservationPresenter() {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
    }

    /**
     * При нажатии на кнопку добавления комикса в корзину
     *
     * @param comicName наименование комикса
     */
    public void onClickAdd(String comicName) {
        Comic comic = SEARCH_SERVICE.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        CART.addComic(comic);
        setContent(CART.getCartItems());
    }

    /**
     * При нажатии на кнопку "Зарезервировать"
     *
     * @param customer имя клиента
     */
    public void onClickReservation(String customer) {
        COMIC_SERVICE.reserveComics(CART, customer);
        tableComics.refresh();
    }

    public void setTable(TableView<CartItem> tableComics) {
        this.tableComics = tableComics;
    }

    /**
     * Установка контента в элементы окна
     *
     * @param cartItems список комиксов
     */
    public void setContent(List<CartItem> cartItems) {
        tableComics.setItems(FXCollections.observableArrayList(cartItems));
        tableComics.refresh();
    }
}

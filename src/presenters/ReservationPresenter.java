package presenters;

import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import services.SearchService;
import ui.reservation.ReservationUI;

/**
 * Контроллер резервирования комиксов
 */
public class ReservationPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final ReservationUI RESERVATION_UI;
    private final Cart cart = new Cart();

    public ReservationPresenter(ReservationUI reservationUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        this.RESERVATION_UI = reservationUI;
    }

    /**
     * При нажатии на кнопку добавления комикса в корзину
     *
     * @param comicName - наименование комикса
     */
    public void onClickAdd(String comicName) {
        CartItem comic = SEARCH_SERVICE.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        cart.addComic(comic.getComic());
        ObservableList<CartItem> comics = FXCollections.observableArrayList(cart.getComics());
        RESERVATION_UI.setContent(comics);
    }

    /**
     * При нажатии на кнопку "Зарезервировать"
     *
     * @param customer - имя клиента
     */
    public void onClickReservation(String customer) {
        COMIC_SERVICE.reservationComics(cart, customer);
    }
}

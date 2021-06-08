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

    private final ComicService comicService;
    private final SearchService searchService;
    private final ReservationUI reservationUI;
    private final Cart cart = new Cart();

    public ReservationPresenter(ReservationUI reservationUI) {
        comicService = ComicService.INSTANCE;
        searchService = new SearchService();
        this.reservationUI = reservationUI;
    }

    /**
     * При нажатии на кнопку добавления комикса в корзину
     *
     * @param comicName - наименование комикса
     */
    public void onClickAdd(String comicName) {
        CartItem comic = searchService.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        cart.addComic(comic.getComic());
        ObservableList<CartItem> comics = FXCollections.observableArrayList(cart.getComics());
        reservationUI.setContent(comics);
    }

    /**
     * При нажатии на кнопку "Зарезервировать"
     *
     * @param customer - имя клиента
     */
    public void onClickReservation(String customer) {
        comicService.reservationComics(cart, customer);
    }
}

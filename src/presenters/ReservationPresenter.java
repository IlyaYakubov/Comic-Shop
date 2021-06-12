package presenters;

import domain.Comic;
import domain.sell.Cart;
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
    private final Cart CART = new Cart();

    public ReservationPresenter(ReservationUI reservationUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        RESERVATION_UI = reservationUI;
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
        RESERVATION_UI.setContent(CART.getCartItems());
    }

    /**
     * При нажатии на кнопку "Зарезервировать"
     *
     * @param customer имя клиента
     */
    public void onClickReservation(String customer) {
        COMIC_SERVICE.reserveComics(CART, customer);
    }
}

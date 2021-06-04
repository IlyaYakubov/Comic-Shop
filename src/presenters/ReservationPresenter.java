package presenters;

import domain.Comic;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import ui.ReservationUI;

/**
 * Контроллер резервирования комиксов
 */
public class ReservationPresenter {

    private ComicService comicService;
    private ReservationUI reservationUI;
    private Cart cart = new Cart();

    public ReservationPresenter(ReservationUI reservationUI) {
        comicService = new ComicService();
        this.reservationUI = reservationUI;
    }

    /**
     * При нажатии на кнопку добавления комикса в корзину
     *
     * @param comicName - наименование комикса
     */
    public void onClickAdd(String comicName) {
        Comic comic = comicService.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        cart.addComic(comic);
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

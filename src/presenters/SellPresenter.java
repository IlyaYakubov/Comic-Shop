package presenters;

import domain.discounts.Discount;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import services.SearchService;
import ui.SellUI;

import java.time.LocalDate;
import java.util.List;

/**
 * Контроллер продажи комиксов
 */
public class SellPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final SellUI SELL_UI;
    private final Cart cart = new Cart();

    public SellPresenter(SellUI sellUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        this.SELL_UI = sellUI;
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

        List<Discount> discounts = COMIC_SERVICE.getDiscounts();
        for (Discount discount : discounts) {
            for (CartItem cartItem : discount.getCart().getComics()) {
                if (cartItem.getComic().getName().equals(comicName)
                        && LocalDate.parse(discount.getDateBegin()).isBefore(LocalDate.now())
                        && LocalDate.parse(discount.getDateEnd()).isAfter(LocalDate.now())) {
                    comic.setComic(cartItem.getComic());
                }
            }
        }

        cart.addComic(comic.getComic());
        ObservableList<CartItem> comics = FXCollections.observableArrayList(cart.getComics());
        SELL_UI.setContent(comics, cart.getAmount());
    }

    /**
     * При нажатии на кнопку "Продажа"
     */
    public void onClickSale() {
        COMIC_SERVICE.makePurchase(cart);
    }

    /**
     * При нажатии на кнопку "Резерв"
     *
     * @param customerName - имя клиента
     */
    public void onClickReserve(String customerName) {
        for (CartItem cartItem : COMIC_SERVICE.reservedComics(customerName)) {
            cart.addComic(cartItem.getComic());
        }
        ObservableList<CartItem> comicsList = FXCollections.observableArrayList(cart.getComics());
        SELL_UI.setContent(comicsList, cart.getAmount());
    }
}

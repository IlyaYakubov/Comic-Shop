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
import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер продажи комиксов
 */
public class SellPresenter {

    private final ComicService comicService;
    private final SearchService searchService;
    private final SellUI sellUI;
    private final Cart cart = new Cart();

    public SellPresenter(SellUI sellUI) {
        comicService = ComicService.INSTANCE;
        searchService = new SearchService();
        this.sellUI = sellUI;
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

        List<Discount> discounts = comicService.getDiscounts();
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
        sellUI.setContent(comics, cart.getAmount());
    }

    /**
     * При нажатии на кнопку "Продажа"
     */
    public void onClickSale() {
        comicService.makePurchase(cart);
    }

    /**
     * При нажатии на кнопку "Резерв"
     *
     * @param customerName - имя клиента
     */
    public void onClickReserve(String customerName) {
        for (CartItem cartItem : comicService.reservedComics(customerName)) {
            cart.addComic(cartItem.getComic());
        }
        ObservableList<CartItem> comicsList = FXCollections.observableArrayList(cart.getComics());
        sellUI.setContent(comicsList, cart.getAmount());
    }
}

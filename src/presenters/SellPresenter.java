package presenters;

import domain.Comic;
import domain.discounts.Discount;
import domain.sell.Cart;
import domain.sell.CartItem;
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

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final SellUI SELL_UI;
    private final Cart CART = new Cart();

    public SellPresenter(SellUI sellUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        SELL_UI = sellUI;
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
        CART.setCartItems(SELL_UI.getTable().getItems());
        List<Discount> discounts = COMIC_SERVICE.getDiscounts();
        if (discounts.size() == 0) {
            CART.addComic(comic);
        }

        // проверка: если комикс с данным наименованием присутсвует в акции - добавляем его
        boolean wasReplacement = false;
        outer:
        for (Discount discount : discounts) {
            for (CartItem cartItem : discount.getCart().getCartItems()) {
                if (cartItem.getComic().getName().equals(comicName)
                        && discount.getDateBegin().isBefore(LocalDate.now())
                        && discount.getDateEnd().isAfter(LocalDate.now())) {
                    CART.addComic(cartItem.getComic());
                    wasReplacement = true;
                    break outer;
                }
            }
        }

        if (discounts.size() > 0 && !wasReplacement) {
            CART.addComic(comic);
        }

        SELL_UI.setContent(CART.getCartItems(), CART.getAmount());
        CART.clear();
    }

    /**
     * При нажатии на кнопку "Продажа"
     */
    public void onClickSale() {
        CART.setCartItems(SELL_UI.getTable().getItems());
        COMIC_SERVICE.makePurchase(LocalDateTime.now(), CART);
    }

    /**
     * При нажатии на кнопку "Резерв"
     *
     * @param customerName имя клиента
     */
    public void onClickReserve(String customerName) {
        CART.setCartItems(SELL_UI.getTable().getItems());
        Cart cartWithReserves = COMIC_SERVICE.getCustomersReservedComics(customerName);
        for (CartItem cartItem : cartWithReserves.getCartItems()) {
            CART.addItem(cartItem);
        }
        SELL_UI.setContent(CART.getCartItems(), CART.getAmount());
        cartWithReserves.clear();
        CART.clear();
    }
}

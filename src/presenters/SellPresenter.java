package presenters;

import domain.Comic;
import domain.discounts.Discount;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import services.ComicService;
import services.SearchService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер продажи комиксов
 */
public class SellPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final Cart CART = new Cart();
    private TableView<CartItem> table;
    private Label labelAmount;

    public SellPresenter() {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
    }

    public void setTable(TableView<CartItem> table) {
        this.table = table;
    }

    public void setLabelAmount(Label labelAmount) {
        this.labelAmount = labelAmount;
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
        CART.setCartItems(table.getItems());
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

        setContent(CART.getCartItems(), CART.getAmount());
        CART.clear();
    }

    /**
     * При нажатии на кнопку "Продажа"
     */
    public void onClickSale() {
        CART.setCartItems(table.getItems());
        List<CartItem> cartItems = new ArrayList<>(table.getItems());
        CART.setCartItems(cartItems);
        COMIC_SERVICE.makePurchase(LocalDateTime.now(), CART);
    }

    /**
     * При нажатии на кнопку "Резерв"
     *
     * @param customerName имя клиента
     */
    public void onClickReserve(String customerName) {
        CART.setCartItems(table.getItems());
        Cart cartWithReserves = COMIC_SERVICE.getCustomersReservedComics(customerName);
        for (CartItem cartItem : cartWithReserves.getCartItems()) {
            CART.addItem(cartItem);
        }
        setContent(CART.getCartItems(), CART.getAmount());
        cartWithReserves.clear();
        CART.clear();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param cartItems список комиксов
     * @param amount    сумма
     */
    public void setContent(List<CartItem> cartItems, double amount) {
        table.setItems(FXCollections.observableArrayList(cartItems));
        table.refresh();
        labelAmount.setText(String.valueOf(amount));
    }
}

package presenters;

import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import ui.discount.DiscountListUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер списка акций на комиксы
 */
public class DiscountListPresenter {

    private DiscountListUI discountListUI;
    private ComicService comicService = new ComicService();
    private List<CartItem> cartItems = new ArrayList<>();

    public DiscountListPresenter(DiscountListUI discountListUI) {
        this.discountListUI = discountListUI;
    }

    /**
     * Обновление отображения скидок в таблице
     */
    public void updateTableDiscounts() {
        comicService.getDiscounts(cartItems);
        ObservableList<CartItem> comicsList = FXCollections.observableArrayList(cartItems);
        discountListUI.setContent(comicsList);
    }
}

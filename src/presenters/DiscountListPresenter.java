package presenters;

import domain.discounts.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import ui.old.discount.DiscountListUI;

/**
 * Контроллер списка акций на комиксы
 */
public class DiscountListPresenter {

    private final DiscountListUI DISCOUNT_LIST_UI;
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    public DiscountListPresenter(DiscountListUI discountListUI) {
        DISCOUNT_LIST_UI = discountListUI;
    }

    /**
     * Обновление отображения скидок в таблице
     */
    public void updateTableDiscounts() {
        ObservableList<Discount> comicsList = FXCollections.observableArrayList(COMIC_SERVICE.getDiscounts());
        DISCOUNT_LIST_UI.setContent(comicsList);
    }
}

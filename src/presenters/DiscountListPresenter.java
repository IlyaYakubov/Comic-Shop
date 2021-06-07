package presenters;

import domain.discounts.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import ui.discount.DiscountListUI;

/**
 * Контроллер списка акций на комиксы
 */
public class DiscountListPresenter {

    private DiscountListUI discountListUI;
    private ComicService comicService = new ComicService();

    public DiscountListPresenter(DiscountListUI discountListUI) {
        this.discountListUI = discountListUI;
    }

    /**
     * Обновление отображения скидок в таблице
     */
    public void updateTableDiscounts() {
        ObservableList<Discount> comicsList = FXCollections.observableArrayList(comicService.getDiscounts());
        discountListUI.setContent(comicsList);
    }
}

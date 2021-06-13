package presenters;

import domain.discounts.Discount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.ComicService;

/**
 * Контроллер списка акций на комиксы
 */
public class DiscountListPresenter {

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private TableView<Discount> table;

    public void setTable(TableView<Discount> table) {
        this.table = table;
    }

    /**
     * Обновление отображения скидок в таблице
     */
    public void updateTableDiscounts() {
        ObservableList<Discount> comicsList = FXCollections.observableArrayList(COMIC_SERVICE.getDiscounts());
        setContent(comicsList);
    }

    /**
     * Установка контента в элементы окна
     *
     * @param discounts список акций
     */
    public void setContent(ObservableList<Discount> discounts) {
        table.setItems(discounts);
    }
}

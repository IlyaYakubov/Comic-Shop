package presenters;

import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.ComicService;

import java.time.LocalDateTime;

/**
 * Контроллер акций на комиксы
 */
public class DiscountPresenter {

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private TableView<CartItem> table;
    private TextField textFieldDiscountName;
    private DatePicker datePickerBegin;
    private DatePicker datePickerEnd;
    private int percent;

    public void setTable(TableView<CartItem> table) {
        this.table = table;
    }

    public void setTextFieldDiscountName(TextField textFieldDiscountName) {
        this.textFieldDiscountName = textFieldDiscountName;
    }

    public void setDatePickerBegin(DatePicker datePickerBegin) {
        this.datePickerBegin = datePickerBegin;
    }

    public void setDatePickerEnd(DatePicker datePickerEnd) {
        this.datePickerEnd = datePickerEnd;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    /**
     * При нажатии на кнопку "Создать акцию"
     */
    public void onClickCreate() {
        Cart cart = new Cart();
        cart.setCartItems(table.getItems());
        COMIC_SERVICE.saveDiscounts(LocalDateTime.now(),
                textFieldDiscountName.getText(),
                datePickerBegin.getValue(),
                datePickerEnd.getValue(),
                cart,
                percent);
    }

    /**
     * Обновление скидок
     */
    public void updateTableDiscounts() {
        table.getItems().forEach(cartItem ->
                cartItem.setPrice(cartItem.getComic().getComicPrice().getSellingPrice()));
        table.getItems().forEach(cartItem -> cartItem.setPrice(
                cartItem.getComic().getComicPrice().getSellingPrice() -
                        cartItem.getComic().getComicPrice().getSellingPrice() * percent / 100));
        table.refresh();
    }
}

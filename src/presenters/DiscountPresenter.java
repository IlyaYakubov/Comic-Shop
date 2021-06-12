package presenters;

import domain.sell.Cart;
import services.ComicService;
import ui.old.discount.DiscountUI;

import java.time.LocalDateTime;

/**
 * Контроллер акций на комиксы
 */
public class DiscountPresenter {

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private final DiscountUI DISCOUNT_UI;

    public DiscountPresenter(DiscountUI discountUI) {
        DISCOUNT_UI = discountUI;
    }

    /**
     * При нажатии на кнопку "Создать акцию"
     */
    public void onClickCreate() {
        Cart cart = new Cart();
        cart.setCartItems(DISCOUNT_UI.getTable().getItems());
        COMIC_SERVICE.saveDiscounts(LocalDateTime.now(),
                DISCOUNT_UI.getTextFieldDiscountName().getText(),
                DISCOUNT_UI.getDatePickerBegin().getValue(),
                DISCOUNT_UI.getDatePickerEnd().getValue(),
                cart,
                DISCOUNT_UI.getPercent());
    }

    /**
     * Обновление скидок
     *
     * @param percent процент скидки
     */
    public void updateTableDiscounts(int percent) {
        DISCOUNT_UI.getTable().getItems().forEach(cartItem ->
                cartItem.setPrice(cartItem.getComic().getComicPrice().getSellingPrice()));
        DISCOUNT_UI.getTable().getItems().forEach(cartItem -> cartItem.setPrice(
                cartItem.getComic().getComicPrice().getSellingPrice() -
                        cartItem.getComic().getComicPrice().getSellingPrice() * percent / 100));
        DISCOUNT_UI.getTable().refresh();
    }
}

package presenters;

import domain.discounts.DiscountComic;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.ObservableList;
import services.ComicService;
import services.SearchService;
import ui.discount.DiscountUI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер акций на комиксы
 */
public class DiscountPresenter {

    private final SearchService SEARCHE_SERVICE = SearchService.INSTANCE;
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private final List<CartItem> CART_ITEMS = new ArrayList<>();
    private DiscountUI discountUI;

    public DiscountPresenter() {
        for (CartItem cartItem : SEARCHE_SERVICE.getAllComics()) {
            DiscountComic discountComic = new DiscountComic(
                    cartItem.getComic().getName(),
                    cartItem.getComic().getAuthor(),
                    cartItem.getComic().getPublishing(),
                    cartItem.getComic().getNumberOfPages(),
                    cartItem.getComic().getGenre(),
                    cartItem.getComic().getYearOfPublishing(),
                    cartItem.getComic().getComicPrice().getCostPrice(),
                    cartItem.getComic().getComicPrice().getSellingPrice(),
                    cartItem.getComic().isContinuation());
            cartItem.setComic(discountComic);
            CART_ITEMS.add(cartItem);
        }
    }

    public DiscountPresenter(DiscountUI discountUI) {
        this();
        this.discountUI = discountUI;
    }

    /**
     * Получение всех комиксов за исключением тех, которые уже добавлены
     *
     * @param items - уже добавленные комиксы
     * @return - элементы корзины с комиксами
     */
    public List<CartItem> getFilteredComics(ObservableList<CartItem> items) {
        CART_ITEMS.removeAll(items);
        return CART_ITEMS;
    }

    /**
     * Получение комикса по наименованию
     *
     * @param hashCode - хэш код комикса
     * @return - элемент корзины
     */
    public CartItem getComicByHashCode(String hashCode) {
        return SEARCHE_SERVICE.getComicByHashCode(hashCode);
    }

    /**
     * Обновление отображения скидок в таблице
     */
    public void updateTableDiscounts() {
        for (CartItem item : discountUI.getTable().getItems()) {
            DiscountComic discountComic = (DiscountComic) item.getComic();
            discountComic.calculateDiscount(discountUI.getPercent());
            item.setPrice(item.getComic().getComicPrice().getSellingPrice() - discountComic.getAmountOfDiscount());
        }
        discountUI.getTable().refresh();
    }

    /**
     * При создании акции
     */
    public void onClickCreate() {
        Cart cart = new Cart();
        for (CartItem item : discountUI.getTable().getItems()) {
            cart.addComic(item.getComic());
        }
        COMIC_SERVICE.setDiscounts(
                String.valueOf(LocalDateTime.now()),
                discountUI.getTextFieldDiscountName().getText(),
                discountUI.getPercent(),
                String.valueOf(discountUI.getDatePickerBegin().getValue()),
                String.valueOf(discountUI.getDatePickerEnd().getValue()),
                cart);
    }
}

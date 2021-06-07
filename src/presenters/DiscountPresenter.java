package presenters;

import domain.DiscountComic;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.ObservableList;
import services.ComicService;
import services.SearchService;
import ui.discount.DiscountUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер акций на комиксы
 */
public class DiscountPresenter {

    private DiscountUI discountUI;
    private SearchService searchService = new SearchService();
    private ComicService comicService = new ComicService();
    private List<CartItem> cartItems = new ArrayList<>();

    public DiscountPresenter() {
        for (CartItem cartItem : searchService.getAllComics()) {
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
            cartItems.add(cartItem);
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
        cartItems.removeAll(items);
        return cartItems;
    }

    /**
     * Получение комикса по наименованию
     *
     * @param comicName - наименование комикса
     * @return - элемент корзины
     */
    public CartItem getComicByName(String comicName) {
        return searchService.getComicByName(comicName);
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
        comicService.setDiscounts(
                discountUI.getTextFieldDiscountName().getText(),
                String.valueOf(discountUI.getPercent()),
                String.valueOf(discountUI.getDatePickerBegin().getValue()),
                String.valueOf(discountUI.getDatePickerEnd().getValue()),
                cart);
    }
}

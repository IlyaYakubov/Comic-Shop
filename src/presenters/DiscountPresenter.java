package presenters;

import domain.sell.CartItem;
import javafx.collections.ObservableList;
import services.SearchService;

import java.util.List;

public class DiscountPresenter {

    private SearchService searchService = new SearchService();
    private List<CartItem> cartItems;

    public DiscountPresenter() {
        cartItems = searchService.getAllComics();
    }

    public List<CartItem> getFilteredComics(ObservableList<CartItem> items) {
        cartItems.removeAll(items);
        return cartItems;
    }

    public void onClickCreate() {

    }

    public CartItem getComicByName(String s) {
        return searchService.getComicByName(s);
    }
}

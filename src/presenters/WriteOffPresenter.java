package presenters;

import domain.Comic;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import services.ComicService;
import services.SearchService;

import java.util.List;

/**
 * Контроллер списания комиксов
 */
public class WriteOffPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final Cart CART = new Cart();
    private TableView<CartItem> table;

    public WriteOffPresenter() {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
    }

    public void setTable(TableView<CartItem> table) {
        this.table = table;
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
        CART.addComic(comic);
        setContent(CART.getCartItems());
    }

    /**
     * При нажатии на кнопку "списать"
     */
    public void onClickWriteOff() {
        COMIC_SERVICE.writeOffComics(CART);
    }

    /**
     * Установка контента в элементы окна
     *
     * @param cartItems список комиксов
     */
    public void setContent(List<CartItem> cartItems) {
        table.setItems(FXCollections.observableArrayList(cartItems));
        table.refresh();
    }
}

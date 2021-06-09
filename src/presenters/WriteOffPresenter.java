package presenters;

import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import services.SearchService;
import ui.WriteOffUI;

/**
 * Контроллер списания комиксов
 */
public class WriteOffPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final WriteOffUI WRITE_OFF_UI;
    private final Cart cart = new Cart();

    public WriteOffPresenter(WriteOffUI writeOffUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        this.WRITE_OFF_UI = writeOffUI;
    }

    /**
     * При нажатии на кнопку добавления комикса в корзину
     *
     * @param comicName - наименование комикса
     */
    public void onClickAdd(String comicName) {
        CartItem comic = SEARCH_SERVICE.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        cart.addComic(comic.getComic());
        ObservableList<CartItem> comics = FXCollections.observableArrayList(cart.getComics());
        WRITE_OFF_UI.setContent(comics);
    }

    /**
     * При нажатии на кнопку "списать"
     */
    public void onClickWriteOff() {
        COMIC_SERVICE.writeOffComics(cart);
    }
}

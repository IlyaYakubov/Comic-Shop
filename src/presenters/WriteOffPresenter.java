package presenters;

import domain.Comic;
import domain.sell.Cart;
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
    private final Cart CART = new Cart();

    public WriteOffPresenter(WriteOffUI writeOffUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        WRITE_OFF_UI = writeOffUI;
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
        WRITE_OFF_UI.setContent(CART.getCartItems());
    }

    /**
     * При нажатии на кнопку "списать"
     */
    public void onClickWriteOff() {
        COMIC_SERVICE.writeOffComics(CART);
    }
}

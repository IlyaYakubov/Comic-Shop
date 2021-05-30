package presenters;

import domain.Comic;
import domain.sale.Cart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.ComicService;
import ui.SellUI;

/**
 * Контроллер продажи комиксов
 */
public class SellPresenter {

    private ComicService comicService;
    private SellUI sellUI;
    private Cart cart = new Cart();

    public SellPresenter(SellUI sellUI) {
        comicService = new ComicService();
        this.sellUI = sellUI;
    }

    /**
     * При нажатии на кнопку добавления комикса в корзину
     * @param comicName - наименование комикса
     */
    public void onClickAdd(String comicName) {
        Comic comic = comicService.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        cart.addComic(comic);
        ObservableList<Comic> comics = FXCollections.observableArrayList(cart.getComics());
        sellUI.setContent(comics);
    }

    /**
     * При нажатии на кнопку "продажа"
     */
    public void onClickSale() {
        comicService.makePurchase(cart);
    }
}

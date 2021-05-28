package presenters;

import services.ComicService;
import ui.SellUI;

/**
 * Сервис продажи комиксов
 */
public class SellPresenter {

    private ComicService comicService;
    private SellUI sellUI;

    public SellPresenter(SellUI sellUI) {
        comicService = new ComicService();
        this.sellUI = sellUI;
    }

    /**
     * Совершить продажу комиксов, которые в корзине
     */
    public void onClickSale(String comicName) {
        comicService.makePurchase(comicName);
    }
}

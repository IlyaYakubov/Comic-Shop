package presenters;

import services.ComicService;

/**
 * Сервис продажи комиксов
 */
public class SalePresenter {

    private ComicService comicService = new ComicService(this);

    /**
     * Положить комикс в корзину перед продажей
     * @param nameOfComic наименование комикса
     */
    public void onClickAddInCart(String nameOfComic) {
        comicService.putComicsInCartForSale(nameOfComic);
    }

    /**
     * Совершить продажу комиксов, которые в корзине
     */
    public void onClickSale() {
        comicService.makePurchase();
    }
}

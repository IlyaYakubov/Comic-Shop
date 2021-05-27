package services;

import domain.sale.Cart;
import domain.sale.Store;
import presenters.SalePresenter;
import repository.FileDao;
import domain.Comic;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис по работе с комиксами
 */
public class ComicService {

    private FileDao fileDao = new FileDao();
    private List<Comic> comicList = fileDao.getAllComics();
    private Cart cart;
    private SalePresenter salePresenter;

    public ComicService(SalePresenter salePresenter) {
        this.salePresenter = salePresenter;
    }

    /**
     * Добавление комикса
     * @param comic комикс
     */
    public void addComic(Comic comic) {
        fileDao.add(comic);
    }

    /**
     * Удаление комикса
     * @param nameOfComic название комикса
     */
    public void deleteComic(String nameOfComic) {
        fileDao.delete(nameOfComic);
    }

    /**
     * Редактирование комикса
     * @param nameOfComic название комикса
     * @param elementOfComic редактируемый элемент комикса
     * @param newElement новый элемент
     */
    public void editComic(String nameOfComic, int elementOfComic, String newElement) {
        fileDao.edit(nameOfComic, elementOfComic, newElement);
    }

    /**
     * Предпродажная подготовка
     * @param nameOfComic название комикса
     */
    public void putComicsInCartForSale(String nameOfComic) {
        for (Comic comic : comicList) {
            if (comic.equals(nameOfComic)) {
                cart = new Cart();
                cart.addComic(comic);
                comicList.remove(comic);
                fileDao.delete(nameOfComic);
                break;
            }
        }
    }

    public void makePurchase() {
        if (cart.isEmpty()) {
            return;
        }
        Store store = new Store(cart);
        store.makePurchase();
    }
}

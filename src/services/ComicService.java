package services;

import domain.Comic;
import domain.sale.Cart;
import domain.sale.Sell;
import repository.FileDao;

import java.util.List;

/**
 * Сервис по работе с комиксами
 */
public class ComicService {

    private static final String DELIMITER = ";";

    private FileDao fileDao = new FileDao();
    private Cart cart;

    /**
     * Добавление комикса
     *
     * @param comic комикс
     */
    public void addComic(String[] comic) {
        fileDao.saveToFile(formComicFromElements(comic).toString());
    }

    /**
     * Удаление комикса
     * Если в файле есть комиксы, то при совпадении имени комикс не перезаписывается по новой
     * (т.к. файл предварительно удаляется)
     *
     * @param nameOfComic название комикса
     */
    public void deleteComic(String nameOfComic) {
        List<String> comicsList = fileDao.readFromFile();
        if (comicsList.size() > 0) {
            fileDao.deleteFile();
            for (String comic : comicsList) {
                String[] arrayOfElements = comic.split(DELIMITER);
                if (arrayOfElements[0].equals(nameOfComic)) {
                    continue;
                }
                fileDao.saveToFile(comic);
            }
        }
    }

    /**
     * Продажа комикса
     *
     * @param comicName наименование продаваемого комикса
     */
    public void makePurchase(String comicName) {
        cart = new Cart();
        Comic comic = fileDao.getComicByName(comicName);
        if (comic != null) {
            cart.addComic(comic);
            Sell sell = new Sell(cart);
            sell.makePurchase();
            deleteComic(comicName);
        }
    }

    private StringBuilder formComicFromElements(String[] comic) {
        StringBuilder data = new StringBuilder();
        data
                .append(comic[0]).append(DELIMITER)
                .append(comic[1]).append(DELIMITER)
                .append(comic[2]).append(DELIMITER)
                .append(comic[3]).append(DELIMITER)
                .append(comic[4]).append(DELIMITER)
                .append(comic[5]).append(DELIMITER)
                .append(comic[6]).append(DELIMITER)
                .append(comic[7]).append(DELIMITER)
                .append(comic[8]).append(DELIMITER);
        return data;
    }
}

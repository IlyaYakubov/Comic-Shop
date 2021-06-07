package services;

import domain.sell.Cart;
import domain.sell.CartItem;
import domain.sell.Sell;
import repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис по работе с комиксами
 */
public class ComicService {

    private static final String DELIMITER = ";";

    private FileDao fileDao = new FileDao();
    private List<String> comicsInFile = fileDao.readFromFile();

    /**
     * Добавление комикса из элементов
     *
     * @param comic - элементы комикса
     */
    public void addComic(String[] comic) {
        fileDao.saveToFile(formComicFromElements(comic).toString());
    }

    /**
     * Редактирование комикса
     *
     * @param comic - элементы комикса
     */
    public void editComic(String[] comic) {
        fileDao.deleteFile();

        List<String> newListOfComics = new ArrayList<>();
        for (String elementsOfComic : comicsInFile) {
            String[] arrayOfElements = elementsOfComic.split(DELIMITER);
            if (comic[0].equals(arrayOfElements[0])) {
                newListOfComics.add(formComicFromElements(comic).toString());
                continue;
            }
            newListOfComics.add(elementsOfComic);
        }

        comicsInFile = newListOfComics;
        for (String newComic : newListOfComics) {
            fileDao.saveToFile(newComic);
        }
    }

    /**
     * Удаление комикса из файла
     * При совпадении имени комикс не перезаписывается
     * (файл предварительно удаляется)
     *
     * @param nameOfComic - название комикса
     */
    public void deleteComic(String nameOfComic) {
        if (comicsInFile.size() > 0) {
            List<String> newListOfComics = new ArrayList<>();
            fileDao.deleteFile();
            boolean wasAPass = false;
            for (String comic : comicsInFile) {
                String[] arrayOfElements = comic.split(DELIMITER);
                if (arrayOfElements[0].equals(nameOfComic) && !wasAPass) {
                    wasAPass = true;
                    continue;
                }
                newListOfComics.add(comic);
                fileDao.saveToFile(comic);
            }
            comicsInFile = newListOfComics;
        }
    }

    /**
     * Продажа комиксов
     *
     * @param cart - корзина комиксов
     */
    public void makePurchase(Cart cart) {
        for (CartItem item : cart.getComics()) {
            deleteComic(item.getComic().getName());
        }

        SellDao sellDao = new SellDao();
        for (CartItem comic : cart.getComics()) {
            String buyItem = LocalDateTime.now() + DELIMITER + comic.getComic().getName() + DELIMITER
                    + comic.getPrice();
            sellDao.saveToFile(buyItem);
        }
        Sell sell = new Sell(cart);
        sell.makePurchase();
    }

    /**
     * Списание комиксов
     *
     * @param cart - корзина комиксов
     */
    public void writeOffComics(Cart cart) {
        for (CartItem comic : cart.getComics()) {
            deleteComic(comic.getComic().getName());
        }

        WriteOffDao writeOffDao = new WriteOffDao();
        for (CartItem comic : cart.getComics()) {
            String buyItem = LocalDateTime.now() + DELIMITER + comic.getComic().getName();
            writeOffDao.saveToFile(buyItem);
        }

        cart.clear();
    }

    /**
     * Резервация комиксов
     *
     * @param cart     - корзина комиксов
     * @param customer - имя клиента
     */
    public void reservationComics(Cart cart, String customer) {
        for (CartItem comic : cart.getComics()) {
            deleteComic(comic.getComic().getName());
        }

        ReservationDao reservationDao = new ReservationDao();
        for (CartItem comic : cart.getComics()) {
            String buyItem = LocalDateTime.now() + DELIMITER + comic.getComic().getName() + DELIMITER + customer;
            reservationDao.saveToFile(buyItem);
        }

        cart.clear();
    }

    /**
     * Запись скидок
     *
     * @param name      - наименование акции
     * @param percent   - процент скидки
     * @param dateBegin - дата начала
     * @param dateEnd   - дата окончания
     * @param cart      - корзина с комиксами
     */
    public void setDiscounts(String name,
                             String percent,
                             String dateBegin,
                             String dateEnd,
                             Cart cart) {
        for (CartItem comic : cart.getComics()) {
            deleteComic(comic.getComic().getName());
        }

        DiscountDao discountDao = new DiscountDao();
        for (CartItem comic : cart.getComics()) {
            String discount = name + DELIMITER + percent + DELIMITER + dateBegin + DELIMITER + dateEnd + DELIMITER +
                    comic.getComic().getName() + DELIMITER + comic.getPrice();

            discountDao.saveToFile(discount);
        }

        cart.clear();
    }

    private StringBuilder formComicFromElements(String[] comic) {
        StringBuilder data = new StringBuilder();
        data
                .append(comic[0]).append(DELIMITER) // наименование
                .append(comic[1]).append(DELIMITER) // автор
                .append(comic[2]).append(DELIMITER) // издательство
                .append(comic[3]).append(DELIMITER) // количество страниц
                .append(comic[4]).append(DELIMITER) // жанр
                .append(comic[5]).append(DELIMITER) // год издательства
                .append(comic[6]).append(DELIMITER) // себестоимость
                .append(comic[7]).append(DELIMITER) // цена продажи
                .append(comic[8]).append(DELIMITER); // является продолжением
        return data;
    }
}

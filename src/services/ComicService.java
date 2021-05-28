package services;

import domain.Comic;
import domain.sale.Cart;
import domain.sale.Sell;
import repository.FileDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
    public void addComic(Comic comic) {
        fileDao.saveToFile(formComicFromElements(comic).toString());
    }

    /**
     * Удаление комикса
     *
     * @param nameOfComic название комикса
     */
    public void deleteComic(String nameOfComic) {
        List<String> comicsList = fileDao.readFromFile();

        fileDao.deleteFile();

        if (comicsList.size() > 0) {
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
     * Редактирование комикса
     *
     * @param nameOfComic    название комикса
     * @param elementOfComic редактируемый элемент комикса
     * @param newElement     новый элемент
     */
    public void editComic(String nameOfComic, int elementOfComic, String newElement) {
        List<String> comicsList = fileDao.readFromFile();
        if (comicsList.size() > 0) {
            for (String comic : comicsList) {
                String[] arrayOfElementsOfComic = comic.split(DELIMITER);
                boolean wasEditing = false;
                if (arrayOfElementsOfComic[0].equals(nameOfComic)) {
                    for (int i = 0; i < arrayOfElementsOfComic.length; i++) {
                        String element = arrayOfElementsOfComic[i];
                        if (i == elementOfComic - 1) {
                            comic += newElement + DELIMITER;
                            wasEditing = true;
                        } else {
                            comic += element + DELIMITER;
                        }
                    }
                }
                if (wasEditing) {
                    String[] arrayOfElements = comic.split(DELIMITER);
                    comic = "";
                    for (int i = 9; i < arrayOfElements.length; i++) {
                        comic += arrayOfElements[i] + DELIMITER;
                    }
                    fileDao.saveToFile(comic);
                }
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

    private StringBuilder formComicFromElements(Comic comic) {
        StringBuilder data = new StringBuilder();
        data
                .append(comic.getName()).append(DELIMITER)
                .append(comic.getAuthor().getName()).append(DELIMITER)
                .append(comic.getPublishing().getName()).append(DELIMITER)
                .append(comic.getNumberOfPages()).append(DELIMITER)
                .append(comic.getGenre().getName()).append(DELIMITER)
                .append(comic.getYearOfPublishing()).append(DELIMITER)
                .append(comic.isContinuation()).append(DELIMITER).append("\n");
        return data;
    }
}

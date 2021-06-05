package services;

import domain.Author;
import domain.Comic;
import domain.Genre;
import domain.Publishing;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.FileDao;

import java.util.List;

public class SearchService {

    private static final String DELIMITER = ";";

    private FileDao fileDao = new FileDao();
    private Cart cart = new Cart();

    /**
     * Получить все комиксы
     *
     * @return список комиксов в форме элемента корзины
     */
    public ObservableList<CartItem> getAllComics() {
        List<String> comics = fileDao.readFromFile();
        for (String comic : comics) {
            String[] elementsOfComic = comic.split(DELIMITER);
            cart.addComic(new Comic(
                    elementsOfComic[0], // наименование
                    new Author(elementsOfComic[1]), // автор
                    new Publishing(elementsOfComic[2]), // издательство
                    Integer.parseInt(elementsOfComic[3]), // количество страниц
                    new Genre(elementsOfComic[4]), // жанр
                    Integer.parseInt(elementsOfComic[5]), // год издания
                    Double.parseDouble(elementsOfComic[6]), // себестоимость
                    Double.parseDouble(elementsOfComic[7]), // цена продажи
                    Boolean.parseBoolean(elementsOfComic[8]) // является ли продолжением
            ));
        }
        return FXCollections.observableArrayList(cart.getComics());
    }

    /**
     * Получение комикса по наименованию
     *
     * @param nameOfComic - наименование комикса
     * @return - комикс, если найден в файле
     */
    public Comic getComicByName(String nameOfComic) {
        return fileDao.getComicByName(nameOfComic);
    }
}

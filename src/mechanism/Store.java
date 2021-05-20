package mechanism;

/**
 * Магазин или склад или хранилище комиксов. Содержит основные функции
 */
public class Store {

    private DataBase dataBase = new DataBase();

    public void sellComic(String nameOfComic, int numberOfComics) {
        dataBase.sellComic(nameOfComic, numberOfComics);
    }
}

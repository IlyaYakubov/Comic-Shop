package mechanism;

import models.Comic;

import java.io.*;

public class WorkWithComic {

    private static final DataBase DATA_BASE = new DataBase();

    /**
     * Добавление комикса
     * @param comic комикс
     * @throws IOException исключение ввода/вывода
     */
    public void addComic(Comic comic) throws IOException {
        DATA_BASE.add(comic);
    }

    /**
     * Удаление комикса
     * @param nameOfComic название комикса
     */
    public void deleteComic(String nameOfComic) {
        DATA_BASE.delete(nameOfComic);
    }

    /**
     * Редактирование комикса
     * @param nameOfComic название комикса
     * @param elementOfComic редактируемый элемент комикса
     * @param newElement новый элемент
     */
    public void editComic(String nameOfComic, int elementOfComic, String newElement) {
        DATA_BASE.edit(nameOfComic, elementOfComic, newElement);
    }
}

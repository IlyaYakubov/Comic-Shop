package mechanism;

import ui.utils.Util;

import java.io.IOException;

import static mechanism.KeyboardHelper.CONSOLE_READER;

/**
 * Магазин или склад или хранилище комиксов. Содержит основные функции
 */
public class Store {

    /**
     * Метод позволяет добавлять комикс в магазин
     * @throws IOException сигнал о том, что произошло какое-то исключение ввода-вывода
     */
    public void add() throws IOException {
        showMessage("Введите название комикса: ");
        String name = CONSOLE_READER.readLine();
        showMessage("Введите ФИО художника/автора: ");
        String author = CONSOLE_READER.readLine();
        showMessage("Введите название издательства комикса: ");
        String publishing = CONSOLE_READER.readLine();
        showMessage("Введите количество страниц: ");
        int numberOfPages;
        while (true) {
            try {
                numberOfPages = Integer.parseInt(CONSOLE_READER.readLine());
                break;
            } catch (NumberFormatException e) {
                showMessage("Вы ввели не число");
            }
        }
        showMessage("Введите жанр: ");
        String genre = CONSOLE_READER.readLine();
        showMessage("Введите год издания: ");
        int yearOfPublishing;
        while (true) {
            try {
                yearOfPublishing = Integer.parseInt(CONSOLE_READER.readLine());
                break;
            } catch (NumberFormatException e) {
                showMessage("Вы ввели не число");
            }
        }
        showMessage("Введите себестоимость: ");
        double costPrice;
        while (true) {
            try {
                costPrice = Double.parseDouble(CONSOLE_READER.readLine());
                break;
            } catch (NumberFormatException e) {
                showMessage("Вы ввели не число");
            }
        }
        showMessage("Введите цену для продажи: ");
        double sellingPrice;
        while (true) {
            try {
                sellingPrice = Double.parseDouble(CONSOLE_READER.readLine());
                break;
            } catch (NumberFormatException e) {
                showMessage("Вы ввели не число");
            }
        }
        showMessage("Является ли комикс продолжением другого комикса или серии (true / false) ? ");
        boolean isContinuation = Boolean.parseBoolean(CONSOLE_READER.readLine());

        Comic comic = new Comic(name, author, publishing, numberOfPages, genre,
                yearOfPublishing, costPrice, sellingPrice, isContinuation);

        BaseData baseData = new BaseData();
        baseData.saveInFileSystem(comic);
    }

    private void showMessage(String message) {
        Util.printMessage(message);
    }
}

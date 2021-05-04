package mechanism;

import mechanism.basedata.BaseData;
import mechanism.basedata.BaseDataException;
import ui.IFunctions;
import ui.Menu;
import ui.utils.Util;

import java.io.IOException;

import static mechanism.KeyboardHelper.CONSOLE_READER;

/**
 * Магазин или склад или хранилище комиксов. Содержит основные функции
 */
public class Store implements IFunctions {

    private BaseData baseData = new BaseData();

    /**
     * Метод позволяет добавлять комикс в магазин
     *
     * @throws IOException сигнал о том, что произошло какое-то исключение ввода-вывода
     */
    @Override
    public void addElement() throws IOException {
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

        baseData.saveInFileSystem(comic);
    }

    /**
     * Позволяет удалить комикс
     */
    @Override
    public void deleteElement() {
        showMessage("Введите название комикса, который требуется удалить");
        try {
            String name = CONSOLE_READER.readLine();
            baseData.deleteElement(name);
            showMessage("Комикс удален");
        } catch (IOException | BaseDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Позволяет отредактировать параметры комикса
     */
    @Override
    public void editElement() {
        showMessage("Введите название комикса, который требуется редактировать");
        try {
            String element = CONSOLE_READER.readLine();
            Menu menu = new Menu();
            menu.showEditMenu(element);
            showMessage("---------- Готово ----------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Позволяет отредактировать параметры комикса в базе данных (файле)
     */
    public void editElementInBaseData(String name, int element) {
        try {
            baseData.editElement(name, element);
        } catch (BaseDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сообщение пользователю
     * @param message сообщение - строка
     */
    private void showMessage(String message) {
        Util.printMessage(message);
    }
}

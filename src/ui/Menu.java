package ui;

import mechanism.Comic;
import mechanism.Store;
import ui.utils.Util;

import java.io.IOException;

import static mechanism.utils.KeyboardHelper.CONSOLE_READER;

/**
 * Класс взаимодействия пользователя с программой
 */
public class Menu {

    private static final StringBuilder TEXT = new StringBuilder();
    private static final Store STORE = new Store();

    private static final int SERIAL_NUMBER_NAME = 1;
    private static final int SERIAL_NUMBER_AUTHOR = 2;
    private static final int SERIAL_NUMBER_PUBLISHING = 3;
    private static final int SERIAL_NUMBER_NUMBER_OF_PAGES = 4;
    private static final int SERIAL_NUMBER_GENRE = 5;
    private static final int SERIAL_NUMBER_YEAR = 6;
    private static final int SERIAL_NUMBER_COST_PRICE = 7;
    private static final int SERIAL_NUMBER_SALE_PRICE = 8;
    private static final int SERIAL_NUMBER_IS_CONTINUATION = 9;

    /**
     * Сообщение пользователю
     *
     * @param message - строка
     */
    public void showMessage(String message) {
        Util.printMessage(message);
    }

    /**
     * Завершение работы программы
     */
    private void exitProgram() {
        showMessage("---------- До скорой встречи в программе! ----------");
        System.exit(0);
    }

    /**
     * Отображает пункты меню и просит пользователя ввести данные
     */
    public void showMainFunctions() {
        TEXT.append("1 - добавить комикс\n");
        TEXT.append("2 - удалить комикс\n");
        TEXT.append("3 - редактировать данные комикса\n");
        TEXT.append("4 - продать комикс\n");
        TEXT.append("0 - выход из приложения\n");

        Util.printMessage(TEXT);

        enteringMenuItem();
    }

    /**
     * Метод ввода пунктов меню
     */
    private void enteringMenuItem() {
        try {
            String userOption = CONSOLE_READER.readLine();
            switch (userOption) {
                case "1" -> {
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

                    STORE.addComic(new Comic(name, author, publishing, numberOfPages, genre,
                            yearOfPublishing, costPrice, sellingPrice, isContinuation));
                }
                case "2" -> {
                    showMessage("Введите название комикса, который требуется удалить");
                    String element = getElementFromUser();
                    STORE.deleteComic(element);
                }
                case "3" -> {
                    Util.printMessage("Введите название комикса, который требуется редактировать");
                    String element = getElementFromUser();
                    showEditMenu(element);
                }
                case "0" -> exitProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        showMainFunctions();
    }

    /**
     * Отображает пункты меню редактирования и просит пользователя ввести пункт меню
     */
    public void showEditMenu(String name) {
        TEXT.append("любой символ - отмена\n").append("1 - название\n").append("2 - автор\n")
                .append("3 - издательство\n").append("4 - количество страниц\n")
                .append("5 - жанр\n").append("6 - год публикации\n")
                .append("7 - себестоимость\n").append("8 - цена продажи\n")
                .append("9 - является ли продолжением серии\n");

        Util.printMessage(TEXT);

        enteringEditMenuItem(name);
    }

    /**
     * В зависимости от выбранного элемнта редактирования передается нужный аргумент
     *
     * @param name имя редактируемого комикса
     */
    private void enteringEditMenuItem(String name) {
        try {
            String userOption = CONSOLE_READER.readLine();
            switch (userOption) {
                case "1" -> {
                    showMessage("Введите новое имя");
                    STORE.editComic(name, SERIAL_NUMBER_NAME, CONSOLE_READER.readLine());
                }
                case "2" -> {
                    showMessage("Введите ФИО нового автора");
                    STORE.editComic(name, SERIAL_NUMBER_AUTHOR, CONSOLE_READER.readLine());
                }
                case "3" -> {
                    showMessage("Введите новое название издания");
                    STORE.editComic(name, SERIAL_NUMBER_PUBLISHING, CONSOLE_READER.readLine());
                }
                case "4" -> {
                    showMessage("Введите новое количество страниц");
                    STORE.editComic(name, SERIAL_NUMBER_NUMBER_OF_PAGES, CONSOLE_READER.readLine());
                }
                case "5" -> {
                    showMessage("Введите название нового жанра");
                    STORE.editComic(name, SERIAL_NUMBER_GENRE, CONSOLE_READER.readLine());
                }
                case "6" -> {
                    showMessage("Введите новый год выпуска");
                    STORE.editComic(name, SERIAL_NUMBER_YEAR, CONSOLE_READER.readLine());
                }
                case "7" -> {
                    showMessage("Введите новую себестоимость");
                    STORE.editComic(name, SERIAL_NUMBER_COST_PRICE, CONSOLE_READER.readLine());
                }
                case "8" -> {
                    showMessage("Введите новую цену продажи");
                    STORE.editComic(name, SERIAL_NUMBER_SALE_PRICE, CONSOLE_READER.readLine());
                }
                case "9" -> {
                    showMessage("Введите \"true\" если комикс всё же является продолжением");
                    STORE.editComic(name, SERIAL_NUMBER_IS_CONTINUATION, CONSOLE_READER.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает значение элемента комикса, введенное пользователем (продавцом) магазина
     *
     * @return элемент (год, издание, автор, жанр и т.д.)
     * @throws IOException - в случае когда с потоком ввода-вывода что-то пошло не так
     */
    private String getElementFromUser() throws IOException {
        String element;
        do {
            element = CONSOLE_READER.readLine();
        } while (element.isEmpty());
        return element;
    }
}

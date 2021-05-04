package ui;

import mechanism.Store;
import ui.utils.Util;

import java.io.IOException;

import static mechanism.KeyboardHelper.CONSOLE_READER;

/**
 * Класс взаимодействия пользователя с программой
 */
public class Menu {

    private static final StringBuilder TEXT = new StringBuilder();
    private static final Store STORE = new Store();

    /**
     * Приветствие программы
     */
    public void greetingMessage() {
        Util.printMessage("---------- Вас приветствует программа \"Магазин комиксов\" ----------");
    }

    /**
     * Завершение программы
     */
    public void goodBayMessage() {
        Util.printMessage("---------- До скорой встречи в программе! ----------");
    }

    /**
     * Отображает пункты меню и просит пользователя ввести данные
     */
    public void show() {
        TEXT.append("1 - добавить комикс\n");
        TEXT.append("2 - удалить комикс\n");
        TEXT.append("3 - редактировать данные комикса\n");
        TEXT.append("0 - выход из приложения\n");

        Util.printMessage(TEXT);

        input();
    }

    /**
     * Метод ввода пунктов меню
     */
    private void input() {
        try {
            String userOption = CONSOLE_READER.readLine();
            switch (userOption) {
                case "1" -> STORE.addElement();
                case "2" -> STORE.deleteElement();
                case "3" -> STORE.editElement();
                case "0" -> exitProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        show();
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

        inputEdit(name);
    }

    /**
     * В зависимости от выбранного элемнта редактирования передается нужный аргумент
     * @param name имя редактируемого комикса
     */
    private void inputEdit(String name) {
        try {
            String userOption = CONSOLE_READER.readLine();
            switch (userOption) {
                case "1" -> STORE.editElementInBaseData(name, 1);
                case "2" -> STORE.editElementInBaseData(name, 2);
                case "3" -> STORE.editElementInBaseData(name, 3);
                case "4" -> STORE.editElementInBaseData(name, 4);
                case "5" -> STORE.editElementInBaseData(name, 5);
                case "6" -> STORE.editElementInBaseData(name, 6);
                case "7" -> STORE.editElementInBaseData(name, 7);
                case "8" -> STORE.editElementInBaseData(name, 8);
                case "9" -> STORE.editElementInBaseData(name, 9);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Завершение работы программы
     */
    private void exitProgram() {
        goodBayMessage();
        System.exit(0);
    }
}

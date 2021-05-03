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
        TEXT.append("0 - выход из приложения\n");

        Util.printMessage(TEXT);
        TEXT.delete(0, TEXT.length() - 1);

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
                case "0" -> exitProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        show();
    }

    /**
     * Завершение работы программы
     */
    private void exitProgram() {
        goodBayMessage();
        System.exit(0);
    }
}

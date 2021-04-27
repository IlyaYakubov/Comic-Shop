package ui;

import mechanism.Store;
import ui.utils.Util;

import java.io.IOException;

import static mechanism.KeyboardHelper.CONSOLE_READER;

/**
 * Класс взаимодействия пользователя с программой
 */
public class Menu implements IMenu {

    private static final StringBuilder TEXT = new StringBuilder();

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
    @Override
    public void show() {
        TEXT.append("1 - добавить комикс\n");
        Util.printMessage(TEXT);
        input();
    }

    /**
     * Метод ввода пунктов меню
     */
    private void input() {
        try {
            String userOption = CONSOLE_READER.readLine();
            if (userOption.equals("1")) {
                Store store = new Store();
                store.add();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

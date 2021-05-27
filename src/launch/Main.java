package launch;

import ui.MainUI;
import ui.utils.Util;

/**
 * Основной класс
 */
public class Main {
    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
        Util.printMessage("---------- Вас приветствует программа \"Магазин комиксов\" ----------");
        mainUI.showMainFunctions();
    }
}

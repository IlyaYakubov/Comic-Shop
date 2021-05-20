package launch;

import ui.Menu;
import ui.utils.Util;

/**
 * Основной класс
 */
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Util.printMessage("---------- Вас приветствует программа \"Магазин комиксов\" ----------");
        menu.showMainFunctions();
    }
}

package launch;

import ui.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.showMessage("---------- Вас приветствует программа \"Магазин комиксов\" ----------");
        menu.showMainFunctions();
    }
}

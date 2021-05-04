package ui.utils;

/**
 * Вспомогательные методы для работы с консолью
 */
public class Util {

    /**
     * Печатает сообщение
     *
     * @param message простая строка для вывода в консоль
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Печатает сообщение
     *
     * @param message собранная строка типа StringBuilder для вывода в консоль
     */
    public static void printMessage(StringBuilder message) {
        System.out.println(message);
        message.delete(0, message.length() - 1);
    }
}

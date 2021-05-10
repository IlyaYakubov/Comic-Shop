package ui.utils;

/**
 * Вспомогательные методы для работы с консолью
 */
public class Util {

    /**
     * Класс содержит только статические методы
     */
    private Util() {
    }

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
        System.out.print(message);
        message.delete(0, message.length() - 1);
    }
}

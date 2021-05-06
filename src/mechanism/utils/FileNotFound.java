package mechanism.utils;

/**
 * Исключение базы данных магазина
 */
public class FileNotFound extends Exception {

    public FileNotFound(String message) {
        super(message);
    }
}

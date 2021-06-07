package repository;

import java.io.*;

/**
 * Работа с файлом акций
 */
public class DiscountDao {

    private static final String FILE_NAME_DISCOUNTS = "discounts.txt";
    private static final File FILE_WITH_DISCOUNTS;

    static {
        FILE_WITH_DISCOUNTS = new File(FILE_NAME_DISCOUNTS);
        if (!FILE_WITH_DISCOUNTS.exists()) {
            try {
                FILE_WITH_DISCOUNTS.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запись акции в файл
     *
     * @param discount - акция
     */
    public void saveToFile(String discount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_DISCOUNTS, true))) {
            writer.write(discount + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package repository;

import domain.sell.Cart;
import domain.sell.CartItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Работа с файлом продажи
 */
public class SellDao {

    private static final String FILE_NAME_SELL = "selling.txt";
    private static final File FILE_WITH_SELL;

    static {
        FILE_WITH_SELL = new File(FILE_NAME_SELL);
        if (!FILE_WITH_SELL.exists()) {
            try {
                FILE_WITH_SELL.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запись продажи в файл
     * @param sell - продажа
     */
    public void saveToFile(String sell) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_SELL, true))) {
            writer.write(sell + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

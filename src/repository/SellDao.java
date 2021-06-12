package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Работа с файлом продажи
 */
public class SellDao {

    private static final String FILE_NAME_SELL = "sales.txt";
    private static final File FILE_WITH_SELL;
    public static SellDao INSTANCE = new SellDao();

    static {
        FILE_WITH_SELL = new File(FILE_NAME_SELL);
        if (!FILE_WITH_SELL.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                FILE_WITH_SELL.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private SellDao() {
    }

    /**
     * Запись продажи в файл
     *
     * @param sell продажа
     */
    public void saveToFile(String sell) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_SELL, true))) {
            writer.write(sell + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Чтение строки (элементов) комикса
     *
     * @return коллекция комиксов
     */
    public List<String> readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_WITH_SELL))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

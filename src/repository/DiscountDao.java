package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Работа с файлом акций
 */
public class DiscountDao {

    public static DiscountDao INSTANCE = new DiscountDao();
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

    private DiscountDao() {
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

    /**
     * Чтение строки комикса с элементами акции
     *
     * @return - строка акции комикса
     */
    public List<String> readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME_DISCOUNTS))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

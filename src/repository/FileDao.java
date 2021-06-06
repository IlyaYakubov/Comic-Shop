package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Работа с файлом
 */
public class FileDao {

    private static final String FILE_NAME_COMICS = "comics.txt";
    private static final File FILE_WITH_COMICS;

    static {
        FILE_WITH_COMICS = new File(FILE_NAME_COMICS);
        if (!FILE_WITH_COMICS.exists()) {
            try {
                FILE_WITH_COMICS.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запись комикса в файл
     *
     * @param comic - комикс из элементов
     */
    public void saveToFile(String comic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_COMICS, true))) {
            writer.write(comic + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Чтение строки (элементов) комикса
     *
     * @return - коллекция комиксов
     */
    public List<String> readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME_COMICS))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Удаление файла
     */
    public void deleteFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FILE_WITH_COMICS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.print("");
        writer.close();
    }
}

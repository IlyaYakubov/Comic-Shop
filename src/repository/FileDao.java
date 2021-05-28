package repository;

import domain.Author;
import domain.Comic;
import domain.Genre;
import domain.Publishing;

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
     * @param comic комикс из элементов
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
     * @return коллекцию комиксов
     */
    public List<String> readFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME_COMICS));
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (FileNotFoundException e) {
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

    public Comic getComicByName(String comicName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WITH_COMICS))) {
            List<String> comicsWithElements = reader.lines().collect(Collectors.toList());
            for (String desiredComic : comicsWithElements) {
                String[] elementsOfComic = desiredComic.split(";");
                if (elementsOfComic[0].equals(comicName)) {
                    Comic comic = new Comic(elementsOfComic[0], new Author(elementsOfComic[1])
                            , new Publishing(elementsOfComic[2]), Integer.parseInt(elementsOfComic[3])
                            , new Genre(elementsOfComic[4]), Integer.parseInt(elementsOfComic[5])
                            , Double.parseDouble(elementsOfComic[6]), Double.parseDouble(elementsOfComic[7])
                            , Boolean.parseBoolean(elementsOfComic[8]));
                    return comic;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

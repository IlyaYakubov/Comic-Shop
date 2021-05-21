package mechanism;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Парсер файлов с данными
 */
public class DataBase {

    private static final String FILE_NAME_COMICS = "Comics.txt";
    private static final File FILE_WITH_COMICS;
    private static final String DELIMITER = ";";

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
     * Добавление
     * @param comic комикс
     * @throws IOException исключение ввода/вывода
     */
    public void add(Comic comic) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_COMICS, true))) {
            writer.write(formComicFromElements(comic).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление
     * @param nameOfComic наименование комикса
     */
    public void delete(String nameOfComic) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WITH_COMICS))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] arrayOfValues = line.split(DELIMITER);
                if (arrayOfValues[0].equals(nameOfComic)) {
                    continue;
                }
                list.add(line);
            }

            writeComicsElements(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Редакитирование
     * @param nameOfComic наименование комикса
     * @param elementOfComic элемент комикса
     * @param newElement отредактированный элемент
     */
    public void edit(String nameOfComic, int elementOfComic, String newElement) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WITH_COMICS))) {
            String elements;
            List<String> listOfElements = new ArrayList<>();
            while ((elements = reader.readLine()) != null) {
                String[] arrayOfElementsOfComic = elements.split(DELIMITER);
                boolean wasEditing = false;
                if (arrayOfElementsOfComic[0].equals(nameOfComic)) {
                    for (int i = 0; i < arrayOfElementsOfComic.length; i++) {
                        String element = arrayOfElementsOfComic[i];
                        if (i == elementOfComic - 1) {
                            elements += newElement + DELIMITER;
                            wasEditing = true;
                        } else {
                            elements += element + DELIMITER;
                        }
                    }
                }
                if (wasEditing) {
                    String[] arrayOfElements = elements.split(DELIMITER);
                    elements = "";
                    for (int i = 9; i < arrayOfElements.length; i++) {
                        elements += arrayOfElements[i] + DELIMITER;
                    }
                }
                listOfElements.add(elements);
            }

            writeComicsElements(listOfElements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private StringBuilder formComicFromElements(Comic comic) {
        StringBuilder data = new StringBuilder();
        data
                .append(comic.getName()).append(DELIMITER)
                .append(comic.getAuthor()).append(DELIMITER)
                .append(comic.getPublishing()).append(DELIMITER)
                .append(comic.getNumberOfPages()).append(DELIMITER)
                .append(comic.getGenre()).append(DELIMITER)
                .append(comic.getYearOfPublishing()).append(DELIMITER)
                .append(comic.getCostPrice()).append(DELIMITER)
                .append(comic.getSellingPrice()).append(DELIMITER)
                .append(comic.isContinuation()).append(DELIMITER).append("\n");
        return data;
    }

    private void writeComicsElements(List<String> listOfElements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_COMICS, false))) {
            for (String element : listOfElements) {
                writer.write(element + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

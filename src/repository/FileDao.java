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
     *
     * @param comic комикс
     */
    public void add(Comic comic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_COMICS, true))) {
            writer.write(formComicFromElements(comic).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Удаление
     *
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
     *
     * @param nameOfComic    наименование комикса
     * @param elementOfComic элемент комикса
     * @param newElement     отредактированный элемент
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

    /**
     * Запрос, возвращающий все комиксы из файла
     * @return список комиксов, как объектов
     */
    public List<Comic> getAllComics() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WITH_COMICS))) {
            List<Comic> comics = new ArrayList<>();
            List<String> comicsWithElements = reader.lines().collect(Collectors.toList());
            for (String comic : comicsWithElements) {
                String[] elementsOfComic = comic.split(DELIMITER);
                comics.add(new Comic(elementsOfComic[0], new Author(elementsOfComic[1])
                        , new Publishing(elementsOfComic[2]), Integer.parseInt(elementsOfComic[3])
                        , new Genre(elementsOfComic[4]), Integer.parseInt(elementsOfComic[5])
                        , Double.parseDouble(elementsOfComic[6]), Double.parseDouble(elementsOfComic[7])
                        , Boolean.parseBoolean(elementsOfComic[8])));
            }
            return comics;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private StringBuilder formComicFromElements(Comic comic) {
        StringBuilder data = new StringBuilder();
        data
                .append(comic.getName()).append(DELIMITER)
                .append(comic.getAuthor().getName()).append(DELIMITER)
                .append(comic.getPublishing().getName()).append(DELIMITER)
                .append(comic.getNumberOfPages()).append(DELIMITER)
                .append(comic.getGenre().getName()).append(DELIMITER)
                .append(comic.getYearOfPublishing()).append(DELIMITER)
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

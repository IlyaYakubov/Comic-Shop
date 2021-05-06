package mechanism;

import ui.IFunctions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Магазин или склад или хранилище комиксов. Содержит основные функции
 */
public class Store implements IFunctions {

    private static final String FILE_NAME = "BaseData.txt";
    private static final File FILE_WITH_COMICS;

    static {
        FILE_WITH_COMICS = new File(FILE_NAME);
        if (!FILE_WITH_COMICS.exists()) {
            try {
                FILE_WITH_COMICS.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод позволяет добавлять комикс в магазин
     *
     * @throws IOException сигнал о том, что произошло какое-то исключение ввода-вывода
     */
    @Override
    public void addComic(Comic comic) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_COMICS, true))) {
            writer.write(comic.getAllData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод позволяет удалить комикс из магазина
     */
    @Override
    public void deleteComic(String nameOfComic) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WITH_COMICS))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] arrayOfValues = line.split(";");
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
     * Метод позволяет отредактировать элементы комикса в магазине
     */
    @Override
    public void editComic(String nameOfComic, int elementOfComic, String newElement) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_WITH_COMICS))) {
            String elements;
            List<String> listOfElements = new ArrayList<>();
            while ((elements = reader.readLine()) != null) {
                String[] arrayOfElementsOfComic = elements.split(";");
                boolean wasEditing = false;
                // для поиска комикса, который будет отредактирован
                if (arrayOfElementsOfComic[0].equals(nameOfComic)) {
                    for (int i = 0; i < arrayOfElementsOfComic.length; i++) {
                        String element = arrayOfElementsOfComic[i];
                        if (i == elementOfComic - 1) {
                            elements += newElement + ";";
                            wasEditing = true;
                        } else {
                            elements += element + ";";
                        }
                    }
                }
                if (wasEditing) {
                    String[] arrayOfElements = elements.split(";");
                    elements = "";
                    for (int i = 9; i < arrayOfElements.length; i++) {
                        elements += arrayOfElements[i] + ";";
                    }
                }
                listOfElements.add(elements);
            }

            writeComicsElements(listOfElements);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

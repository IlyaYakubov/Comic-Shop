package mechanism.basedata;

import mechanism.Comic;
import ui.utils.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static mechanism.KeyboardHelper.CONSOLE_READER;

/**
 * Основная база данных, позволяющая сохранять удалять редактировать и загружать данные из файла.
 */
public class BaseData {

    private static final String FILE_NAME = "BaseData.txt";

    /**
     * Сохранение комикса в файловой системе для хранения
     *
     * @param comic комикс, который сохранится в файле
     */
    public void saveInFileSystem(Comic comic) {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            saveInFile(file, comic);
        } else {
            try {
                if (file.createNewFile()) {
                    saveInFile(file, comic);
                } else {
                    throw new IOException("Не возможно создать базу данных");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Поиск и удаление комикса из файла
     *
     * @param name название комикса
     */
    public void deleteElement(String name) throws BaseDataException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            deleteInFile(file, name);
        } else {
            throw new BaseDataException("Не найдена база данных");
        }
    }

    /**
     * Поиск и редактирование комикса в файле
     *
     * @param name название комикса
     */
    public void editElement(String name, int element) throws BaseDataException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            switch (element) {
                case 1 -> Util.printMessage("Введите новое имя:");
                case 2 -> Util.printMessage("Введите ФИО нового автора:");
                case 3 -> Util.printMessage("Введите новое название издания:");
                case 4 -> Util.printMessage("Введите новое количество страниц:");
                case 5 -> Util.printMessage("Введите название нового жанра:");
                case 6 -> Util.printMessage("Введите новый год выпуска:");
                case 7 -> Util.printMessage("Введите новую себестоимость:");
                case 8 -> Util.printMessage("Введите новую цену продажи:");
                case 9 -> Util.printMessage("Введите \"true\" если комикс всё же является продолжением:");
            }
            try {
                String value = CONSOLE_READER.readLine();
                editInFile(file, name, element, value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new BaseDataException("Не найдена база данных");
        }
    }

    private void saveInFile(File file, Comic comic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(comic.getAllData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteInFile(File file, String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] arrayOfValues = line.split(";");
                if (arrayOfValues[0].equals(name)) {
                    continue;
                }
                list.add(line);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (String element : list) {
                    writer.write(element + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editInFile(File file, String name, int option, String value) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            List<String> list = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] arrayOfValues = line.split(";");
                boolean wasEditing = false;
                if (arrayOfValues[0].equals(name)) {
                    for (int i = 0; i < arrayOfValues.length; i++) {
                        String arrayValue = arrayOfValues[i];
                        if (i == option - 1) {
                            line += value + ";";
                            wasEditing = true;
                        } else {
                            line += arrayValue + ";";
                        }
                    }
                }
                if (wasEditing) {
                    String[] array = line.split(";");
                    line = "";
                    for (int i = 9; i < array.length; i++) {
                        line += array[i] + ";";
                    }
                }
                list.add(line);
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                for (String element : list) {
                    writer.write(element + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

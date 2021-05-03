package mechanism.basedata;

import mechanism.Comic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
}

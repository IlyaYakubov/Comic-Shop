package mechanism;

import java.io.*;

/**
 * Основная база данных, позволяющая сохранять удалять редактировать и загружать данные из файла.
 */
public class BaseData {

    private static final String FILE_NAME = "BaseData.txt";

    /**
     * Сохранение комикса в файловой системе для хранения
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

    private void saveInFile(File file, Comic comic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(comic.getAllData().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

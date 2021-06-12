package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Работа с файлом списания
 */
public class WriteOffDao {

    private static final String FILE_NAME_WRITE_OFF = "writeoffed.txt";
    private static final File FILE_WITH_WRITE_OFF;
    public static WriteOffDao INSTANCE = new WriteOffDao();

    static {
        FILE_WITH_WRITE_OFF = new File(FILE_NAME_WRITE_OFF);
        if (!FILE_WITH_WRITE_OFF.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                FILE_WITH_WRITE_OFF.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private WriteOffDao() {
    }

    /**
     * Запись списания в файл
     *
     * @param writeOff списанный комикс
     */
    public void saveToFile(String writeOff) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_WRITE_OFF, true))) {
            writer.write(writeOff + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Работа с файлом резервирования
 */
public class ReservationDao {

    private static final String FILE_NAME_RESERVATION = "reservation_comics.txt";
    private static final File FILE_WITH_RESERVATION;

    static {
        FILE_WITH_RESERVATION = new File(FILE_NAME_RESERVATION);
        if (!FILE_WITH_RESERVATION.exists()) {
            try {
                FILE_WITH_RESERVATION.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запись резервирования в файл
     *
     * @param reservation - зарезервированный комикс
     */
    public void saveToFile(String reservation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_RESERVATION, true))) {
            writer.write(reservation + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Работа с файлом резервирования
 */
public class ReservationDao {

    public static ReservationDao INSTANCE = new ReservationDao();
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

    private ReservationDao() {
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

    /**
     * Чтение резервированных комиксов
     *
     * @return - строка резервации комикса
     */
    public List<String> readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_WITH_RESERVATION))) {
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
            writer = new PrintWriter(FILE_WITH_RESERVATION);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.print("");
        writer.close();
    }
}

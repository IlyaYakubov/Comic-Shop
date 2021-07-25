package repository;

/**
 * Работа с файлом
 */
public class ReservationDao extends FileDao implements IFileDao {

    public static ReservationDao INSTANCE = new ReservationDao("reservations.txt");

    private ReservationDao(String fileName) {
        super(fileName);
    }
}

package repositories;

/**
 * Работа с файлом
 */
public class ReservationDAO extends FileDAOImpl implements FileDAO {

    public static ReservationDAO INSTANCE = new ReservationDAO("reservations.txt");

    private ReservationDAO(String fileName) {
        super(fileName);
    }
}

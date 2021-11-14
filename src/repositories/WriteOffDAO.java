package repositories;

/**
 * Работа с файлом
 */
public class WriteOffDAO extends FileDAOImpl implements FileDAO {

    public static WriteOffDAO INSTANCE = new WriteOffDAO("offs.txt");

    private WriteOffDAO(String fileName) {
        super(fileName);
    }
}

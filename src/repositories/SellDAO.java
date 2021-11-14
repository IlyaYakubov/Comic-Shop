package repositories;

/**
 * Работа с файлом
 */
public class SellDAO extends FileDAOImpl implements FileDAO {

    public static SellDAO INSTANCE = new SellDAO("sales.txt");

    private SellDAO(String fileName) {
        super(fileName);
    }
}

package repositories;

/**
 * Работа с файлом
 */
public class SaleDAO extends FileDAOImpl implements FileDAO {

    public static SaleDAO INSTANCE = new SaleDAO("sales.txt");

    private SaleDAO(String fileName) {
        super(fileName);
    }
}

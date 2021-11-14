package repositories;

/**
 * Работа с файлом
 */
public class DiscountDAO extends FileDAOImpl implements FileDAO {

    public static DiscountDAO INSTANCE = new DiscountDAO("discounts.txt");

    private DiscountDAO(String fileName) {
        super(fileName);
    }
}

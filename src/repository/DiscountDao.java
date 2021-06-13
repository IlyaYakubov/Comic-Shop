package repository;

/**
 * Работа с файлом
 */
public class DiscountDao extends ComicsDao implements IFileDao {

    public static DiscountDao INSTANCE = new DiscountDao("discounts.txt");

    private DiscountDao(String fileName) {
        super(fileName);
    }
}

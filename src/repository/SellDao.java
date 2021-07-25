package repository;

/**
 * Работа с файлом
 */
public class SellDao extends FileDao implements IFileDao {

    public static SellDao INSTANCE = new SellDao("sales.txt");

    private SellDao(String fileName) {
        super(fileName);
    }
}

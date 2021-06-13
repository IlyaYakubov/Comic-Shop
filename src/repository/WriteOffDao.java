package repository;

/**
 * Работа с файлом
 */
public class WriteOffDao extends ComicsDao implements IFileDao {

    public static WriteOffDao INSTANCE = new WriteOffDao("offs.txt");

    private WriteOffDao(String fileName) {
        super(fileName);
    }
}

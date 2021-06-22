package repository;

/**
 * Работа с файлом
 */
public class WriteOffDao extends FileDao implements IFileDao {

    public static WriteOffDao INSTANCE = new WriteOffDao("offs.txt");

    private WriteOffDao(String fileName) {
        super(fileName);
    }
}

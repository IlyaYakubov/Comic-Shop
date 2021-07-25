package repository;

/**
 * Работа с файлом
 */
public class UserDao extends FileDao implements IFileDao {

    public static UserDao INSTANCE = new UserDao("users.txt");

    private UserDao(String fileName) {
        super(fileName);
    }
}

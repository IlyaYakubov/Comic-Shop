package repository;

/**
 * Работа с файлом
 */
public class UserDao extends ComicsDao implements IFileDao {

    public static UserDao INSTANCE = new UserDao("users.txt");

    private UserDao(String fileName) {
        super(fileName);
    }
}

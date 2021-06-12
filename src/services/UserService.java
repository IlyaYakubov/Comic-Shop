package services;

import repository.UserDao;

import java.util.List;

/**
 * Сервис по работе с пользователем
 */
public class UserService {

    private final UserDao USER_DAO = new UserDao();
    private final String NAME;
    private final String PASSWORD;

    public UserService(String name, String password) {
        NAME = name;
        PASSWORD = password;
    }

    /**
     * Сохранение пользователя и пароля в файл. В файле имя пользователя имеет четный индекс, а пароль - не четный
     */
    public void saveUser() {
        USER_DAO.saveToFile(NAME + "\n" + PASSWORD);
    }

    /**
     * Пользователь зарегистрирован
     *
     * @return true - в случае если пользователь найден
     */
    public boolean userInTheSystem() {
        List<String> users = USER_DAO.readFromFile();
        for (int i = 0; i < users.size() - 1; i++) {
            if (users.get(i).equals(NAME) && users.get(i + 1).equals(PASSWORD)) {
                return true;
            }
        }
        return false;
    }
}

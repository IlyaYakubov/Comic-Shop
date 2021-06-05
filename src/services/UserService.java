package services;

import repository.UserDao;

import java.util.List;

/**
 * Сервис по работе с пользователем
 */
public class UserService {

    private UserDao userDao = new UserDao();
    private String name;
    private String password;

    public UserService(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Сохранение пользователя и пароля в файл. В файле имя пользователя имеет четный индекс, а пароль - не четный
     */
    public void saveUser() {
        userDao.saveToFile(name + "\n" + password);
    }

    /**
     * Пользователь зарегистрирован
     *
     * @return - true в случае если пользователь найден
     */
    public boolean userInTheSystem() {
        List<String> users = userDao.readFromFile();
        for (int i = 0; i < users.size() - 1; i++) {
            if (users.get(i).equals(name) && users.get(i + 1).equals(password)) {
                return true;
            }
        }
        return false;
    }
}

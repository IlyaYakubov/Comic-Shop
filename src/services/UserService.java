package services;

import repository.UserDao;

/**
 * Сервис по работе с пользователем
 */
public class UserService {

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
        UserDao userDao = new UserDao();
        userDao.saveToFile(name + "\n" + password);
    }
}

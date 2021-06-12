package presenters;

import services.UserService;

/**
 * Контроллер регистрации
 */
public class RegistrationPresenter {

    private String name;
    private String password;

    public RegistrationPresenter(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Сохранение пользователя
     */
    public void saveUser() {
        UserService userService = new UserService(name, password);
        userService.saveUser();
    }
}

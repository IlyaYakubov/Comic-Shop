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

    public void saveUser() {
        UserService userService = new UserService(name, password);
        userService.saveUser();
    }
}

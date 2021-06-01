package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Работа с файлом зарегистрированных пользователей
 */
public class UserDao {

    private static final String FILE_NAME_USERS = "users.txt";
    private static final File FILE_WITH_USERS;

    static {
        FILE_WITH_USERS = new File(FILE_NAME_USERS);
        if (!FILE_WITH_USERS.exists()) {
            try {
                FILE_WITH_USERS.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Запись пользователя в файл
     * @param user - пользователь с паролем
     */
    public void saveToFile(String user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_WITH_USERS, true))) {
            writer.write(user + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Чтение файла с пользователями
     * @return - коллекция пользователей с паролями
     */
    public List<String> readFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_WITH_USERS))) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}

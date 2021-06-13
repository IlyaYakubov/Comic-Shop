package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Работа с файлом
 */
public class UserDao extends ComicsDao implements IFileDao {

    public static UserDao INSTANCE = new UserDao("users.txt");

    private UserDao(String fileName) {
        super(fileName);
    }
}

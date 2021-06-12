package repository;

import java.util.List;

/**
 * Контекст выполнения стратегий с файлами
 */
public class DomainsDaoClient {

    private IFileDao domainDao;

    public void setFileDao(IFileDao domainDao) {
        this.domainDao = domainDao;
    }

    /**
     * Сохранение данных в файл
     *
     * @param data данные
     */
    public void saveToFile(String data) {
        domainDao.saveToFile(data);
    }

    /**
     * Чтение данных из файла
     *
     * @return коллекция строк из файла
     */
    public List<String> readFromFile() {
        return domainDao.readFromFile();
    }

    /**
     * Удаление файла
     */
    public void deleteFile() {
        domainDao.deleteFile();
    }
}

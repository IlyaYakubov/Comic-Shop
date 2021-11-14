package repositories;

import java.util.List;

/**
 * Контекст выполнения стратегий с файлами
 */
public class DomainsClientDAO {

    private FileDAO domainDao;

    public void setFileDao(FileDAO domainDao) {
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

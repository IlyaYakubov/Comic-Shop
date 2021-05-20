package mechanism;

import java.io.*;

public class WorkWithComic {

    private DataBase dataBase = new DataBase();

    public void addComic(Comic comic) throws IOException {
        dataBase.add(comic);
    }

    public void deleteComic(String nameOfComic) {
        dataBase.delete(nameOfComic);
    }

    public void editComic(String nameOfComic, int elementOfComic, String newElement) {
        dataBase.edit(nameOfComic, elementOfComic, newElement);
    }
}

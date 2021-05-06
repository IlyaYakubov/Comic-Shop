package ui;

import mechanism.Comic;

import java.io.IOException;

/**
 * Меню, отображающее основные функции магазина
 */
public interface IFunctions {

    void addComic(Comic comic) throws IOException;

    void deleteComic(String nameOfComic);

    void editComic(String nameOfComic, int elementOfComic, String newElement);
}

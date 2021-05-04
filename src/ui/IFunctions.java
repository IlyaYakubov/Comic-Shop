package ui;

import java.io.IOException;

/**
 * Меню, отображающее основные функции магазина
 */
public interface IFunctions {

    void addElement() throws IOException;

    void deleteElement();

    void editElement();
}

package presenters;

import domain.Comic;
import services.ComicService;
import services.SearchService;
import ui.edit.EditUI;

/**
 * Контроллер редактирования комикса
 */
public class EditPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final EditUI EDITUI;

    public EditPresenter(EditUI editUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        EDITUI = editUI;
    }

    /**
     * Поиск комикса для редактирования
     *
     * @param comicName наименование комикса
     */
    public boolean findComicForEdit(String comicName) {
        Comic comic = SEARCH_SERVICE.getComicByName(comicName);
        if (comic == null) {
            return false;
        }
        EDITUI.setContent(comic);
        return true;
    }

    /**
     * При нажатии на кнопку завершения редактирования элементов комикса
     *
     * @param comic элементы комикса
     */
    public void onClickEdit(String[] comic) {
        COMIC_SERVICE.editComic(comic);
    }
}

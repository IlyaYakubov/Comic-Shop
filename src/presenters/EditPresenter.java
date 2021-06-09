package presenters;

import domain.sell.CartItem;
import services.ComicService;
import services.SearchService;
import ui.edit.EditUI;

/**
 * Контроллер редактирования комикса
 */
public class EditPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;
    private final EditUI editUI;

    public EditPresenter(EditUI editUI) {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
        this.editUI = editUI;
    }

    /**
     * При нажатии на кнопку завершения редактирования элементов комикса
     *
     * @param comicName - наименование комикса
     */
    public boolean findComicForEdit(String comicName) {
        CartItem comic = SEARCH_SERVICE.getComicByName(comicName);
        if (comic == null) {
            return false;
        }
        editUI.setContent(comic.getComic());
        return true;
    }

    /**
     * При нажатии на кнопку завершения редактирования элементов комикса
     *
     * @param comic - элементы комикса
     */
    public void onClickEdit(String[] comic) {
        COMIC_SERVICE.editComic(comic);
    }
}

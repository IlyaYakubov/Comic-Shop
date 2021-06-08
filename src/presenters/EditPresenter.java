package presenters;

import domain.sell.CartItem;
import services.ComicService;
import services.SearchService;
import ui.edit.EditUI;

/**
 * Контроллер редактирования комикса
 */
public class EditPresenter {

    private final ComicService comicService;
    private final SearchService searchService;
    private final EditUI editUI;

    public EditPresenter(EditUI editUI) {
        comicService = ComicService.INSTANCE;
        searchService = new SearchService();
        this.editUI = editUI;
    }

    /**
     * При нажатии на кнопку завершения редактирования элементов комикса
     *
     * @param comicName - наименование комикса
     */
    public boolean findComicForEdit(String comicName) {
        CartItem comic = searchService.getComicByName(comicName);
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
        comicService.editComic(comic);
    }
}

package presenters;

import domain.sell.CartItem;
import services.ComicService;
import services.SearchService;
import ui.edit.EditUI;

/**
 * Контроллер редактирования комикса
 */
public class EditPresenter {

    private ComicService comicService;
    private SearchService searchService;
    private EditUI editUI;

    public EditPresenter(EditUI editUI) {
        comicService = new ComicService();
        searchService = new SearchService();
        this.editUI = editUI;
    }

    /**
     * При нажатии на кнопку завершения редактирования элементов комикса
     *
     * @param comicName - наименование комикса
     */
    public void findComicForEdit(String comicName) {
        CartItem comic = searchService.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        editUI.setContent(comic.getComic());
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

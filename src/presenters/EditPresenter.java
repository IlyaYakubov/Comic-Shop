package presenters;

import domain.Comic;
import services.ComicService;
import services.SearchService;
import ui.EditUI;

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
        Comic comic = searchService.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        editUI.setContent(comic);
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

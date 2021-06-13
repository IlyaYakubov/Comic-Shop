package presenters;

import domain.Comic;
import services.ComicService;
import services.SearchService;

/**
 * Контроллер редактирования комикса
 */
public class EditPresenter {

    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;

    public EditPresenter() {
        COMIC_SERVICE = ComicService.INSTANCE;
        SEARCH_SERVICE = SearchService.INSTANCE;
    }

    /**
     * Поиск комикса для редактирования
     *
     * @param comicName наименование комикса
     */
    public Comic findComicForEdit(String comicName) {
        return SEARCH_SERVICE.getComicByName(comicName);
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

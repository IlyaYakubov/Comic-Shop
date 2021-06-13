package presenters;

import domain.Comic;
import services.ComicService;
import services.SearchService;

/**
 * Контроллер удаления комиксов
 */
public class DeletePresenter {

    public static DeletePresenter INSTANCE = new DeletePresenter();
    private final ComicService COMIC_SERVICE;
    private final SearchService SEARCH_SERVICE;

    private DeletePresenter() {
        SEARCH_SERVICE = SearchService.INSTANCE;
        COMIC_SERVICE = ComicService.INSTANCE;
    }

    /**
     * Поиск комикса
     *
     * @param comicName наименование комикса
     */
    public Comic findComicForDelete(String comicName) {
        return SEARCH_SERVICE.getComicByName(comicName);
    }

    /**
     * Удаление комикса
     *
     * @param nameOfComics наименование комикса
     */
    public void onClickEdit(String nameOfComics) {
        COMIC_SERVICE.deleteComic(nameOfComics);
    }
}

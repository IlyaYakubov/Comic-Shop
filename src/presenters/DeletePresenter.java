package presenters;

import services.ComicService;

/**
 * Контроллер удаления комиксов
 */
public class DeletePresenter {

    private final ComicService comicService;

    public DeletePresenter() {
        comicService = ComicService.INSTANCE;
    }

    /**
     * Удаление комикса
     *
     * @param nameOfComics - наименование комикса
     */
    public void onClickEdit(String nameOfComics) {
        comicService.deleteComic(nameOfComics);
    }
}

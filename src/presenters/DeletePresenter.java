package presenters;

import services.ComicService;

/**
 * Контроллер удаления комиксов
 */
public class DeletePresenter {

    private ComicService comicService;

    public DeletePresenter() {
        comicService = new ComicService();
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

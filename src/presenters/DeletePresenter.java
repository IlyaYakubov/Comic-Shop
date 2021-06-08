package presenters;

import services.ComicService;

/**
 * Контроллер удаления комиксов
 */
public class DeletePresenter {

    public static DeletePresenter INSTANCE = new DeletePresenter();
    private final ComicService comicService = ComicService.INSTANCE;

    private DeletePresenter() {
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

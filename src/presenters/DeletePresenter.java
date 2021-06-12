package presenters;

import services.ComicService;

/**
 * Контроллер удаления комиксов
 */
public class DeletePresenter {

    public static DeletePresenter INSTANCE = new DeletePresenter();
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    private DeletePresenter() {
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

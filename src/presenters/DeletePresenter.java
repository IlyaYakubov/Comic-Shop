package presenters;

import services.ComicService;
import ui.DeleteUI;

/**
 * Контроллер удаления комиксов
 */
public class DeletePresenter {

    private ComicService comicService;
    private DeleteUI deleteUI;

    public DeletePresenter(DeleteUI deleteUI) {
        comicService = new ComicService();
        this.deleteUI = deleteUI;
    }

    /**
     * Добавление комикса
     */
    public void onClickDelete(String nameOfComics) {
        comicService.deleteComic(nameOfComics);
    }
}

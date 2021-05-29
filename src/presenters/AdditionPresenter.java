package presenters;

import services.ComicService;
import ui.AdditionUI;

/**
 * Контроллер добавления комикса
 */
public class AdditionPresenter {

    private ComicService comicService;
    private AdditionUI additionUI;

    public AdditionPresenter(AdditionUI additionUI) {
        comicService = new ComicService();
        this.additionUI = additionUI;
    }

    /**
     * Добавление комикса
     */
    public void onClickAdd(String[] elementsOfComic) {
        comicService.addComic(elementsOfComic);
    }
}

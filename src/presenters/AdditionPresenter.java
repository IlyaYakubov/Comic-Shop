package presenters;

import services.ComicService;

/**
 * Контроллер добавления комикса
 */
public class AdditionPresenter {

    public static AdditionPresenter INSTANCE = new AdditionPresenter();
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    private AdditionPresenter() {
    }

    /**
     * При нажатии на кнопку добавления элементов комикса
     *
     * @param elementsOfComic массив с элементами комикса
     */
    public void onClickAdd(String[] elementsOfComic) {
        COMIC_SERVICE.addComic(elementsOfComic);
    }
}

package presenters;

import services.ComicService;

/**
 * Контроллер добавления комикса
 */
public class AdditionPresenter {

    private ComicService comicService;

    public AdditionPresenter() {
        comicService = new ComicService();
    }

    /**
     * При нажатии на кнопку добавления элементов комикса
     * @param elementsOfComic - массив с элементами комикса
     */
    public void onClickAdd(String[] elementsOfComic) {
        comicService.addComic(elementsOfComic);
    }
}

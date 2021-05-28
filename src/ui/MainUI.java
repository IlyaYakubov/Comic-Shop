package ui;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import services.ComicService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс взаимодействия пользователя с программой
 */
public class MainUI extends Application {

    private static final BufferedReader CONSOLE_READER = new BufferedReader(new InputStreamReader(System.in));
    private static final StringBuilder TEXT = new StringBuilder();

    private static final int SERIAL_NUMBER_NAME = 1;
    private static final int SERIAL_NUMBER_AUTHOR = 2;
    private static final int SERIAL_NUMBER_PUBLISHING = 3;
    private static final int SERIAL_NUMBER_NUMBER_OF_PAGES = 4;
    private static final int SERIAL_NUMBER_GENRE = 5;
    private static final int SERIAL_NUMBER_YEAR = 6;
    private static final int SERIAL_NUMBER_COST_PRICE = 7;
    private static final int SERIAL_NUMBER_SALE_PRICE = 8;
    private static final int SERIAL_NUMBER_IS_CONTINUATION = 9;

    /**
     * Отображает кнопки меню
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Основное окно");

        Button buttonAddComic = new Button("Добавление");
        Button buttonDeleteComic = new Button("Удаление");
        Button buttonEditComic = new Button("Редактирование");
        Button buttonSellComic = new Button("Продажа");

        FlowPane flowPane = new FlowPane();
        flowPane.getChildren().add(buttonAddComic);
        flowPane.getChildren().add(buttonDeleteComic);
        flowPane.getChildren().add(buttonEditComic);
        flowPane.getChildren().add(buttonSellComic);
        flowPane.setVgap(10);
        flowPane.setHgap(10);
        flowPane.setOrientation(Orientation.VERTICAL);

        buttonAddComic.setOnMouseClicked(mouseEvent -> {

        });

        buttonDeleteComic.setOnMouseClicked(mouseEvent -> {

        });

        buttonEditComic.setOnMouseClicked(mouseEvent -> {

        });

        buttonSellComic.setOnMouseClicked(mouseEvent -> {
            SellUI sellUI = new SellUI();
            try {
                sellUI.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Scene scene = new Scene(flowPane, 600, 800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Метод ввода пунктов меню
     */
    private void enteringMenuItem() {
        try {
            String userOption = CONSOLE_READER.readLine();
            ComicService comicService = new ComicService();
            switch (userOption) {
                case "1" -> {
                    //Util.printMessage("Введите название комикса: ");
                    String name = CONSOLE_READER.readLine();
                    //Util.printMessage("Введите ФИО художника/автора: ");
                    String author = CONSOLE_READER.readLine();
                    //Util.printMessage("Введите название издательства комикса: ");
                    String publishing = CONSOLE_READER.readLine();
                    //Util.printMessage("Введите количество страниц: ");
                    int numberOfPages;
                    while (true) {
                        try {
                            numberOfPages = Integer.parseInt(CONSOLE_READER.readLine());
                            break;
                        } catch (NumberFormatException e) {
                            //Util.printMessage("Вы ввели не число");
                        }
                    }
                    //Util.printMessage("Введите жанр: ");
                    String genre = CONSOLE_READER.readLine();
                    //Util.printMessage("Введите год издания: ");
                    int yearOfPublishing;
                    while (true) {
                        try {
                            yearOfPublishing = Integer.parseInt(CONSOLE_READER.readLine());
                            break;
                        } catch (NumberFormatException e) {
                            //Util.printMessage("Вы ввели не число");
                        }
                    }
                    //Util.printMessage("Введите себестоимость: ");
                    double costPrice;
                    while (true) {
                        try {
                            costPrice = Double.parseDouble(CONSOLE_READER.readLine());
                            break;
                        } catch (NumberFormatException e) {
                            //Util.printMessage("Вы ввели не число");
                        }
                    }
                    //Util.printMessage("Введите цену для продажи: ");
                    double sellingPrice;
                    while (true) {
                        try {
                            sellingPrice = Double.parseDouble(CONSOLE_READER.readLine());
                            break;
                        } catch (NumberFormatException e) {
                            //Util.printMessage("Вы ввели не число");
                        }
                    }
                    //Util.printMessage("Является ли комикс продолжением другого комикса или серии (true / false) ? ");
                    boolean isContinuation = Boolean.parseBoolean(CONSOLE_READER.readLine());

                    /*comicService.addComic(new Comic(name, author, publishing, numberOfPages, genre,
                            yearOfPublishing, costPrice, sellingPrice, isContinuation));*/
                }
                case "2" -> {
                    //Util.printMessage("Введите название комикса, который требуется удалить");
                    String element = getElementOfComic();
                    comicService.deleteComic(element);
                }
                case "3" -> {
                    //Util.printMessage("Введите название комикса, который требуется редактировать");
                    String element = getElementOfComic();
                    showEditMenu(element);
                }
                case "4" -> {
                    //Util.printMessage("Введите название комикса, который требуется продать");
                    String nameOfComic = getElementOfComic();
                    /*SellPresenter sellPresenter = new SellPresenter(this);
                    sellPresenter.onClickAddInCart(nameOfComic);*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //showMainFunctions();
    }

    /**
     * Отображает пункты меню редактирования и просит пользователя ввести пункт меню
     *
     * @param nameOfComic наименование комикса
     */
    private void showEditMenu(String nameOfComic) {
        TEXT.append("любой символ - отмена\n").append("1 - название\n").append("2 - автор\n")
                .append("3 - издательство\n").append("4 - количество страниц\n")
                .append("5 - жанр\n").append("6 - год публикации\n")
                .append("7 - себестоимость\n").append("8 - цена продажи\n")
                .append("9 - является ли продолжением серии\n");

        enteringEditMenuItem(nameOfComic);
    }

    /**
     * В зависимости от выбранного элемента редактирования передается нужный аргумент
     *
     * @param nameOfComic имя редактируемого комикса
     */
    private void enteringEditMenuItem(String nameOfComic) {
        try {
            String userOption = CONSOLE_READER.readLine();
            ComicService comicService = new ComicService();
            switch (userOption) {
                case "1" -> {
                    //Util.printMessage("Введите новое имя");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_NAME, CONSOLE_READER.readLine());
                }
                case "2" -> {
                    //Util.printMessage("Введите ФИО нового автора");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_AUTHOR, CONSOLE_READER.readLine());
                }
                case "3" -> {
                    //Util.printMessage("Введите новое название издания");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_PUBLISHING, CONSOLE_READER.readLine());
                }
                case "4" -> {
                    //Util.printMessage("Введите новое количество страниц");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_NUMBER_OF_PAGES, CONSOLE_READER.readLine());
                }
                case "5" -> {
                    //Util.printMessage("Введите название нового жанра");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_GENRE, CONSOLE_READER.readLine());
                }
                case "6" -> {
                    //Util.printMessage("Введите новый год выпуска");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_YEAR, CONSOLE_READER.readLine());
                }
                case "7" -> {
                    //Util.printMessage("Введите новую себестоимость");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_COST_PRICE, CONSOLE_READER.readLine());
                }
                case "8" -> {
                    //Util.printMessage("Введите новую цену продажи");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_SALE_PRICE, CONSOLE_READER.readLine());
                }
                case "9" -> {
                    //Util.printMessage("Введите \"true\" если комикс всё же является продолжением");
                    comicService.editComic(nameOfComic, SERIAL_NUMBER_IS_CONTINUATION, CONSOLE_READER.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получает значение элемента комикса, введенное пользователем (продавцом) магазина
     *
     * @return элемент (год, издание, автор, жанр и т.д.)
     * @throws IOException - в случае когда с потоком ввода-вывода что-то пошло не так
     */
    private String getElementOfComic() throws IOException {
        String element;
        do {
            element = CONSOLE_READER.readLine();
        } while (element.isEmpty());
        return element;
    }
}

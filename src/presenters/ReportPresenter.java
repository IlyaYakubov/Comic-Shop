package presenters;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import services.ReportService;

/**
 * Контроллер отчетов
 */
public class ReportPresenter {

    private final ReportService REPORT_SERVICE = ReportService.INSTANCE;
    private TableView<String> table;
    private String dateBeginString;
    private String dateEndString;

    public void setDateBeginString(String dateBeginString) {
        this.dateBeginString = dateBeginString;
    }

    public void setDateEndString(String dateEndString) {
        this.dateEndString = dateEndString;
    }

    /**
     * Формирование отчета
     *
     * @param checkBoxValue режим отчета
     */
    public void onClick(String checkBoxValue) {
        switch (checkBoxValue) {
            case "продаваемые" -> setContent(FXCollections.observableList(
                    REPORT_SERVICE.getTopSold(dateBeginString, dateEndString)));
            case "новинки" -> setContent(FXCollections.observableList(
                    REPORT_SERVICE.getTopNew(dateBeginString, dateEndString)));
            case "топ авторов" -> setContent(FXCollections.observableList(
                    REPORT_SERVICE.getTopAuthor(dateBeginString, dateEndString)));
            case "топ жанров" -> setContent(FXCollections.observableList(
                    REPORT_SERVICE.getTopGenre(dateBeginString, dateEndString)));
        }

    }

    public void setTable(TableView<String> table) {
        this.table = table;
    }

    /**
     * Установка контента в элементы окна
     *
     * @param comics список комиксов
     */
    public void setContent(ObservableList<String> comics) {
        table.setItems(comics);
    }
}

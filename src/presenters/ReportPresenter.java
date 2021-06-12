package presenters;

import javafx.collections.FXCollections;
import services.ReportService;
import ui.ReportUI;

/**
 * Контроллер отчетов
 */
public class ReportPresenter {

    private final ReportService REPORT_SERVICE = ReportService.INSTANCE;
    private final ReportUI REPORT_UI;

    public ReportPresenter(ReportUI reportUI) {
        this.REPORT_UI = reportUI;
    }

    /**
     * Формирование отчета
     *
     * @param checkBoxValue режим отчета
     */
    public void onClick(String checkBoxValue) {
        switch (checkBoxValue) {
            case "продаваемые" -> REPORT_UI.setContent(FXCollections.observableList(REPORT_SERVICE.getTopSold()));
            case "новинки" -> REPORT_UI.setContent(FXCollections.observableList(REPORT_SERVICE.getTopNew()));
            case "топ авторов" -> REPORT_UI.setContent(FXCollections.observableList(REPORT_SERVICE.getTopAuthor()));
            case "топ жанров" -> REPORT_UI.setContent(FXCollections.observableList(REPORT_SERVICE.getTopGenre()));
        }

    }
}

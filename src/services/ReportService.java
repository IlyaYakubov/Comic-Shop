package services;

import domains.Comic;
import domains.ReportingComic;
import domains.sale.Sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Сервис отчетов
 */
public class ReportService {

    private static final int ZERO = 0;
    private static final int LAST_HOUR = 23;
    private static final int LAST_MINUTE = 59;

    public static ReportService INSTANCE = new ReportService();
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    private ReportService() {
        // do nothing
    }

    /**
     * Список продаваемых
     *
     * @return сформированный список
     */
    public List<String> getTopSold(String startDateText, String endDateText) {
        LocalDateTime startDate = getStartDate(startDateText);
        LocalDateTime endDate = getEndDate(endDateText);

        List<ReportingComic> comics = new ArrayList<>();
        List<Sale> sales = COMIC_SERVICE.getSales();
        sales.stream()
                .filter(sale -> sale.getDate().isAfter(startDate)).filter(sale -> sale.getDate().isBefore(endDate))
                .forEach(sale -> sale.getCart().getCartItems().forEach(cartItem ->
                        comics.add((ReportingComic) cartItem.getComic())));
        Map<String, Long> countCartItems = comics.stream().collect(
                Collectors.groupingBy(Comic::getName, Collectors.counting()));

        return getSortedComics(countCartItems);
    }

    /**
     * Список новинок
     *
     * @return сформированный список
     */
    public List<String> getTopNews(String startDateText, String endDateText) {
        LocalDateTime startDate = getStartDate(startDateText);
        LocalDateTime endDate = getEndDate(endDateText);
        if (COMIC_SERVICE.getComics().size() == 0) {
            return new ArrayList<>();
        }
        List<String> comics = new ArrayList<>();
        getReportingComics().stream().sorted(Comparator.comparingInt(Comic::getYearOfPublishing)).
                sorted((o1, o2) -> -o1.getYearOfPublishing() - o2.getYearOfPublishing()).
                filter(comic -> comic.getReceiptDate().isAfter(startDate)).
                filter(comic -> comic.getReceiptDate().isBefore(endDate)).
                forEach(comic -> comics.add(comic.getName())
                );
        return comics.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Топ авторов
     *
     * @return сформированный список
     */
    public List<String> getTopAuthors(String startDateText, String endDateText) {
        LocalDateTime startDate = LocalDateTime.parse(startDateText);
        LocalDateTime endDate = LocalDateTime.parse(endDateText);
        if (COMIC_SERVICE.getComics().size() == 0) {
            return new ArrayList<>();
        }
        Map<String, Long> countAuthors = getCountAuthors(startDate, endDate, getReportingComics());
        return getSortedComics(countAuthors);
    }

    /**
     * Топ жанров
     *
     * @return сформированный список
     */
    public List<String> getTopGenres(String startDateText, String endDateText) {
        LocalDateTime startDate = getStartDate(startDateText);
        LocalDateTime endDate = getEndDate(endDateText);
        if (COMIC_SERVICE.getComics().size() == 0) {
            return new ArrayList<>();
        }
        List<ReportingComic> comics = getReportingComics();
        return getSortedComics(getCountGenres(startDate, endDate, comics));
    }

    private List<String> getSortedComics(Map<String, Long> countCartItems) {
        List<String> comics = new ArrayList<>();
        countCartItems.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                cartItemLongEntry -> comics.add(cartItemLongEntry.getKey())
        );
        return comics;
    }

    private LocalDateTime getStartDate(String startDateText) {
        return LocalDate.parse(startDateText).atTime(ZERO, ZERO, ZERO);
    }

    private LocalDateTime getEndDate(String endDateText) {
        return LocalDate.parse(endDateText).atTime(LAST_HOUR, LAST_MINUTE, LAST_MINUTE);
    }

    private List<ReportingComic> getReportingComics() {
        List<ReportingComic> comics = new ArrayList<>();
        for (Comic comic : COMIC_SERVICE.getComics()) {
            comics.add((ReportingComic) comic);
        }
        return comics;
    }

    private Map<String, Long> getCountAuthors(LocalDateTime startDate,
                                              LocalDateTime endDate,
                                              List<ReportingComic> comics) {
        return comics.stream()
                .filter(comic -> comic.getReceiptDate().isAfter(startDate))
                .filter(comic -> comic.getReceiptDate().isBefore(endDate))
                .collect(Collectors.groupingBy(comic -> comic.getAuthor().getName(), Collectors.counting()));
    }

    private Map<String, Long> getCountGenres(LocalDateTime startDate,
                                             LocalDateTime endDate,
                                             List<ReportingComic> comics) {
        return comics.stream()
                .filter(comic -> comic.getReceiptDate().isAfter(startDate))
                .filter(comic -> comic.getReceiptDate().isBefore(endDate))
                .collect(Collectors.groupingBy(comic -> comic.getGenre().getName(), Collectors.counting()));
    }
}

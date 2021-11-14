package services;

import domains.Comic;
import domains.ReportingComic;
import domains.sell.Sell;

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

    public static ReportService INSTANCE = new ReportService();
    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;

    private ReportService() {
    }

    /**
     * Список продаваемых
     *
     * @return сформированный список
     */
    public List<String> getTopSold(String dateBeginString, String dateEndString) {
        LocalDateTime dateBegin = getDateBegin(dateBeginString);
        LocalDateTime dateEnd = getDateEnd(dateEndString);

        List<ReportingComic> comics = new ArrayList<>();
        List<Sell> allSells = COMIC_SERVICE.getSells();
        allSells.stream()
                .filter(sell -> sell.getDate().isAfter(dateBegin)).filter(sell -> sell.getDate().isBefore(dateEnd))
                .forEach(sell -> sell.getCart().getCartItems().forEach(cartItem ->
                        comics.add((ReportingComic) cartItem.getComic())));
        Map<String, Long> countCartItems = comics.stream().collect(
                Collectors.groupingBy(Comic::getName, Collectors.counting()));

        List<String> newComics = new ArrayList<>();
        countCartItems.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                cartItemLongEntry -> newComics.add(cartItemLongEntry.getKey())
        );
        return newComics;
    }

    /**
     * Список новинок
     *
     * @return сформированный список
     */
    public List<String> getTopNew(String dateBeginString, String dateEndString) {
        LocalDateTime dateBegin = getDateBegin(dateBeginString);
        LocalDateTime dateEnd = getDateEnd(dateEndString);
        if (COMIC_SERVICE.getComics().size() == 0) {
            return new ArrayList<>();
        }
        List<ReportingComic> allComics = getReportingComics();
        List<String> newComics = new ArrayList<>();
        allComics.stream().sorted(Comparator.comparingInt(Comic::getYearOfPublishing)).
                sorted((o1, o2) -> -o1.getYearOfPublishing() - o2.getYearOfPublishing()).
                filter(comic -> comic.getReceiptDate().isAfter(dateBegin)).
                filter(comic -> comic.getReceiptDate().isBefore(dateEnd)).
                forEach(comic -> newComics.add(comic.getName())
                );
        return newComics.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Топ авторов
     *
     * @return сформированный список
     */
    public List<String> getTopAuthor(String dateBeginString, String dateEndString) {
        LocalDateTime dateBegin = LocalDateTime.parse(dateBeginString);
        LocalDateTime dateEnd = LocalDateTime.parse(dateEndString);
        if (COMIC_SERVICE.getComics().size() == 0) {
            return new ArrayList<>();
        }
        List<ReportingComic> allComics = getReportingComics();
        Map<String, Long> countAuthors = getCountAuthors(dateBegin, dateEnd, allComics);
        List<String> newComics = new ArrayList<>();
        countAuthors.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                authorLongEntry -> newComics.add(authorLongEntry.getKey())
        );
        return newComics;
    }

    /**
     * Топ жанров
     *
     * @return сформированный список
     */
    public List<String> getTopGenre(String dateBeginString, String dateEndString) {
        LocalDateTime dateBegin = getDateBegin(dateBeginString);
        LocalDateTime dateEnd = getDateEnd(dateEndString);
        if (COMIC_SERVICE.getComics().size() == 0) {
            return new ArrayList<>();
        }
        List<ReportingComic> allComics = getReportingComics();
        Map<String, Long> countGenres = getCountGenres(dateBegin, dateEnd, allComics);
        List<String> newComics = new ArrayList<>();
        countGenres.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                genreLongEntry -> newComics.add(genreLongEntry.getKey())
        );
        return newComics;
    }

    private LocalDateTime getDateBegin(String dateBeginString) {
        return LocalDate.parse(dateBeginString).atTime(0, 0, 0);
    }

    private LocalDateTime getDateEnd(String dateEndString) {
        return LocalDate.parse(dateEndString).atTime(23, 59, 59);
    }

    private List<ReportingComic> getReportingComics() {
        List<ReportingComic> allComics = new ArrayList<>();
        for (Comic comic : COMIC_SERVICE.getComics()) {
            allComics.add((ReportingComic) comic);
        }
        return allComics;
    }

    private Map<String, Long> getCountAuthors(LocalDateTime dateBegin,
                                              LocalDateTime dateEnd,
                                              List<ReportingComic> allComics) {
        return allComics.stream()
                .filter(comic -> comic.getReceiptDate().isAfter(dateBegin))
                .filter(comic -> comic.getReceiptDate().isBefore(dateEnd))
                .collect(Collectors.groupingBy(comic -> comic.getAuthor().getName(), Collectors.counting()));
    }

    private Map<String, Long> getCountGenres(LocalDateTime dateBegin,
                                             LocalDateTime dateEnd,
                                             List<ReportingComic> allComics) {
        return allComics.stream()
                .filter(comic -> comic.getReceiptDate().isAfter(dateBegin))
                .filter(comic -> comic.getReceiptDate().isBefore(dateEnd))
                .collect(Collectors.groupingBy(comic -> comic.getGenre().getName(), Collectors.counting()));
    }
}

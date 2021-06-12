package services;

import domain.Comic;
import domain.sell.Sell;

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
    public List<String> getTopSold() {
        List<Comic> comics = new ArrayList<>();
        List<Sell> allSells = COMIC_SERVICE.getSells();
        allSells.forEach(sell -> sell.getCart().getCartItems().forEach(cartItem -> comics.add(cartItem.getComic())));
        Map<String, Long> countCartItems = comics.stream().collect(Collectors.groupingBy(Comic::getName, Collectors.counting()));

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
    public List<String> getTopNew() {
        List<Comic> allComics = COMIC_SERVICE.getComics();
        List<String> newComics = new ArrayList<>();
        allComics.stream().sorted(Comparator.comparingInt(Comic::getYearOfPublishing)).sorted(
                (o1, o2) -> -o1.getYearOfPublishing() - o2.getYearOfPublishing()).forEach(
                comic -> newComics.add(comic.getName())
        );
        return newComics.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Топ авторов
     *
     * @return сформированный список
     */
    public List<String> getTopAuthor() {
        List<Comic> allComics = COMIC_SERVICE.getComics();
        Map<String, Long> countAuthors = allComics.stream().collect(Collectors.groupingBy(comic -> comic.getAuthor().getName(), Collectors.counting()));

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
    public List<String> getTopGenre() {
        List<Comic> allComics = COMIC_SERVICE.getComics();
        Map<String, Long> countGenres = allComics.stream().collect(Collectors.groupingBy(comic -> comic.getGenre().getName(), Collectors.counting()));

        List<String> newComics = new ArrayList<>();
        countGenres.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                genreLongEntry -> newComics.add(genreLongEntry.getKey())
        );
        return newComics;
    }
}

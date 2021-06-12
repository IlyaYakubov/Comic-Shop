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
    private final ComicService comicService = ComicService.INSTANCE;

    private ReportService() {
    }

    public List<String> getTopSold() {
        List<Comic> comics = new ArrayList<>();
        List<Sell> allSells = comicService.getSells();
        allSells.forEach(sell -> sell.getCart().getCartItems().forEach(cartItem -> comics.add(cartItem.getComic())));
        Map<String, Long> countCartItems = comics.stream().collect(Collectors.groupingBy(Comic::getName, Collectors.counting()));

        List<String> newComics = new ArrayList<>();
        countCartItems.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                cartItemLongEntry -> newComics.add(cartItemLongEntry.getKey())
        );
        return newComics;
    }

    public List<String> getTopNew() {
        List<Comic> allComics = comicService.getComics();
        List<String> newComics = new ArrayList<>();
        allComics.stream().sorted(Comparator.comparingInt(Comic::getYearOfPublishing)).sorted(
                (o1, o2) -> -o1.getYearOfPublishing() - o2.getYearOfPublishing()).forEach(
                comic -> newComics.add(comic.getName())
        );
        return newComics.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getTopAuthor() {
        List<Comic> allComics = comicService.getComics();
        Map<String, Long> countAuthors = allComics.stream().collect(Collectors.groupingBy(comic -> comic.getAuthor().getName(), Collectors.counting()));

        List<String> newComics = new ArrayList<>();
        countAuthors.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                authorLongEntry -> newComics.add(authorLongEntry.getKey())
        );
        return newComics;
    }

    public List<String> getTopGenre() {
        List<Comic> allComics = comicService.getComics();
        Map<String, Long> countGenres = allComics.stream().collect(Collectors.groupingBy(comic -> comic.getGenre().getName(), Collectors.counting()));

        List<String> newComics = new ArrayList<>();
        countGenres.entrySet().stream().sorted((o1, o2) -> (int) -(o1.getValue() - o2.getValue())).forEach(
                genreLongEntry -> newComics.add(genreLongEntry.getKey())
        );
        return newComics;
    }
}

package services;

import domain.*;
import domain.discounts.Discount;
import domain.reservation.Customer;
import domain.reservation.Reservation;
import domain.sell.Cart;
import domain.sell.CartItem;
import domain.sell.Sell;
import repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Сервис по работе с комиксами
 */
public class ComicService {

    private static final int NAME = 0;
    private static final int AUTHOR = 1;
    private static final int PUBLISHING = 2;
    private static final int NUMBER_OF_PAGES = 3;
    private static final int GENRE = 4;
    private static final int YEAR = 5;
    private static final int COST_PRICE = 6;
    private static final int PRICE = 7;
    private static final int IS_CONTINUE = 8;
    private static final int STRING_PARAM = 9;
    private static final int NAME_OF_DISCOUNTS = 10;
    private static final int DATE_BEGIN = 11;
    private static final int DATE_END = 12;
    private static final String DELIMITER = ";";

    public static ComicService INSTANCE = new ComicService();
    private final ComicsDao COMIC_DAO = ComicsDao.INSTANCE;
    private final SellDao SELL_DAO = SellDao.INSTANCE;
    private final WriteOffDao WRITE_OFF_DAO = WriteOffDao.INSTANCE;
    private final ReservationDao RESERVATION_DAO = ReservationDao.INSTANCE;
    private final DiscountDao DISCOUNT = DiscountDao.INSTANCE;

    private final DomainsDaoClient DOMAIN_DAO = new DomainsDaoClient();

    private final List<Comic> COMICS = new ArrayList<>();
    private final List<Sell> SELLS = new ArrayList<>();
    private final List<Reservation> RESERVATIONS = new ArrayList<>();
    private final List<Discount> DISCOUNTS = new ArrayList<>();
    private final List<String> SELLS_IN_FILE = SELL_DAO.readFromFile();
    private final List<String> RESERVATIONS_IN_FILE = RESERVATION_DAO.readFromFile();
    private final List<String> DISCOUNTS_IN_FILE = DISCOUNT.readFromFile();
    private List<String> comicsInFile = COMIC_DAO.readFromFile();

    private ComicService() {
        createComicsFromFile();
        createComicsFromSoldFile();
        createComicsFromReservationsFile();
        createDiscountsFromDiscountsFile();
    }

    public List<Comic> getComics() {
        return COMICS;
    }

    public List<Sell> getSells() {
        return SELLS;
    }

    public List<Discount> getDiscounts() {
        return DISCOUNTS;
    }

    /**
     * Добавление комикса из элементов
     *
     * @param comic элементы комикса
     */
    public void addComic(String[] comic) {
        Comic newComic = formComicFromElements(comic);
        COMICS.add(newComic);
        DOMAIN_DAO.setFileDao(COMIC_DAO);
        DOMAIN_DAO.saveToFile(formComicForFile(comic));
        comicsInFile = DOMAIN_DAO.readFromFile();
    }

    /**
     * Редактирование комикса
     * Алгоритм:
     * 1. Удаляется файл
     * 2. Строки для файла обходятся циклом и по условию совпадения имени - заменяется строкой с отредатированными
     * значениями.
     * Тем самым происходит редактирование одного комикса
     *
     * @param newComicElements элементы комикса
     */
    public void editComic(String[] newComicElements) {
        if (COMICS.size() > 0) {
            List<String> newComicsForFile = new ArrayList<>();
            for (String elementsOfComic : comicsInFile) {
                String[] arrayOfElements = elementsOfComic.split(DELIMITER);
                if (newComicElements[NAME].equals(arrayOfElements[NAME])) {
                    newComicsForFile.add(formComicForFile(newComicElements));
                    continue;
                }
                newComicsForFile.add(elementsOfComic);
            }
            comicsInFile = newComicsForFile;
            updateComicInShop();
        }
    }

    /**
     * Удаление одного комикса из файла
     * Алгоритм:
     * 1. Удаляется файл
     * 2. Строки для файла обходятся циклом и по условию совпадения имени - переход к следующей
     * итерации без записи.
     * 3. Если имя не совпадает или уже был пропуск - то строка для файла записывается
     * Тем самым происходит удаление одного комикса
     *
     * @param comicName название комикса
     */
    public void deleteComic(String comicName) {
        if (COMICS.size() > 0) {
            List<String> newComicsForFile = new ArrayList<>();
            boolean wasAPass = false;
            for (String elementsOfComic : comicsInFile) {
                String[] arrayOfElements = elementsOfComic.split(DELIMITER);
                if (comicName.equals(arrayOfElements[NAME]) && !wasAPass) {
                    wasAPass = true;
                    continue;
                }
                newComicsForFile.add(elementsOfComic);
            }
            comicsInFile = newComicsForFile;
            updateComicInShop();
        }
    }

    /**
     * Продажа комиксов
     * Также происходит удаление и добавление комиксов в соответствующих коллекциях
     *
     * @param date дата продажи
     * @param cart корзина комиксов
     */
    public void makePurchase(LocalDateTime date, Cart cart) {
        deleteComicsFromFile(cart);
        Sell sell = new Sell(date, cart);
        for (CartItem cartItem : cart.getCartItems()) {
            DOMAIN_DAO.setFileDao(SELL_DAO);
            DOMAIN_DAO.saveToFile(formComicForFile(cartItem) + LocalDateTime.now());
        }
        sell.makePurchase();
        SELLS.add(sell);
    }

    /**
     * Списание комиксов
     *
     * @param cart корзина c комиксами
     */
    public void writeOffComics(Cart cart) {
        // предварительная проверка: если есть списываемые комиксы в резерве, отменить списание
        // также если списывается больше чем есть в магазине
        if (reserveCheck(cart)) {
            return;
        }
        deleteComicsFromFile(cart);
        for (CartItem cartItem : cart.getCartItems()) {
            DOMAIN_DAO.setFileDao(WRITE_OFF_DAO);
            DOMAIN_DAO.saveToFile(formComicForFile(cartItem));
        }
        cart.clear();
    }

    /**
     * Резервирование комиксов
     *
     * @param cart         корзина с комиксами
     * @param customerName имя клиента
     */
    public void reserveComics(Cart cart, String customerName) {
        Reservation reservation = new Reservation(new Customer(customerName), cart);
        for (CartItem cartItem : cart.getCartItems()) {
            DOMAIN_DAO.setFileDao(RESERVATION_DAO);
            DOMAIN_DAO.saveToFile(formComicForFile(cartItem) + customerName);
        }
        RESERVATIONS.add(reservation);
        reservation.makeReserve();
    }

    /**
     * Получение резервированных комиксов клиента
     *
     * @param customerName имя клиента
     * @return корзина с резервированными комиксами
     */
    public Cart getCustomersReservedComics(String customerName) {
        Cart cart = new Cart();
        if (RESERVATIONS.size() == 0) {
            return cart;
        }
        RESERVATIONS.stream().filter(reservation -> reservation.getCustomer().getName().equals(customerName)).forEach(reservation -> {
            for (CartItem cartItem : reservation.getCart().getCartItems()) {
                cart.addItem(cartItem);
            }
        });
        RESERVATIONS.removeIf(x -> x.getCustomer().getName().equals(customerName));
        if (cart.getCartItems().size() > 0) {
            DOMAIN_DAO.setFileDao(RESERVATION_DAO);
            DOMAIN_DAO.deleteFile();
            for (Reservation reservation : RESERVATIONS) {
                for (CartItem cartItem : reservation.getCart().getCartItems()) {
                    DOMAIN_DAO.saveToFile(formComicForFile(cartItem) + reservation.getCustomer().getName());
                }
            }
        }
        return cart;
    }

    /**
     * Запись акции
     *
     * @param name      наименование акции
     * @param dateBegin дата начала
     * @param dateEnd   дата окончания
     * @param cart      корзина с комиксами
     */
    public void saveDiscounts(LocalDateTime dateTime,
                              String name,
                              LocalDate dateBegin,
                              LocalDate dateEnd,
                              Cart cart,
                              int percent) {
        Discount discount = new Discount(name, dateTime, dateBegin, dateEnd, cart);
        discount.getCart().setCartItems(cart.getCartItems());
        discount.calculateDiscounts(percent);
        DISCOUNTS.add(discount);
        for (CartItem cartItem : discount.getCart().getCartItems()) {
            DOMAIN_DAO.setFileDao(DISCOUNT);
            DISCOUNT.saveToFile(formComicForFile(cartItem) +
                    dateTime + DELIMITER + name + DELIMITER + dateBegin + DELIMITER + dateEnd);
        }
    }

    private void createComicsFromFile() {
        for (String comicElements : comicsInFile) {
            Comic comic = formComicFromElements(comicElements);
            COMICS.add(comic);
        }
    }

    private void createComicsFromSoldFile() {
        Map<String, List<CartItem>> structure = new HashMap<>();
        for (String comicElements : SELLS_IN_FILE) {
            String[] elementsOfComic = comicElements.split(DELIMITER);
            String param = elementsOfComic[STRING_PARAM];
            List<CartItem> comics;
            if (structure.containsKey(param)) {
                comics = structure.get(param);
            } else {
                comics = new ArrayList<>();
                structure.put(param, comics);
            }
            comics.add(new CartItem(formComicFromElements(comicElements), Double.parseDouble(elementsOfComic[PRICE]), elementsOfComic[NAME]));
        }
        for (Map.Entry<String, List<CartItem>> entry : structure.entrySet()) {
            Cart cart = new Cart();
            cart.setCartItems(entry.getValue());
            Sell sell = new Sell(LocalDateTime.parse(entry.getKey()), cart);
            SELLS.add(sell);
        }
    }

    private void createComicsFromReservationsFile() {
        Map<String, List<CartItem>> structure = new HashMap<>();
        for (String comicElements : RESERVATIONS_IN_FILE) {
            String[] elementsOfComic = comicElements.split(DELIMITER);
            String param = elementsOfComic[STRING_PARAM];
            List<CartItem> comics;
            if (structure.containsKey(param)) {
                comics = structure.get(param);
            } else {
                comics = new ArrayList<>();
                structure.put(param, comics);
            }
            comics.add(new CartItem(formComicFromElements(comicElements), Double.parseDouble(elementsOfComic[PRICE]), elementsOfComic[NAME]));
        }
        for (Map.Entry<String, List<CartItem>> entry : structure.entrySet()) {
            Cart cart = new Cart();
            cart.setCartItems(entry.getValue());
            Reservation reservation = new Reservation(new Customer(entry.getKey()), cart);
            RESERVATIONS.add(reservation);
        }
    }

    private void createDiscountsFromDiscountsFile() {
        Map<String, List<String>> structure = new HashMap<>();
        for (String comicElements : DISCOUNTS_IN_FILE) {
            String[] elementsOfComic = comicElements.split(DELIMITER);
            String param = elementsOfComic[STRING_PARAM];
            List<String> comics;
            if (structure.containsKey(param)) {
                comics = structure.get(param);
            } else {
                comics = new ArrayList<>();
                structure.put(param, comics);
            }
            comics.add(comicElements);
        }

        for (Map.Entry<String, List<String>> entry : structure.entrySet()) {
            Cart cart = new Cart();
            for (String discountComics : entry.getValue()) {
                String[] elementsOfComic = discountComics.split(DELIMITER);
                cart.addItem(new CartItem(formComicFromElements(elementsOfComic), Double.parseDouble(elementsOfComic[PRICE]), elementsOfComic[NAME]));
            }
            String[] elementsOfComic = entry.getValue().get(0).split(DELIMITER);
            Discount discount = new Discount(
                    elementsOfComic[NAME_OF_DISCOUNTS],
                    LocalDateTime.parse(entry.getKey()),
                    LocalDate.parse(elementsOfComic[DATE_BEGIN]),
                    LocalDate.parse(elementsOfComic[DATE_END]),
                    cart);
            DISCOUNTS.add(discount);
        }
    }

    private void updateComicInShop() {
        DOMAIN_DAO.setFileDao(COMIC_DAO);
        DOMAIN_DAO.deleteFile();
        COMICS.clear();
        for (String comicElements : comicsInFile) {
            DOMAIN_DAO.saveToFile(comicElements);
            COMICS.add(formComicFromElements(comicElements));
        }
    }

    private Comic formComicFromElements(String comicElements) {
        String[] elementsOfComic = comicElements.split(DELIMITER);
        ReportingComic reportingComic = new ReportingComic(
                elementsOfComic[NAME], // наименование
                new Author(elementsOfComic[AUTHOR]), // автор
                new Publishing(elementsOfComic[PUBLISHING]), // издательство
                Integer.parseInt(elementsOfComic[NUMBER_OF_PAGES]), // количество страниц
                new Genre(elementsOfComic[GENRE]), // жанр
                Integer.parseInt(elementsOfComic[YEAR]), // год издательства
                Double.parseDouble(elementsOfComic[COST_PRICE]), // себестоимость
                Double.parseDouble(elementsOfComic[PRICE]), // цена продажи
                Boolean.parseBoolean(elementsOfComic[IS_CONTINUE]));  // является продолжением
        reportingComic.setReceiptDate(LocalDateTime.parse(elementsOfComic[STRING_PARAM])); // дата
        return reportingComic;
    }

    private Comic formComicFromElements(String[] elementsOfComic) {
        ReportingComic reportingComic = new ReportingComic(
                elementsOfComic[NAME], // наименование
                new Author(elementsOfComic[AUTHOR]), // автор
                new Publishing(elementsOfComic[PUBLISHING]), // издательство
                Integer.parseInt(elementsOfComic[NUMBER_OF_PAGES]), // количество страниц
                new Genre(elementsOfComic[GENRE]), // жанр
                Integer.parseInt(elementsOfComic[YEAR]), // год издательства
                Double.parseDouble(elementsOfComic[COST_PRICE]), // себестоимость
                Double.parseDouble(elementsOfComic[PRICE]), // цена продажи
                Boolean.parseBoolean(elementsOfComic[IS_CONTINUE])); // является продолжением
        reportingComic.setReceiptDate(LocalDateTime.parse(elementsOfComic[STRING_PARAM])); // дата
        return reportingComic;
    }

    private String formComicForFile(String[] comic) {
        return comic[NAME] + DELIMITER + // наименование
                comic[AUTHOR] + DELIMITER + // автор
                comic[PUBLISHING] + DELIMITER + // издательство
                comic[NUMBER_OF_PAGES] + DELIMITER + // количество страниц
                comic[GENRE] + DELIMITER + // жанр
                comic[YEAR] + DELIMITER + // год издательства
                comic[COST_PRICE] + DELIMITER + // себестоимость
                comic[PRICE] + DELIMITER + // цена продажи
                comic[IS_CONTINUE] + DELIMITER + // является продолжением
                LocalDateTime.now() + DELIMITER; // дата добавления в магазин

    }

    private String formComicForFile(CartItem cartItem) {
        return cartItem.getComic().getName() + DELIMITER + // наименование
                cartItem.getComic().getAuthor().getName() + DELIMITER + // автор
                cartItem.getComic().getPublishing().getName() + DELIMITER + // издательство
                cartItem.getComic().getNumberOfPages() + DELIMITER + // количество страниц
                cartItem.getComic().getGenre().getName() + DELIMITER + // жанр
                cartItem.getComic().getYearOfPublishing() + DELIMITER + // год издательства
                cartItem.getComic().getComicPrice().getCostPrice() + DELIMITER + // себестоимость
                cartItem.getComic().getComicPrice().getSellingPrice() + DELIMITER + // цена продажи
                cartItem.getComic().isContinuation() + DELIMITER; // является продолжением
    }

    private void deleteComicsFromFile(Cart cart) {
        for (CartItem cartItem : cart.getCartItems()) {
            deleteComic(cartItem.getComic().getName());
        }
    }

    private boolean reserveCheck(Cart cart) {
        Collections.sort(cart.getCartItems());

        Map<String, List<CartItem>> structureOfWriteOff = new HashMap<>();
        for (CartItem cartItem : cart.getCartItems()) {
            String comicName = cartItem.getName();
            List<CartItem> cartItems;
            if (structureOfWriteOff.containsKey(comicName)) {
                cartItems = structureOfWriteOff.get(comicName);
            } else {
                cartItems = new ArrayList<>();
                structureOfWriteOff.put(comicName, cartItems);
            }
            cartItems.add(cartItem);
        }

        for (Map.Entry<String, List<CartItem>> entry : structureOfWriteOff.entrySet()) {
            int countWriteOff = entry.getValue().size();
            int countInShop = 0;
            int countInReserve = 0;
            for (Comic comicInShop : COMICS) {
                if (entry.getKey().equals(comicInShop.getName())) {
                    countInShop++;
                }
            }
            for (Reservation reservation : RESERVATIONS) {
                if (entry.getKey().equals(reservation.getName())) {
                    countInReserve++;
                }
            }
            if (countWriteOff > (countInShop - countInReserve)) {
                return true;
            }
        }
        return false;
    }
}

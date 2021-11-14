package services;

import domains.*;
import domains.reservation.Customer;
import domains.reservation.Reservation;
import domains.sale.Cart;
import domains.sale.CartItem;
import domains.sale.Sale;
import repositories.*;

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
    private static final int DATE_OF_CREATION = 9;
    private static final int NAME_OF_DISCOUNTS = 10;
    private static final int DATE_BEGIN = 11;
    private static final int DATE_END = 12;
    private static final String DELIMITER = ";";

    public static ComicService INSTANCE = new ComicService();
    private final FileDAOImpl COMIC_DAO = FileDAOImpl.INSTANCE;
    private final SaleDAO SALE_DAO = SaleDAO.INSTANCE;
    private final WriteOffDAO WRITE_OFF_DAO = WriteOffDAO.INSTANCE;
    private final ReservationDAO RESERVATION_DAO = ReservationDAO.INSTANCE;
    private final DiscountDAO DISCOUNT_DAO = DiscountDAO.INSTANCE;

    private final DomainsClientDAO DOMAIN_DAO = new DomainsClientDAO();

    private final List<Comic> COMICS = new ArrayList<>();
    private final List<Sale> SALES = new ArrayList<>();
    private final List<Reservation> RESERVATIONS = new ArrayList<>();
    private final List<Discount> DISCOUNTS = new ArrayList<>();
    private final List<String> SALES_ON_FILE = SALE_DAO.readFromFile();
    private final List<String> RESERVATIONS_IN_FILE = RESERVATION_DAO.readFromFile();
    private final List<String> DISCOUNTS_IN_FILE = DISCOUNT_DAO.readFromFile();
    private List<String> comicsInFile = COMIC_DAO.readFromFile();

    private ComicService() {
        createComicsFromFile();
        createComicsFromSalesFile();
        createComicsFromReservationsFile();
        createDiscountsFromDiscountsFile();
    }

    public List<Comic> getComics() {
        return COMICS;
    }

    public List<Sale> getSells() {
        return SALES;
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
        Sale sale = new Sale(date, cart);
        for (CartItem cartItem : cart.getCartItems()) {
            DOMAIN_DAO.setFileDao(SALE_DAO);
            DOMAIN_DAO.saveToFile(reformattingComicForSalesFile(cartItem) + LocalDateTime.now());
        }
        sale.makePurchase();
        SALES.add(sale);
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
            DOMAIN_DAO.saveToFile(reformattingComicForSalesFile(cartItem));
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
            DOMAIN_DAO.saveToFile(reformattingComicForSalesFile(cartItem) + customerName);
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
                    DOMAIN_DAO.saveToFile(reformattingComicForSalesFile(cartItem) + reservation.getCustomer().getName());
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
            DOMAIN_DAO.setFileDao(DISCOUNT_DAO);
            DISCOUNT_DAO.saveToFile(reformattingComicForSalesFile(cartItem) +
                    dateTime + DELIMITER + name + DELIMITER + dateBegin + DELIMITER + dateEnd);
        }
    }

    private void createComicsFromFile() {
        for (String comicElements : comicsInFile) {
            Comic comic = formComicFromElements(comicElements);
            COMICS.add(comic);
        }
    }

    private void createComicsFromSalesFile() {
        fillCollectionFromFile(true);
    }

    private void createComicsFromReservationsFile() {
        fillCollectionFromFile(false);
    }

    private void fillCollectionFromFile(boolean isSales) {
        List<String> comicsAsElements;
        if (isSales) {
            comicsAsElements = SALES_ON_FILE;
        } else {
            comicsAsElements = RESERVATIONS_IN_FILE;
        }
        Map<String, List<CartItem>> comics = new HashMap<>();
        for (String comicElements : comicsAsElements) {
            String[] elementsOfComic = comicElements.split(DELIMITER);
            String dateOfCreation = elementsOfComic[DATE_OF_CREATION];
            List<CartItem> cartItems;
            if (comics.containsKey(dateOfCreation)) {
                cartItems = comics.get(dateOfCreation);
            } else {
                cartItems = new ArrayList<>();
                comics.put(dateOfCreation, cartItems);
            }
            cartItems.add(new CartItem(formComicFromElements(comicElements), Double.parseDouble(elementsOfComic[PRICE]), elementsOfComic[NAME]));
        }
        for (Map.Entry<String, List<CartItem>> cartItems : comics.entrySet()) {
            Cart cart = new Cart();
            cart.setCartItems(cartItems.getValue());
            if (isSales) {
                SALES.add(new Sale(LocalDateTime.parse(cartItems.getKey()), cart));
            } else {
                RESERVATIONS.add(new Reservation(new Customer(cartItems.getKey()), cart));
            }
        }
    }

    private void createDiscountsFromDiscountsFile() {
        Map<String, List<String>> comicsWithDates = new HashMap<>();
        for (String comicElements : DISCOUNTS_IN_FILE) {
            String[] elementsOfComic = comicElements.split(DELIMITER);
            String dateOfCreation = elementsOfComic[DATE_OF_CREATION];
            List<String> comics;
            if (comicsWithDates.containsKey(dateOfCreation)) {
                comics = comicsWithDates.get(dateOfCreation);
            } else {
                comics = new ArrayList<>();
                comicsWithDates.put(dateOfCreation, comics);
            }
            comics.add(comicElements);
        }

        for (Map.Entry<String, List<String>> entry : comicsWithDates.entrySet()) {
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
        return getReportingComic(elementsOfComic);
    }

    private Comic formComicFromElements(String[] elementsOfComic) {
        return getReportingComic(elementsOfComic);
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

    private String reformattingComicForSalesFile(CartItem cartItem) {
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

    private ReportingComic getReportingComic(String[] elementsOfComic) {
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
        reportingComic.setReceiptDate(LocalDateTime.parse(elementsOfComic[DATE_OF_CREATION])); // дата
        return reportingComic;
    }
}

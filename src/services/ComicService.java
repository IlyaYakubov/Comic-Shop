package services;

import domain.Author;
import domain.Genre;
import domain.Publishing;
import domain.discounts.Discount;
import domain.discounts.DiscountComic;
import domain.sell.Cart;
import domain.sell.CartItem;
import domain.sell.ComicPrice;
import domain.sell.Sell;
import repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис по работе с комиксами
 */
public class ComicService {

    private static final String DELIMITER = ";";
    public static ComicService INSTANCE = new ComicService();
    private final FileDao fileDao = FileDao.INSTANCE;
    private final ReservationDao reservationDao = ReservationDao.INSTANCE;
    private final DiscountDao discountDao = DiscountDao.INSTANCE;
    private final List<DiscountComic> reserves = new ArrayList<>();
    private final List<DiscountComic> allComics = new ArrayList<>();
    private List<String> comicsInFile = fileDao.readFromFile();

    private ComicService() {
    }

    /**
     * Добавление комикса из элементов
     *
     * @param comic - элементы комикса
     */
    public void addComic(String[] comic) {
        fileDao.saveToFile(formComicFromElements(comic).toString());
    }

    /**
     * Редактирование комикса
     *
     * @param comic - элементы комикса
     */
    public void editComic(String[] comic) {
        fileDao.deleteFile();

        List<String> newListOfComics = new ArrayList<>();
        for (String elementsOfComic : comicsInFile) {
            String[] arrayOfElements = elementsOfComic.split(DELIMITER);
            if (comic[0].equals(arrayOfElements[0])) {
                newListOfComics.add(formComicFromElements(comic).toString());
                continue;
            }
            newListOfComics.add(elementsOfComic);
        }

        comicsInFile = newListOfComics;
        for (String newComic : newListOfComics) {
            fileDao.saveToFile(newComic);
        }
    }

    /**
     * Удаление комикса из файла
     * При совпадении имени комикс не перезаписывается
     * (файл предварительно удаляется)
     *
     * @param nameOfComic - название комикса
     */
    public void deleteComic(String nameOfComic) {
        if (comicsInFile.size() > 0) {
            List<String> newListOfComics = new ArrayList<>();
            fileDao.deleteFile();
            boolean wasAPass = false;
            for (String comic : comicsInFile) {
                String[] arrayOfElements = comic.split(DELIMITER);
                if (arrayOfElements[0].equals(nameOfComic) && !wasAPass) {
                    wasAPass = true;
                    continue;
                }
                newListOfComics.add(comic);
                fileDao.saveToFile(comic);
            }
            comicsInFile = newListOfComics;
        }
    }

    /**
     * Продажа комиксов
     *
     * @param cart - корзина комиксов
     */
    public void makePurchase(Cart cart) {
        // todo проверить есть ли по скидкам

        for (DiscountComic reserve : reserves) {
            cart.addComic(reserve);
        }
        reserves.clear();

        for (CartItem item : cart.getComics()) {
            deleteComic(item.getComic().getName());
        }

        SellDao sellDao = SellDao.INSTANCE;
        for (CartItem comic : cart.getComics()) {
            String buyItem = LocalDateTime.now() + DELIMITER + comic.getComic().getName() + DELIMITER
                    + comic.getPrice();
            sellDao.saveToFile(buyItem);
        }
        Sell sell = new Sell(cart);
        sell.makePurchase();
    }

    /**
     * Списание комиксов
     *
     * @param cart - корзина комиксов
     */
    public void writeOffComics(Cart cart) {
        // todo проверить есть ли по скидкам

        getAll();
        getReserves();
        List<CartItem> cartItems = new ArrayList<>(cart.getComics());
        for (CartItem comic : cartItems) {
            int countInFile = 0;
            int countInReserve = 0;
            int countForWriteOff = 0;
            for (DiscountComic comicInFile : allComics) {
                if (comic.getName().equals(comicInFile.getName())) {
                    countInFile++;
                }
            }
            for (DiscountComic comicInReserve : reserves) {
                if (comic.getName().equals(comicInReserve.getName())) {
                    countInReserve++;
                }
            }
            for (CartItem cartItem : cart.getComics()) {
                if (comic.getName().equals(cartItem.getComic().getName())) {
                    countForWriteOff++;
                }
            }
            if (countForWriteOff > (countInFile - countInReserve)) {
                // нельзя списать больше чем разность между в файле и резерве
                return;
            }
        }

        for (CartItem comic : cart.getComics()) {
            deleteComic(comic.getComic().getName());
        }

        WriteOffDao writeOffDao = new WriteOffDao();
        for (CartItem comic : cart.getComics()) {
            String buyItem = LocalDateTime.now() + DELIMITER + comic.getComic().getName();
            writeOffDao.saveToFile(buyItem);
        }

        cart.clear();
    }

    /**
     * Резервация комиксов
     *
     * @param cart     - корзина комиксов
     * @param customer - имя клиента
     */
    public void reservationComics(Cart cart, String customer) {
        ReservationDao reservationDao = ReservationDao.INSTANCE;
        for (CartItem comic : cart.getComics()) {
            String reservationElement = LocalDateTime.now() + DELIMITER + customer + DELIMITER +
                    comic.getComic().getName() + DELIMITER +
                    comic.getComic().getAuthor().getName() + DELIMITER +
                    comic.getComic().getPublishing().getName() + DELIMITER +
                    comic.getComic().getNumberOfPages() + DELIMITER +
                    comic.getComic().getGenre().getName() + DELIMITER +
                    comic.getComic().getYearOfPublishing() + DELIMITER +
                    comic.getComic().getComicPrice().getCostPrice() + DELIMITER +
                    comic.getComic().getComicPrice().getSellingPrice() + DELIMITER +
                    comic.getComic().isContinuation();
            reservationDao.saveToFile(reservationElement);
        }

        cart.clear();
    }

    /**
     * Получение резервированных комиксов
     *
     * @param customerName - имя клиента
     * @return коллекцию элементов корзины с комиксами
     */
    public List<CartItem> reservedComics(String customerName) {
        List<String> reservationsInFile = reservationDao.readFromFile();

        ReservationDao reservationDao = ReservationDao.INSTANCE;
        reservationDao.deleteFile();

        List<CartItem> items = new ArrayList<>();
        for (String reserve : reservationsInFile) {
            String[] elementsOfReserve = reserve.split(DELIMITER);
            if (elementsOfReserve[1].equals(customerName)) {
                DiscountComic comic = new DiscountComic(
                        elementsOfReserve[2], // наименование
                        new Author(elementsOfReserve[3]), // автор
                        new Publishing(elementsOfReserve[4]), // издательство
                        Integer.parseInt(elementsOfReserve[5]), // количество страниц
                        new Genre(elementsOfReserve[6]), // жанр
                        Integer.parseInt(elementsOfReserve[7]), // год издательства
                        Double.parseDouble(elementsOfReserve[8]), // себестоимость
                        Double.parseDouble(elementsOfReserve[9]), // цена продажи
                        Boolean.parseBoolean(elementsOfReserve[10])); // является продолжением

                items.add(new CartItem(comic, comic.getComicPrice().getSellingPrice(), elementsOfReserve[1]));
                reserves.add(comic);
            } else {
                reservationDao.saveToFile(reserve);
            }

        }
        return items;
    }

    /**
     * Запись скидок
     *
     * @param name      - наименование акции
     * @param percent   - процент скидки
     * @param dateBegin - дата начала
     * @param dateEnd   - дата окончания
     * @param cart      - корзина с комиксами
     */
    public void setDiscounts(String date,
                             String name,
                             int percent,
                             String dateBegin,
                             String dateEnd,
                             Cart cart) {
        DiscountDao discountDao = DiscountDao.INSTANCE;
        for (CartItem comic : cart.getComics()) {
            comic.getComic().setComicPrice(
                    new ComicPrice(
                            comic.getComic().getComicPrice().getCostPrice(),
                            comic.getComic().getComicPrice().getSellingPrice() -
                                    (comic.getComic().getComicPrice().getSellingPrice() * percent / 100)));
            String discount = date + DELIMITER + name + DELIMITER + percent + DELIMITER +
                    dateBegin + DELIMITER + dateEnd + DELIMITER +
                    comic.getComic().getName() + DELIMITER +
                    comic.getComic().getAuthor().getName() + DELIMITER +
                    comic.getComic().getPublishing().getName() + DELIMITER +
                    comic.getComic().getNumberOfPages() + DELIMITER +
                    comic.getComic().getGenre().getName() + DELIMITER +
                    comic.getComic().getYearOfPublishing() + DELIMITER +
                    comic.getComic().getComicPrice().getCostPrice() + DELIMITER +
                    comic.getComic().getComicPrice().getSellingPrice() + DELIMITER +
                    comic.getComic().isContinuation();

            discountDao.saveToFile(discount);
        }

        cart.clear();
    }

    /**
     * Получение акций
     *
     * @return - коллекция акций
     */
    public List<Discount> getDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        Discount discount = new Discount();
        String previousDate = "";
        for (String discountComic : discountDao.readFromFile()) {
            String[] elementsOfDiscountComic = discountComic.split(DELIMITER);
            String currentDate = elementsOfDiscountComic[0];
            if (!currentDate.equals(previousDate)) {
                discount = new Discount();
            }
            DiscountComic comic = new DiscountComic(
                    elementsOfDiscountComic[5], // наименование
                    new Author(elementsOfDiscountComic[6]), // автор
                    new Publishing(elementsOfDiscountComic[7]), // издательство
                    Integer.parseInt(elementsOfDiscountComic[8]), // количество страниц
                    new Genre(elementsOfDiscountComic[9]), // жанр
                    Integer.parseInt(elementsOfDiscountComic[10]), // год издательства
                    Double.parseDouble(elementsOfDiscountComic[11]), // себестоимость
                    Double.parseDouble(elementsOfDiscountComic[12]), // цена продажи
                    Boolean.parseBoolean(elementsOfDiscountComic[13])); // является продолжением
            // elementsOfDiscountComic[1] - наименование акции
            // elementsOfDiscountComic[3] - начало акции
            // elementsOfDiscountComic[4] - окончание акции
            discount.add(new CartItem(comic, comic.getComicPrice().getSellingPrice(), elementsOfDiscountComic[1]),
                    elementsOfDiscountComic[3], elementsOfDiscountComic[4]);
            if (!currentDate.equals(previousDate)) {
                discounts.add(discount);
            }
            previousDate = currentDate;
        }
        return discounts;
    }

    private StringBuilder formComicFromElements(String[] comic) {
        StringBuilder data = new StringBuilder();
        data
                .append(comic[0]).append(DELIMITER) // наименование
                .append(comic[1]).append(DELIMITER) // автор
                .append(comic[2]).append(DELIMITER) // издательство
                .append(comic[3]).append(DELIMITER) // количество страниц
                .append(comic[4]).append(DELIMITER) // жанр
                .append(comic[5]).append(DELIMITER) // год издательства
                .append(comic[6]).append(DELIMITER) // себестоимость
                .append(comic[7]).append(DELIMITER) // цена продажи
                .append(comic[8]).append(DELIMITER); // является продолжением
        return data;
    }

    private void getReserves() {
        List<String> reservationsInFile = reservationDao.readFromFile();
        List<CartItem> items = new ArrayList<>();
        for (String reserve : reservationsInFile) {
            String[] elementsOfReserve = reserve.split(DELIMITER);
            DiscountComic comic = new DiscountComic(
                    elementsOfReserve[2], // наименование
                    new Author(elementsOfReserve[3]), // автор
                    new Publishing(elementsOfReserve[4]), // издательство
                    Integer.parseInt(elementsOfReserve[5]), // количество страниц
                    new Genre(elementsOfReserve[6]), // жанр
                    Integer.parseInt(elementsOfReserve[7]), // год издательства
                    Double.parseDouble(elementsOfReserve[8]), // себестоимость
                    Double.parseDouble(elementsOfReserve[9]), // цена продажи
                    Boolean.parseBoolean(elementsOfReserve[10])); // является продолжением

            items.add(new CartItem(comic, comic.getComicPrice().getSellingPrice(), elementsOfReserve[1]));
            reserves.add(comic);
        }
    }

    private void getAll() {
        List<CartItem> items = new ArrayList<>();
        for (String comic : comicsInFile) {
            String[] elementsOfComic = comic.split(DELIMITER);
            DiscountComic discountComic = new DiscountComic(
                    elementsOfComic[0], // наименование
                    new Author(elementsOfComic[1]), // автор
                    new Publishing(elementsOfComic[2]), // издательство
                    Integer.parseInt(elementsOfComic[3]), // количество страниц
                    new Genre(elementsOfComic[4]), // жанр
                    Integer.parseInt(elementsOfComic[5]), // год издательства
                    Double.parseDouble(elementsOfComic[6]), // себестоимость
                    Double.parseDouble(elementsOfComic[7]), // цена продажи
                    Boolean.parseBoolean(elementsOfComic[8])); // является продолжением

            items.add(new CartItem(discountComic, discountComic.getComicPrice().getSellingPrice(), elementsOfComic[1]));
            allComics.add(discountComic);
        }
    }
}

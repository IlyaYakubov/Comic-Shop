package controllers;

import domain.Comic;
import domain.discounts.Discount;
import domain.sell.Cart;
import domain.sell.CartItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;
import services.ComicService;
import services.SearchService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SellController {

    private final int MIN_WIDTH = 700;
    private final int MIN_HEIGHT = 500;

    private final ComicService COMIC_SERVICE = ComicService.INSTANCE;
    private final SearchService SEARCH_SERVICE = SearchService.INSTANCE;
    private final Cart CART = new Cart();

    @FXML
    private TextField editTextComicName;

    @FXML
    private TableView<CartItem> tableResult;

    @FXML
    private Label labelAmount;

    @FXML
    void onClickAdd() {
        openWindow("/ui/add.fxml");
    }

    @FXML
    void onClickEdit() {
        openWindow("/ui/find_comic.fxml");
    }

    @FXML
    void onClickDelete() {
        openWindow("/ui/delete.fxml");
    }

    @FXML
    void onClickWriteOff() {
        openWindow("/ui/write_off.fxml");
    }

    @FXML
    void onClickReserve() {
        openWindow("/ui/reservation.fxml");
    }

    @FXML
    void onClickDiscounts() {
        openWindow("/ui/discounts.fxml");
    }

    @FXML
    void onClickSearch() {
        openWindow("/ui/main.fxml");
    }

    @FXML
    void onClickReports() {
        openWindow("/ui/report.fxml");
    }

    @FXML
    void onClickAddInCart() {
        String comicName = editTextComicName.getText().trim();
        Comic comic = SEARCH_SERVICE.getComicByName(comicName);
        if (comic == null) {
            return;
        }
        CART.setCartItems(tableResult.getItems());
        List<Discount> discounts = COMIC_SERVICE.getDiscounts();
        if (discounts.size() == 0) {
            CART.addComic(comic);
        }

        // проверка: если комикс с данным наименованием присутсвует в акции - добавляем его
        boolean wasReplacement = false;
        outer:
        for (Discount discount : discounts) {
            for (CartItem cartItem : discount.getCart().getCartItems()) {
                if (cartItem.getComic().getName().equals(comicName)
                        && discount.getDateBegin().isBefore(LocalDate.now())
                        && discount.getDateEnd().isAfter(LocalDate.now())) {
                    CART.addComic(cartItem.getComic());
                    wasReplacement = true;
                    break outer;
                }
            }
        }

        if (discounts.size() > 0 && !wasReplacement) {
            CART.addComic(comic);
        }

        setContent(CART.getCartItems(), CART.getAmount());
        CART.clear();
    }

    @FXML
    void onClickReserved() {
        if (Stage.getWindows().size() > 1) {
            ObservableList<Window> windows = Stage.getWindows();
            windows.get(1).requestFocus();
            windows.get(1).centerOnScreen();
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/find_customer.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FindCustomerController findCustomerController = loader.getController();
        findCustomerController.setPresenter(this);

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.show();
    }

    @FXML
    void onClickOk() {
        if (tableResult.getItems().size() == 0) {
            return;
        }
        CART.setCartItems(tableResult.getItems());
        List<CartItem> cartItems = new ArrayList<>(tableResult.getItems());
        CART.setCartItems(cartItems);
        COMIC_SERVICE.makePurchase(LocalDateTime.now(), CART);
        tableResult.getItems().clear();
        tableResult.refresh();
        labelAmount.setText("0.0");
    }

    @FXML
    void initialize() {
        TableColumn<CartItem, String> nameColumn = new TableColumn<>("Комикс");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(350.0);
        tableResult.getColumns().add(nameColumn);

        TableColumn<CartItem, Double> priceColumn = new TableColumn<>("Стоимость");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setPrefWidth(150.0);
        tableResult.getColumns().add(priceColumn);
    }

    private void openWindow(String path) {
        editTextComicName.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMinWidth(MIN_WIDTH);
        stage.setMinHeight(MIN_HEIGHT);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Установка контента в элементы окна
     *
     * @param cartItems список комиксов
     * @param amount    сумма
     */
    public void setContent(List<CartItem> cartItems, double amount) {
        tableResult.setItems(FXCollections.observableArrayList(cartItems));
        tableResult.refresh();
        labelAmount.setText(String.valueOf(amount));
    }

    /**
     * При нажатии на кнопку "Резерв"
     *
     * @param customerName имя клиента
     */
    public void onClickReserve(String customerName) {
        CART.setCartItems(tableResult.getItems());
        Cart cartWithReserves = COMIC_SERVICE.getCustomersReservedComics(customerName);
        for (CartItem cartItem : cartWithReserves.getCartItems()) {
            CART.addItem(cartItem);
        }
        setContent(CART.getCartItems(), CART.getAmount());
        cartWithReserves.clear();
        CART.clear();
    }
}

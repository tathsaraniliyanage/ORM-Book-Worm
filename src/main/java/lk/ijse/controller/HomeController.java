package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lk.ijse.projection.NotReturnUsers;
import lk.ijse.service.BookService;
import lk.ijse.service.BorrowingService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tm.NotReturnTm;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    private static HomeController controller;
    private final BorrowingService borrowingService =
            (BorrowingService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BORROWING);
    private final BookService bookService =
            (BookService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BOOK);
    private final ModelMapper modelMapper = new ModelMapper();
    private final ObservableList<NotReturnTm> observableList = FXCollections.observableArrayList();
    public TableView tblReceived;
    public TableColumn colName;
    public TableColumn colBook;
    public TableColumn colBorrowing;
    public TableColumn colDueDate;
    public TableColumn colContact;
    public TableColumn colAction;
    public Text txtAvailableBooks;
    public Text txtUnavailableBooks;
    public JFXTextField txtSearchText;

    public HomeController() {
        controller = this;
    }

    public static HomeController getController() {
        return controller;
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        tblReceived.getItems().clear();
        List<NotReturnUsers> notReturnUsers = borrowingService.getNotReturnUsersSearch(txtSearchText.getText());
        List<NotReturnTm> returnTms = modelMapper.map(notReturnUsers, new TypeToken<List<NotReturnTm>>() {
        }.getType());
        tblReceived.getItems().setAll(returnTms);
        tblReceived.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBorrowing.setCellValueFactory(new PropertyValueFactory<>("borrowing_date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("received_date"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("updateStatus"));
        tblReceived.setItems(observableList);
        loadAll();
        setCount();
    }

    private void setCount() {
        txtAvailableBooks.setText(bookService.availableBookCount() + "+");
        txtUnavailableBooks.setText(bookService.unavailableBookCount() + "+");
    }

    public void loadAll() {
        tblReceived.getItems().clear();
        List<NotReturnUsers> notReturnUsers = borrowingService.getNotReturnUsers();
        List<NotReturnTm> returnTms = modelMapper.map(notReturnUsers, new TypeToken<List<NotReturnTm>>() {
        }.getType());
        tblReceived.getItems().setAll(returnTms);
        tblReceived.refresh();
    }
}

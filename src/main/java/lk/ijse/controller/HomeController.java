package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import lk.ijse.projection.NotReturnUsers;
import lk.ijse.service.BorrowingService;
import lk.ijse.service.ServiceFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class HomeController implements Initializable {
    public TableView tblReceived;
    public TableColumn colName;
    public TableColumn colBook;
    public TableColumn colBorrowing;
    public TableColumn colDueDate;
    public TableColumn colContact;
    public Text txtAvailableBooks;
    public Text txtUnavailableBooks;

    ObservableList observableList= FXCollections.observableArrayList();

    BorrowingService borrowingService=
            (BorrowingService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BORROWING);

    public void onKeyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBorrowing.setCellValueFactory(new PropertyValueFactory<>("borrowing_date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("received_date"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        tblReceived.setItems(observableList);

        List<NotReturnUsers> notReturnUsers = borrowingService.getNotReturnUsers();
        tblReceived.getItems().setAll(notReturnUsers);
    }
}

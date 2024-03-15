package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.BorrowingDetailDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;
import lk.ijse.entity.enumuretion.BorrowingStatus;
import lk.ijse.entity.enumuretion.Status;
import lk.ijse.service.BookService;
import lk.ijse.service.BorrowingService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.tm.BorrowingListTm;
import lk.ijse.util.NavigationUtility;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class BorrowingFromController implements Initializable {
    public JFXTextField txtDescription;
    public Text txtId;
    public Text txtName;
    public Text txtDate;
    public TableView tblBook;
    public TableColumn colName;
    public TableColumn colAction;

    UserService userService= (UserService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.USER);
    BorrowingService borrowingService= (BorrowingService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.BORROWING);
    BookService bookService= (BookService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.BOOK);
    ObservableList observableList= FXCollections.observableArrayList();

    public void doneOnAction(ActionEvent actionEvent) {
        BorrowingDTO borrowingDTO = new BorrowingDTO();
        borrowingDTO.setBorrowing_date(LocalDate.parse(txtDate.getText()));
        borrowingDTO.setReceived_date(LocalDate.now());
        borrowingDTO.setDescription(txtDescription.getText());


        UserDTO userDTO = userService.getUser(Long.valueOf(txtId.getText()));
        userDTO.setId(1L);
        borrowingDTO.setUserDTO(userDTO);

        List<BorrowingDetailDTO>borrowingDetailDTOS=new ArrayList<>();
        for (BookDTO bookDTO:DashboardFormController.list){
            BorrowingDetailDTO borrowingDetailDTO = new BorrowingDetailDTO();
            borrowingDetailDTO.setBookDTO(bookDTO);
            borrowingDetailDTO.setBorrowingDTO(borrowingDTO);
            borrowingDetailDTO.setStatus(BorrowingStatus.PENDING);
            borrowingDetailDTOS.add(borrowingDetailDTO);
        }
        borrowingDTO.setBorrowingDetailDTOS(borrowingDetailDTOS);

        List<BookDTO>pendingToUpdateBookDTOs=new ArrayList<>();
        for (BookDTO bookDTO:DashboardFormController.list){
            BookDTO book = bookService.getBook(bookDTO.getId());
            bookDTO.setStatus(Status.UNAVAILABLE);
            bookDTO.setBranchDTO(book.getBranchDTO());
            pendingToUpdateBookDTOs.add(bookDTO);
        }
        boolean booking = borrowingService.booking(borrowingDTO, pendingToUpdateBookDTOs);
        if (booking){
            NavigationUtility.close(actionEvent);
            new Alert(Alert.AlertType.CONFIRMATION,"Borrowing Successful ").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Something else try agens ").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDate currentDate = LocalDate.now();
        LocalDate dateAfter7Days = currentDate.plusDays(7);
        txtDate.setText(String.valueOf(dateAfter7Days));

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("colAction"));
        tblBook.setItems(observableList);

        List<BorrowingListTm>list=new ArrayList<>();

        for (BookDTO bookDTO:DashboardFormController.list){
            list.add(new BorrowingListTm(bookDTO.getName(),null));
        }
        tblBook.getItems().setAll(list);
    }
}

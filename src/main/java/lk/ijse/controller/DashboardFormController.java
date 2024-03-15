package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tm.BookTm;
import lk.ijse.util.NavigationUtility;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class DashboardFormController implements Initializable {
    public TableView tblBook;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colStatus;
    public TableColumn colDate;
    public TableColumn colAction;
    public Text txtID;
    public Text txtUsername;
    public Text txtEmail;

    ObservableList observableList= FXCollections.observableArrayList();

    private static DashboardFormController controller;

    ModelMapper modelMapper=new ModelMapper();

    public static List<BookDTO> list=new ArrayList<>();

    public DashboardFormController() {
        controller=this;
    }

    public static DashboardFormController getController() {
        return controller;
    }

    private BookService service=
            (BookService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BOOK);
    public void bookFromTblOnClick(MouseEvent mouseEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("register_date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("colAction"));
        tblBook.setItems(observableList);
        loadBook();
    }

    public void loadBook() {
        tblBook.getItems().clear();
        List<BookDTO> all = service.findAll();
      List<BookTm> bookTms=  modelMapper.map(all,new TypeToken<List<BookTm>>() {}.getType());
      tblBook.getItems().setAll(bookTms);
      tblBook.refresh();
    }

    public void borrowingOnAction(ActionEvent actionEvent) {
        if(!list.isEmpty()){
            NavigationUtility.popupNavigation("BorrowingFrom.fxml");
        }else {
            new Alert(Alert.AlertType.WARNING,"Not Borrowing Anyone").show();
        }
    }

    public void userUpdateOnClick(MouseEvent mouseEvent) {
        UserController.id=txtID.getText();
        NavigationUtility.popupNavigation("UserFrom.fxml");
    }

    public void logOutAction(ActionEvent actionEvent) {
        NavigationUtility.switchNavigation("LoginFrom.fxml",actionEvent);
    }
}

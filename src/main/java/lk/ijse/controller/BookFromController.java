package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.dto.BookDTO;
import lk.ijse.projection.BookProjection;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tm.AdminBookTm;
import lk.ijse.util.NavigationUtility;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class BookFromController implements Initializable {
    public TableView tblBook;
    public TableColumn col;
    public TableColumn colName;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colDate;
    public TableColumn colStatus;
    public TableColumn colBranch;
    public TableColumn colUpdate;
    public TableColumn colDelete;
    public JFXTextField txtSearchText;
    ObservableList list = FXCollections.observableArrayList();

    private static BookFromController controller;

    public BookFromController() {
        controller=this;
    }

    public static BookFromController getController() {
        return controller;
    }

    private final BookService bookService =
            (BookService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BOOK);
    private final ModelMapper modelMapper=new ModelMapper();


    public void addOnAction(ActionEvent actionEvent) {
        NavigationUtility.popupNavigation("BookAddFrom.fxml");

    }

    public void onKeyReleased(KeyEvent keyEvent) {
        tblBook.getItems().clear();
        List<BookProjection> bookProjectionList = bookService.searchBooks(txtSearchText.getText());
        List<AdminBookTm> map=null;
        if (bookProjectionList!=null)
            map = modelMapper.map(
                    bookProjectionList,
                    new TypeToken<
                            List<AdminBookTm>
                            >()
                    {}.getType());

        tblBook.getItems().addAll(map);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("Branch"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        tblBook.setItems(list);

        loadAllBooks();

    }

    public void loadAllBooks() {
        tblBook.getItems().clear();
        List<BookProjection> bookProjectionList=bookService.getBooks();
        List<AdminBookTm> map=null;
        if (bookProjectionList!=null)
            map = modelMapper.map(
                    bookProjectionList,
                    new TypeToken<
                            List<AdminBookTm>
                            >()
                    {}.getType());

        tblBook.getItems().addAll(map);
    }
}

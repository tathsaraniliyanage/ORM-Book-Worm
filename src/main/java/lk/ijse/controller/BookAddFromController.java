package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.enumuretion.Status;
import lk.ijse.service.BookService;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.NavigationUtility;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class BookAddFromController implements Initializable {
    private final BranchService branchService =
            (BranchService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BRANCH);
    private final BookService bookService =
            (BookService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BOOK);
    public JFXTextField txtName;
    public Text txtBranchName;
    public JFXTextField txtGarner;
    public JFXTextField txtAuthor;
    public JFXComboBox cmbBranch;

    public void doneOnAction(ActionEvent actionEvent) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setRegister_date(LocalDate.now());
        bookDTO.setStatus(Status.AVAILABLE);
        bookDTO.setAuthor(txtAuthor.getText());
        bookDTO.setGenre(txtGarner.getText());
        bookDTO.setName(txtName.getText());
        BranchDTO branchDTO = branchService.getBranch(Long.valueOf(String.valueOf(cmbBranch.getValue())));
        bookDTO.setBranchDTO(branchDTO);

        boolean isSave = bookService.save(bookDTO);
        if (isSave) {
            NavigationUtility.close(actionEvent);
            BookFromController.getController().loadAllBooks();
            new Alert(Alert.AlertType.CONFIRMATION, "Book Add Successful ").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something else try agens ").show();
        }
    }

    public void cmbBranchOnAction(ActionEvent actionEvent) {
        BranchDTO branchDTO = branchService.getBranch(Long.valueOf(String.valueOf(cmbBranch.getValue())));
        if (branchDTO != null)
            txtBranchName.setText(branchDTO.getBranch());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<BranchDTO> all = branchService.findAll();
        List<String> ids=new ArrayList<>();
        for (BranchDTO bookDTO:all){
            ids.add(String.valueOf(bookDTO.getId()));
        }
        cmbBranch.getItems().addAll(ids);
    }
}

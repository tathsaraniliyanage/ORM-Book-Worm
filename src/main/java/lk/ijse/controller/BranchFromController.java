package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.dto.BranchDTO;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tm.BranchTm;
import lk.ijse.util.NavigationUtility;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Sasindu Malshan
 * @project BookWorm
 * @date 3/15/2024
 */

public class BranchFromController implements Initializable {
    private static BranchFromController controller;
    private final BranchService branchService =
            (BranchService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BRANCH);
    private final ObservableList<BranchTm> observableList = FXCollections.observableArrayList();
    public TableView tblBook;
    public TableColumn col;
    public TableColumn colBranch;
    public TableColumn colDate;
    public TableColumn colContact;
    public TableColumn colLocation;
    public TableColumn colUpdate;
    public TableColumn colDelete;
    private final ModelMapper modelMapper = new ModelMapper();

    public BranchFromController() {
        controller = this;
    }

    public static BranchFromController getController() {
        return controller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("Update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        tblBook.setItems(observableList);

        loadAll();
    }

    public void loadAll() {
        tblBook.getItems().clear();
        List<BranchDTO> branchDTOS = branchService.findAll();
        if (!branchDTOS.isEmpty()) {
            List<BranchTm> branchTms = modelMapper.map(branchDTOS, new TypeToken<List<BranchTm>>() {
            }.getType());
            tblBook.getItems().addAll(branchTms);
        }
        tblBook.refresh();
    }

    public void addOnAction(ActionEvent actionEvent) {
        NavigationUtility.popupNavigation("BranchAddFrom.fxml");
    }

    public void onKeyReleased(KeyEvent keyEvent) {

    }
}

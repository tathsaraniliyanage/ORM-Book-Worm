package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.dto.BranchDTO;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.NavigationUtility;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * @author Sasindu Malshan
 * @project BookWorm
 * @date 3/15/2024
 */

public class BranchUpdateFromController implements Initializable {
    public static Long id;
    private final BranchService branchService = (BranchService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.BRANCH);
    public JFXTextField txtBranch;
    public JFXTextField txtContact;
    public JFXTextField txtLocation;

    public void doneOnAction(ActionEvent actionEvent) {
        BranchDTO branchDTO = branchService.getBranch(id);
        branchDTO.setBranch(txtBranch.getText());
        branchDTO.setContact(txtContact.getText());
        branchDTO.setDate(LocalDate.now());
        branchDTO.setContact(txtContact.getText());
        branchDTO.setLocation(txtLocation.getText());

        boolean save = branchService.update(branchDTO);
        if (save) {
            BranchFromController.getController().loadAll();
            NavigationUtility.close(actionEvent);
            new Alert(Alert.AlertType.CONFIRMATION, "Branch Update Successful ").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Something else try agens ").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BranchDTO branch = branchService.getBranch(id);
        txtBranch.setText(branch.getBranch());
        txtContact.setText(branch.getContact());
        txtLocation.setText(branch.getLocation());
    }
}

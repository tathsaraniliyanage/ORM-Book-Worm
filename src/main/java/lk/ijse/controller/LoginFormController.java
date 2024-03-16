package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.dto.UserDTO;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.NavigationUtility;

import java.io.IOException;


public class LoginFormController {
    private final UserService userService =
            (UserService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.USER);
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXTextField txtPasswordText;

    public void loginOnAction(ActionEvent actionEvent) {
        UserDTO auth = userService.auth(txtPassword.getText(), txtUsername.getText());
        if (auth != null) {
            switch (auth.getUser()) {
                case ADMIN: {
                    NavigationUtility.switchNavigation("AdminDashBord.fxml", actionEvent);
                    AdminDashboardFormController.getController().txtID.setText(String.valueOf(auth.getId()));
                    AdminDashboardFormController.getController().txtEmail.setText(auth.getEmail());
                    AdminDashboardFormController.getController().txtUsername.setText(auth.getUserName());
                    break;
                }
                case USER: {
                    NavigationUtility.switchNavigation("DashBord.fxml", actionEvent);
                    DashboardFormController.getController().txtID.setText(String.valueOf(auth.getId()));
                    DashboardFormController.getController().txtEmail.setText(auth.getEmail());
                    DashboardFormController.getController().txtUsername.setText(auth.getUserName());
                    break;
                }
                default:
                    new Alert(Alert.AlertType.WARNING, "Check Your Username And Password").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Check Your Username And Password").show();
            txtUsername.clear();
            txtPassword.clear();
            txtPasswordText.clear();
        }
    }


    public void mouseEntered(MouseEvent mouseEvent) {
        txtPasswordText.setText(txtPassword.getText());
        txtPasswordText.setVisible(true);
    }


    public void mouseEnteredImg(MouseEvent mouseEvent) {
        txtPassword.setText(txtPasswordText.getText());
        txtPasswordText.setVisible(false);

    }

    public void regOnClick(MouseEvent mouseEvent) {
        try {
            NavigationUtility.switchNavigation("RgistaionFrom.fxml", mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

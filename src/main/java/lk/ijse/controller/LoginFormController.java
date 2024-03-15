package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import lk.ijse.entity.enumuretion.TypeUser;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.NavigationUtility;


public class LoginFormController {
    private final UserService userService =
            (UserService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.USER);
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXTextField txtPasswordText;

    public void loginOnAction(ActionEvent actionEvent) {
        TypeUser userType = userService.auth(txtPassword.getText(), txtUsername.getText());
        if (userType!=null) {
            switch (userType) {
                case ADMIN:
                    NavigationUtility.switchNavigation("AdminDashBord.fxml", actionEvent);
                    break;
                case USER:
                    NavigationUtility.switchNavigation("DashBord.fxml", actionEvent);
                    break;
                default:
                    new Alert(Alert.AlertType.WARNING, "Check Your Username And Password").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Check Your Username And Password").show();
            txtUsername.clear();
            txtPassword.clear();
        }

//        NavigationUtility.switchNavigation("DashBord.fxml", actionEvent);
    }



    public void mouseEntered(MouseEvent mouseEvent) {

            txtPasswordText.setText(txtPassword.getText());
            txtPasswordText.setVisible(true);
    }


    public void mouseEnteredImg(MouseEvent mouseEvent) {
        txtPassword.setText(txtPasswordText.getText());
        txtPasswordText.setVisible(false);

    }
}

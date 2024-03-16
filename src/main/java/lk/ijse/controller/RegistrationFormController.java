package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.enumuretion.TypeUser;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.NavigationUtility;



public class RegistrationFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXTextField txtMail;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;

    private final UserService userService=
            (UserService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.USER);

    public void signUpOnAction(ActionEvent actionEvent) {
        UserDTO userDTO = new UserDTO(
                0L,
                txtUsername.getText(),
                txtPassword.getText(),
                txtMail.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                TypeUser.USER
        );
        boolean isSave = userService.save(userDTO);
        if (isSave){
            new Alert(Alert.AlertType.CONFIRMATION,"ok ").show();
            NavigationUtility.switchNavigation("DashBord.fxml",actionEvent);
            DashboardFormController.getController().txtID.setText(String.valueOf(userDTO.getId()));
            DashboardFormController.getController().txtEmail.setText(userDTO.getEmail());
            DashboardFormController.getController().txtUsername.setText(userDTO.getUserName());
        }else {
            new Alert(Alert.AlertType.WARNING,"error ").show();
        }

    }
}

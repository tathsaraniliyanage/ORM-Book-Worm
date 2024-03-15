package lk.ijse.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.enumuretion.TypeUser;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;
import lk.ijse.util.NavigationUtility;

import java.net.URL;
import java.util.ResourceBundle;



public class UserController implements Initializable {
    public JFXTextField txtContact;
    public Text txtId;
    public JFXTextField txtAddress;
    public JFXTextField txtMail;
    public JFXTextField txtPassword;
    public JFXTextField txtUsername;

    private static TypeUser userType;
    public static String id;

    UserService userService = (UserService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.USER);

    public void doneOnAction(ActionEvent actionEvent) {

        UserDTO userDTO = new UserDTO(
                Long.valueOf(txtId.getText()),
                txtUsername.getText(),
                txtPassword.getText(),
                txtMail.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                userType
        );

        boolean isUpdate = userService.update(userDTO);
        if (isUpdate){
            new Alert(Alert.AlertType.CONFIRMATION,"Update Success").show();
            NavigationUtility.close(actionEvent);
        }else {
            new Alert(Alert.AlertType.WARNING,"Something Wrong").show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDTO user = userService.getUser(Long.valueOf(id));
        txtContact.setText(user.getTel());
        txtId.setText(String.valueOf(user.getId()));
        txtAddress.setText(user.getAddress());
        txtMail.setText(user.getEmail());
        txtPassword.setText(user.getPassword());
        txtUsername.setText(user.getUserName());
        userType= user.getUser();

    }
}

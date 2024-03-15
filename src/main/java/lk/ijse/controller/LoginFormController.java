package lk.ijse.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.util.NavigationUtility;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class LoginFormController {
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;

    public void loginOnAction(ActionEvent actionEvent) {
        NavigationUtility.switchNavigation("DashBord.fxml",actionEvent);
    }
}

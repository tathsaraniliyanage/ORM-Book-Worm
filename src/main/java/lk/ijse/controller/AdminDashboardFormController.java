package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.util.NavigationUtility;

import java.net.URL;
import java.util.ResourceBundle;



public class AdminDashboardFormController implements Initializable {

    private  static AdminDashboardFormController controller;
    public AnchorPane rootPane;
    public Text txtID;
    public Text txtUsername;
    public Text txtEmail;

    public AdminDashboardFormController() {
        controller=this;
    }

    public static AdminDashboardFormController getController() {
        return controller;
    }

    public void bookViewOnAction(ActionEvent actionEvent) {
        NavigationUtility.onTheTopNavigation(rootPane,"BookFrom.fxml");
    }

    public void homePageViewOnAction(ActionEvent actionEvent) {
        NavigationUtility.onTheTopNavigation(rootPane,"HomeFrom.fxml");
    }

    public void userUpdateOnClick(MouseEvent mouseEvent) {
        UserController.id=txtID.getText();
        NavigationUtility.popupNavigation("UserFrom.fxml");
    }

    public void logOutAction(ActionEvent actionEvent) {
        NavigationUtility.switchNavigation("LoginFrom.fxml",actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NavigationUtility.onTheTopNavigation(rootPane,"HomeFrom.fxml");
    }

    public void branchOnAction(ActionEvent actionEvent) {
        NavigationUtility.onTheTopNavigation(rootPane,"BranchFrom.fxml");
    }
}

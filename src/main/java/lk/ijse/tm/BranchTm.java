package lk.ijse.tm;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.controller.BookFromController;
import lk.ijse.controller.BookUpdateFromController;
import lk.ijse.controller.BranchFromController;
import lk.ijse.controller.BranchUpdateFromController;
import lk.ijse.service.BookService;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.NavigationUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @author Praboda Thathsarani
 * @Project BookWorm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BranchTm {
    private Long id;
    private String branch;
    private String location;
    private String contact;
    private LocalDate date;
    private Button Update;
    private Button Delete;

    public Button getDelete() {

        /**
         *create new java fx button
         * */
        Button button = new Button();
        /**
         *created button to add  css style
         * */
        button.setStyle(" -fx-background-radius: 20px;\n" +
                "\n" +
                "    -fx-background-color: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(56, 116, 141, 0.43),15,0,0,2);");
        /**
         *create new image in java to load image
         * */
        Image image = new Image("/assest/img/select-none.png");
        /**
         *create new image view in java fx and set a image in image view constructor to load image
         * */
        ImageView view = new ImageView(image);
        /**
         *created  image view set height and width in size 17*17 box type
         * */
        view.setFitHeight(17);
        view.setFitWidth(17);

        /**
         *created  button to set created image view in this method using
         * */
        button.setGraphic(view);
        /**
         *created  button set in customer tm object to load all cel table view
         * */
        button.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are your Sure ?", ButtonType.NO, ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.YES)) {

                BranchService branchService=
                        (BranchService) ServiceFactory
                                .getBoFactory()
                                .getService(ServiceFactory.ServiceTypes.BRANCH);

                boolean delete = branchService.delete(this.id);
                if (delete) {
                    BranchFromController.getController().loadAll();
                    new Alert(Alert.AlertType.CONFIRMATION, "Branch delete Successful ").show();
                } else {
                    new Alert(Alert.AlertType.WARNING, "Something else try agens ").show();
                }

            }


        });
        return Delete == null ? button : Delete;
    }

    public Button getUpdate() {

        /**
         *create new java fx button
         * */
        Button button = new Button();
        /**
         *created button to add  css style
         * */
        button.setStyle(" -fx-background-radius: 20px;\n" +
                "\n" +
                "    -fx-background-color: white;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(56, 116, 141, 0.43),15,0,0,2);");
        /**
         *create new image in java to load image
         * */
        Image image = new Image("/assest/img/select-none.png");
        /**
         *create new image view in java fx and set a image in image view constructor to load image
         * */
        ImageView view = new ImageView(image);
        /**
         *created  image view set height and width in size 17*17 box type
         * */
        view.setFitHeight(17);
        view.setFitWidth(17);

        /**
         *created  button to set created image view in this method using
         * */
        button.setGraphic(view);
        /**
         *created  button set in customer tm object to load all cel table view
         * */
        button.setOnAction(actionEvent -> {
            BranchUpdateFromController.id=this.id;
            NavigationUtility.popupNavigation("BranchUpdateFrom.fxml");

        });
        return Update == null ? button : Update;
    }

}

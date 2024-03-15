package lk.ijse.tm;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import lk.ijse.controller.DashboardFormController;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.enumuretion.Status;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BorrowingListTm {
    private String name;
    private Button colAction;
    public Button getColAction() {

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
//        Image image = new Image("view/assest/icon/icons8-remove-24 (2).png");
        /**
         *create new image view in java fx and set a image in image view constructor to load image
         * */
//        ImageView view = new ImageView(image);
        /**
         *created  image view set height and width in size 17*17 box type
         * */
//        view.setFitHeight(17);
//        view.setFitWidth(17);

        /**
         *created  button to set created image view in this method using
         * */
//        button.setGraphic(view);
        /**
         *created  button set in customer tm object to load all cel table view
         * */
        button.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are your Sure ?", ButtonType.NO, ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.YES)) {

            }


        });
        return colAction == null ? button : colAction;
    }
}

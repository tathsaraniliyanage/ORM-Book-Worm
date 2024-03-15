package lk.ijse.tm;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.controller.BookFromController;
import lk.ijse.controller.BookUpdateFromController;
import lk.ijse.entity.enumuretion.Status;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.NavigationUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AdminBookTm {
    private Long id;
    private String Name;
    private String Author;
    private String Genre;
    private LocalDate Date;
    private Status Status;
    private String Branch;
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

                BookService bookService =
                        (BookService) ServiceFactory
                                .getBoFactory()
                                .getService(ServiceFactory.ServiceTypes.BOOK);
                boolean delete = bookService.delete(this.id);
                if (delete) {
                    BookFromController.getController().loadAllBooks();
                    new Alert(Alert.AlertType.CONFIRMATION, "Book delete Successful ").show();
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
            BookUpdateFromController.bookId=this.id;
            NavigationUtility.popupNavigation("BookUpdateFrom.fxml");


        });
        return Update == null ? button : Update;
    }
}

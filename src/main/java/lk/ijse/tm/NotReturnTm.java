package lk.ijse.tm;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.controller.HomeController;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDetailDTO;
import lk.ijse.entity.enumuretion.BorrowingStatus;
import lk.ijse.entity.enumuretion.Status;
import lk.ijse.service.BookService;
import lk.ijse.service.BorrowingDetailService;
import lk.ijse.service.ServiceFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class NotReturnTm {
    private Long id;
    private Long borrowing_id;
    private String username;
    private String name;
    private LocalDate borrowing_date;
    private LocalDate received_date;
    private String Tel;
    private Button updateStatus;

    private BookService service =
            (BookService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.BOOK);

    private BorrowingDetailService borrowingDetailService = (BorrowingDetailService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.BORROWING_DETAILS);

    public Button getUpdateStatus() {

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
            System.out.println("book_id"+this.id);
            System.out.println("borrowing_id"+this.borrowing_id);
            Alert alert = new Alert(Alert.AlertType.WARNING, "Are your Sure ?", ButtonType.NO, ButtonType.YES);
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.YES)) {
                BookService bookService = (BookService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.BOOK);
                BookDTO book = bookService.getBook(this.id);
                book.setStatus(Status.AVAILABLE);
                boolean update = bookService.update(book);

                if (update) {
                    BorrowingDetailDTO dto = borrowingDetailService.getDetails(this.id, this.borrowing_id);
                    dto.setStatus(BorrowingStatus.RETURNED);
                    boolean update2 = borrowingDetailService.update(dto);
                    if (update2){
                        new Alert(Alert.AlertType.CONFIRMATION, "This Book Returned now is AVAILABLE !").show();
                        HomeController.getController().loadAll();
                    }else {
                        new Alert(Alert.AlertType.WARNING, "This Book Not AVAILABLE !").show();
                    }
                } else {
                    new Alert(Alert.AlertType.WARNING, "This Book Not AVAILABLE !").show();
                }
            }


        });
        return updateStatus == null ? button : updateStatus;
    }
}

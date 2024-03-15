import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.enumuretion.TypeUser;
import lk.ijse.projection.NotReturnUsers;
import lk.ijse.service.BookService;
import lk.ijse.service.BorrowingService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.service.UserService;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project BookWorm
 * @date 3/11/2024
 */

public class Main {

   private static final UserService userService=
            (UserService) ServiceFactory
                    .getBoFactory()
                    .getService(ServiceFactory.ServiceTypes.USER);
   private static final BookService bookService =
           (BookService) ServiceFactory.getBoFactory().getService(ServiceFactory.ServiceTypes.BOOK);

   private static final BorrowingService borrowingService=
           (BorrowingService) ServiceFactory
                   .getBoFactory()
                   .getService(ServiceFactory.ServiceTypes.BORROWING);

    public static void main(String[] args) {
//       userTest();
//       bookTest();
//        BookDTO book = bookService.getBook(1L);
//        System.out.println(book.toString());

//        List<NotReturnUsers> notReturnUsers = borrowingService.getNotReturnUsers();
//        System.out.println(notReturnUsers);

        BookDTO book = bookService.getBook(1L);
        System.out.println(book.toString());


    }

    private static void bookTest() {
        List<BookDTO> search = bookService.search("1");
        System.out.println(search);
    }

    private static void userTest() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(4L);
        userDTO.setAddress("Galle");
        userDTO.setTel("0770666666");
        userDTO.setEmail("sassind@gmail.com");
        userDTO.setPassword("sasindu1234");
        userDTO.setUser(TypeUser.USER);
        userDTO.setUserName("sasidndu2001");

        boolean save = userService.update(userDTO);
        System.out.println(save);

    }

}

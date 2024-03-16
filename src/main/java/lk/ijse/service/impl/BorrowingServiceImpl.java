package lk.ijse.service.impl;

import lk.ijse.cofig.FactoryConfiguration;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.BorrowingDetailDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowing;
import lk.ijse.entity.BorrowingDetail;
import lk.ijse.projection.NotReturnUsers;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.BorrowingDetailRepository;
import lk.ijse.repository.BorrowingRepository;
import lk.ijse.repository.RepoFactory;
import lk.ijse.service.BorrowingService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.List;



public class BorrowingServiceImpl implements BorrowingService {
    private final Session session = FactoryConfiguration.getInstance().getSession();
    private final ModelMapper modelMapper = new ModelMapper();
    private final BorrowingRepository borrowingRepository=
            (BorrowingRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.BORROWING);

    private final BookRepository bookRepository=
            (BookRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.BOOK);

    private final BorrowingDetailRepository borrowingDetailRepository=
            (BorrowingDetailRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.BORROWING_DETAILS);


    @Override
    public boolean save(BorrowingDTO dto) {
        return false;
    }

    @Override
    public boolean update(BorrowingDTO dto) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<BorrowingDTO> findAll() {
        return null;
    }

    @Override
    public List<BorrowingDTO> search(String text) {
        return null;
    }

    @Override
    public boolean booking(BorrowingDTO borrowingDTO, List<BookDTO> pendingToUpdateBookDTOs) {
        Transaction transaction = session.beginTransaction();
        try {
//            borrowingRepository.setSession(session);
//            borrowingRepository.save(modelMapper.map(borrowingDTO, Borrowing.class));

            List<BorrowingDetailDTO> borrowingDetailDTOS = borrowingDTO.getBorrowingDetailDTOS();
            borrowingDetailRepository.setSession(session);
            for (BorrowingDetailDTO borrowingDetailDTO:borrowingDetailDTOS){
                borrowingDetailRepository.save(modelMapper.map(borrowingDetailDTO, BorrowingDetail.class));
            }

            bookRepository.setSession(session);
            for (BookDTO bookDTO:pendingToUpdateBookDTOs){
                bookRepository.update(modelMapper.map(bookDTO, Book.class));
            }
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
    }

    @Override
    public List<NotReturnUsers> getNotReturnUsers() {
       try {
           borrowingRepository.setSession(session);
          return borrowingRepository.getNotReturnUsers();
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public List<NotReturnUsers> getNotReturnUsersSearch(String searchText) {
        try {
            borrowingRepository.setSession(session);
            return borrowingRepository.getNotReturnUsersSearch( searchText);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

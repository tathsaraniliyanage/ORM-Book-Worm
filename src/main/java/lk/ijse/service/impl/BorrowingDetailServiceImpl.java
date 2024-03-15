package lk.ijse.service.impl;

import lk.ijse.cofig.FactoryConfiguration;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDTO;
import lk.ijse.dto.BorrowingDetailDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Borrowing;
import lk.ijse.entity.BorrowingDetail;
import lk.ijse.repository.BorrowingDetailRepository;
import lk.ijse.repository.RepoFactory;
import lk.ijse.service.BorrowingDetailService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;

import java.util.List;


public class BorrowingDetailServiceImpl implements BorrowingDetailService {
    private final Session session = FactoryConfiguration.getInstance().getSession();
    private final ModelMapper modelMapper = new ModelMapper();
    private final BorrowingDetailRepository borrowingDetailRepository =
            (BorrowingDetailRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.BORROWING_DETAILS);

    @Override
    public boolean save(BorrowingDetailDTO dto) {
        return false;
    }

    @Override
    public boolean update(BorrowingDetailDTO dto) {
        Transaction transaction = session.beginTransaction();
        try {
            System.out.println(dto.toString());
            borrowingDetailRepository.setSession(session);
            borrowingDetailRepository.update(modelMapper.map(dto,BorrowingDetail.class));
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<BorrowingDetailDTO> findAll() {
        return null;
    }

    @Override
    public List<BorrowingDetailDTO> search(String text) {
        return null;
    }

    @Override
    public BorrowingDetailDTO getDetails(Long book_id, Long borrowing_id) {

        try {
            borrowingDetailRepository.setSession(session);
            BorrowingDetail details = borrowingDetailRepository.getDetails(book_id, borrowing_id);
            Book book = details.getBook();
            Borrowing borrowing = details.getBorrowing();
            BookDTO bookDTO=modelMapper.map(book,BookDTO.class);
            BorrowingDTO borrowingDTO=modelMapper.map(borrowing,BorrowingDTO.class);
            BorrowingDetailDTO dto=new BorrowingDetailDTO(
                    details.getId(),
                    bookDTO,
                    borrowingDTO,
                    details.getStatus()
            );

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}

package lk.ijse.service.impl;

import lk.ijse.cofig.FactoryConfiguration;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BorrowingDetailDTO;
import lk.ijse.repository.BorrowingDetailRepository;
import lk.ijse.repository.RepoFactory;
import lk.ijse.service.BorrowingDetailService;
import org.hibernate.Session;
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
        return false;
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
}

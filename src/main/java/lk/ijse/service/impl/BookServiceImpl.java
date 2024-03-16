package lk.ijse.service.impl;

import lk.ijse.cofig.FactoryConfiguration;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookProjection;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.RepoFactory;
import lk.ijse.service.BookService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;



public class BookServiceImpl implements BookService {
    private final Session session = FactoryConfiguration.getInstance().getSession();
    private final ModelMapper modelMapper = new ModelMapper();
    private final BookRepository repository =
            (BookRepository) RepoFactory
                    .getRepoFactory()
                    .getRepo(RepoFactory.RepositoryTypes.BOOK);

    @Override
    public boolean save(BookDTO bookDTO) {
        try {
            repository.setSession(session);
            Long save = repository.save(modelMapper.map(bookDTO, Book.class));
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            return save != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BookDTO bookDTO) {
        try {
            repository.setSession(session);
            repository.update(modelMapper.map(bookDTO, Book.class));
            Transaction transaction = session.beginTransaction();
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BookDTO> findAll() {
        try {
            repository.setSession(session);
            return modelMapper.map( repository.findAll(),new TypeToken<List<BookDTO>>() {
            }.getType());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<BookDTO> search(String text) {
        try {
            repository.setSession(session);
            return modelMapper.map( repository.search(text),new TypeToken<List<BookDTO>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            repository.setSession(session);
            Transaction transaction = session.beginTransaction();
            repository.delete(id);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int count() {
        try {
            repository.setSession(session);
            int count = repository.count();

            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public BookDTO getBook(Long id) {
        try {
            repository.setSession(session);
            Book exits = repository.isExits(id);
            BranchDTO map1 = modelMapper.map(exits.getBranch(), BranchDTO.class);
            BookDTO map = modelMapper.map(exits, BookDTO.class);
            map.setBranchDTO(map1);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<BookProjection> getBooks() {
        try {
            repository.setSession(session);
            return repository.getBooks();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int availableBookCount() {
        try {
            repository.setSession(session);
            int count = repository.availableBookCount();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public int unavailableBookCount() {
        try {
            repository.setSession(session);
            int count = repository.unavailableBookCount();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return Integer.MIN_VALUE;
        }
    }
}
